$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var resizedWindow = false;
  var imagesRemaining = 1;

  var getImages = function() {
    $.ajax({
      url: "/api/annotations/assessment/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImages(data)
      }
    })
  };

  var renderImages = function(images) {
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height()/1.5);
      resizedWindow = true;
    }

    for(var i = 0; i < images.length; i++) {
      var image = images[i];
      $("#main").append(template(image));
    }

    $(".assessment-button").click(function() {
      // ew gross this is such a hack
      var assessmentIdEl = $(this)[0];
      var relevance = assessmentIdEl.id.split("-")[0];
      var imageId = assessmentIdEl.id.split("-")[1];
      var conceptId = assessmentIdEl.id.split("-")[2];
      annotate(imageId, relevance, conceptId);
    });

  };

  var annotate = function(imageId, relevance, conceptId) {
    var json = {
      "imageId": imageId,
      "relevance": relevance,
      "conceptId": conceptId
    };

    $.ajax({
      url: "/api/annotations/assessment/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {
        $("#item-" + imageId).remove();
        imagesRemaining--;
        if (imagesRemaining === 0) {
          imagesRemaining = 1;
          getImages();
        }
      }
    })
  };

  getImages();
});
