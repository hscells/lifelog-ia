$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var topicTemplate = _.template($("#topic-template").text());
  var resizedWindow = false;

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
    $.ajax({
      url: "/api/annotations/query/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImages(data)
      }
    })
  };

  var renderImages = function(image) {

    getTopic(image['name']);

    // resize the size of the screen so annotators don't have to scroll
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    // render the item
    $("#main").html(template(image)).fadeIn('fast');

    // add an event listener for the save button
    $("#save").click(function() {
      annotate(image["id"]);
    });

    // set the start time
    startTime = image["startTime"];


    $("#annotation").keydown(function (e) {
      //noinspection JSUnresolvedVariable
      var keyCode = e.keyCode ? e.keyCode : e.witch;
      if (keyCode == 13) {
        annotate(image["id"]);
      }
    }).focus(); // place the cursor in the text field

  };

  var annotate = function(imageId) {
    var annotation = $("#annotation").val();

    $("#main").fadeOut('fast');
    $("#topic").fadeOut('fast');

    var json = {
      "imageId": imageId,
      "annotation": annotation,
      "startTime": startTime
    };

    $.ajax({
      url: "/api/annotations/query/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {
        getImage();
      },
      error: function() {
        toastr.error("An error occurred, previous image was not annotated.")
        getImage();
      }
    })
  };

  getImage();
});
