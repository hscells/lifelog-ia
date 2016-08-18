$(document).ready(function() {

  var itemTemplate = _.template($("#item-template").text());
  var assessmentTemplate = _.template($("#assessment-template").text());
  var resizedWindow = false;
  var conceptsRemaining = 0;
  var renderDeferred;

  var getImage = function() {
    renderDeferred = $.Deferred();
    $.ajax({
      url: "/api/annotations/assessment/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
        renderImage(data);
        $.when(renderDeferred).done(function(image) {
          getConcepts(image["id"])
        });
      }
    })
  };

  var getConcepts = function(imageId) {
    var concepts = [];
    $.ajax({
      url: "/api/annotations/assessment/concepts/" + imageId,
      dataType: "json",
      method: "GET",
      success: function(data) {
       renderConcepts(imageId, data);
      }
    });
  };

  var renderImage = function(image) {
    // resize the size of the screen so annotators don't have to scroll
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    // render the main item html
    $("#main").html(itemTemplate(image)).fadeIn('fast');

    //noinspection JSUnresolvedFunction
    renderDeferred.resolve(image);
  };

  var renderConcepts = function(imageId, concepts) {
    // render the concepts
    for (var i = 0; i < concepts.length; i++) {
      $("#assessments").append(assessmentTemplate(concepts[i])).hide().slideDown();
    }

    // the number of concepts remaining is unknown until this point
    conceptsRemaining = concepts.length;

    // add a listener to the assessment buttons that will annotate concepts -> image
    $(".assessment-button").click(function() {
      // ew gross this is such a hack
      var assessmentIdEl = $(this)[0];
      var relevance = assessmentIdEl.id.split("-")[0];
      var conceptId = assessmentIdEl.id.split("-")[1];
      annotate(imageId, relevance, conceptId);
    });
  };

  var annotate = function(imageId, relevance, conceptId) {
    var json = {
      "imageId": imageId,
      "relevance": relevance,
      "conceptId": conceptId
    };

    $("#main").fadeOut('fast');

    $("#" + conceptId).remove();

    $.ajax({
      url: "/api/annotations/assessment/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {
        conceptsRemaining--;
        if (conceptsRemaining === 0) {
          conceptsRemaining = 1;
          getImage();
        }
      },
      error: function() {
        toastr.error("An error occurred, previous image was not annotated.")
        getImage();
      }
    });
  };

  getImage();
});
