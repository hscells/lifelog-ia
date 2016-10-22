$(document).ready(function() {

  var Concept = function(conceptId, imageId) {
    this.conceptId = conceptId;
    this.imageId = imageId;
  };

  var itemTemplate = _.template($("#item-template").text());
  var assessmentTemplate = _.template($("#assessment-template").text());
  var topicTemplate = _.template($("#topic-template").text());
  var conceptTemplate = _.template($("#concept-button").text());
  var resizedWindow = false;
  var conceptsRemaining = 0;
  var renderDeferred;
  var unannotatedConcepts = [];
  var annotatedConcepts = [];
  var concept_data = undefined;

  var startTime = 0;

  var getTopic = function (imageId) {
    $.ajax({
      url: "/api/topics/" + imageId,
      dataType: "json",
      method: "GET",
      success: function(data) {
        renderTopic(data)
      }
    })
  };

  var renderTopic = function (topic) {
    if (topic != undefined) {
      $("#topic").html(topicTemplate(topic)).fadeIn('fast')
    }
  };

  var getImage = function() {
    renderDeferred = $.Deferred();
    $.ajax({
      url: "/api/annotations/assessment/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
        renderImage(data);
        $.when(renderDeferred).done(function(image) {
          getConcepts(image["id"]);
          getTopic(image["name"]);
        });
      }
    })
  };

  var getConcepts = function(imageId) {
    if (concept_data === undefined) {
      $.ajax({
        url: "/api/annotations/assessment/concepts/" + imageId,
        dataType: "json",
        method: "GET",
        success: function(data) {
          concept_data = data;
          renderConcepts(imageId, data);
        }
      });
    } else {
      renderConcepts(imageId, concept_data);
    }
  };

  var renderImage = function(image) {

    // resize the size of the screen so annotators don't have to scroll
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    // set the start time
    startTime = image["startTime"];

    // render the main item html
    $("#main").html(itemTemplate(image)).fadeIn('fast');

    //noinspection JSUnresolvedFunction
    renderDeferred.resolve(image);
  };

  var renderConcepts = function(imageId, concepts) {
    unannotatedConcepts = [];
    annotatedConcepts = [];
    // render the concepts
    for (var i = 0; i < concepts.length; i++) {
      var concept = concepts[i];
      unannotatedConcepts.push(new Concept(concept['id'], imageId));
      var conceptButton = $(conceptTemplate(concept));
      $(conceptButton).click({concept: concept}, function(event){
        $("#assessments").append(assessmentTemplate(event.data.concept));
        // add a listener to the assessment buttons that will annotate concepts -> image
        var assessmentButton = $(".assessment-button");
        assessmentButton.off();
        assessmentButton.click(function() {
          // ew gross this is such a hack
          var assessmentIdEl = $(this)[0];
          var relevance = assessmentIdEl.id.split("-")[0];
          var conceptId = assessmentIdEl.id.split("-")[1];
          annotate(imageId, relevance, conceptId);
          annotatedConcepts.push(parseInt(conceptId));
        });
        $(this).remove()
      });
      $("#available-concepts").append(conceptButton).hide().slideDown();
    }

    // the number of concepts remaining is unknown until this point
    conceptsRemaining = concepts.length;

    $(".next").click(function() {
      $("#main").fadeOut('fast');
      $("#topic").fadeOut('fast');
      getImage();
      var k = 0;
      for (var i = 0; i < unannotatedConcepts.length; i++) {
        var concept = unannotatedConcepts[i];
        if (annotatedConcepts.indexOf(concept.conceptId) < 0) {
          annotate(concept.imageId, 0, concept.conceptId);
          k++;
        }
      }
    });

  };

  var annotate = function(imageId, relevance, conceptId) {
    var json = {
      "imageId": imageId,
      "relevance": relevance,
      "conceptId": conceptId,
      "startTime": startTime
    };

    $("#" + conceptId).remove();

    $.ajax({
      url: "/api/annotations/assessment/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {

      },
      error: function() {
        toastr.error("An error occurred, previous image was not annotated.")
      }
    });
  };

  getImage();
});
