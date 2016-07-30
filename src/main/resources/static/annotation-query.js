$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var resizedWindow = false;
  var imagesRemaining = 1;
  var imageId;

  var getImages = function() {
    $.ajax({
      url: "/api/annotations/query/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImages(data)
      }
    })
  };

  var renderImages = function(images) {
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }
    for(var i = 0; i < images.length; i++) {
      var image = images[i];
      imageId = image['id'];
      $("#main").append(template(image));
    }

    $("#save").click(function() {
      annotate(imageId);
    });

    $("#annotation").keydown(function (e) {
      //noinspection JSUnresolvedVariable
      var keyCode = e.keyCode ? e.keyCode : e.witch;
      if (keyCode == 13) {
        annotate(imageId);
      }
    });

    $("#annotation").focus();

  };

  var annotate = function(imageId) {
    var annotation = $("#annotation").val();

    var json = {
      "imageId": imageId,
      "annotation": annotation
    };

    $.ajax({
      url: "/api/annotations/query/annotate",
      contentType: "application/json",
      method: "POST",
      async: false,
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
