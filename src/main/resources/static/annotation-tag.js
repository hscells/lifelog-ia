$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var tagTemplate = _.template($("#tag-template").text());
  var topicTemplate = _.template($("#topic-template").text());
  var resizedWindow = false;

  var tags = [];
  var imageId;
  var renderDeferred;

  var startTime = 0;

  var getImage = function() {
    renderDeferred = $.Deferred();
    $.ajax({
      url: "/api/annotations/tag/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
        renderImage(data);
        $.when(renderDeferred).done(function() {
          getTags();
        });
      }
    });
  };

  var getTags = function() {
    $.ajax({
      url: "/api/annotations/tag/tags",
      dataType: "json",
      method: "GET",
      success: function(data) {
        // initialise the typeahead and focus the text field
        $("#input").typeahead({ source: data }).focus();
      }
    });
  };

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

  var renderImage = function(image) {

    getTopic(image['name']);

    // resize the size of the screen so annotators don't have to scroll
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    // this empties the list of tags
    tags = [];

    // set the start time
    startTime = image["startTime"];

    imageId = image["id"];
    $("#main").html(template(image)).fadeIn('fast');

    // we need an event listener so that the enter key can add tags
    $("#input").keypress(function(e) {
      var key = e.which || e.keyCode;
      if (key === 13) {
        var el = this;
        var tag = $(el).val();
        addTag(imageId, tag);
        $(el).val("");
      }
    });

    // event listener to move onto the next item
    $(".save").click(function() {
      annotate(imageId);
    });

    // event listener for doing the same thing as pressing enter in the text field
    $(".add-tag").click(function() {
      var inputEl = $("#input");
      var tag = $(inputEl).val();
      addTag(imageId, tag);
      $(inputEl).val("");
    });

    //noinspection JSUnresolvedFunction
    renderDeferred.resolve();
  };

  /**
   * Visually add a tag to the interface
   * @param imageId
   * @param tag
   */
  var addTag = function(imageId, tag) {
    // create a dom node from the template
    var tagEl = tagTemplate({name: tag});
    // check if the tag has already been added or if it's empty
    if (tags.indexOf(tag) === -1 && tag != "") {
      tags.push(tag);
      // add an event listener to remove the tag once it's been added in the interface
      $(tagEl).appendTo($("#tags-" + imageId)).click(function() {
        tags = _.without(tags, $(this).text().trim());
        this.remove();
      });
    } else {
      toastr.info("Image already contains this tag")
    }
  };

  var annotate = function(imageId) {

    $("#main").fadeOut('fast');
    $("#topic").fadeOut('fast');

    var json = {
      "imageId": imageId,
      "annotation": tags,
      "startTime": startTime
    };

    $.ajax({
      url: "/api/annotations/tag/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {
        getImage();
        getTags();
      },
      error: function() {
        toastr.error("An error occurred, previous image was not annotated.")
        getImage();
        getTags();
      }
    })
  };

  getImage();
});
