$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var imagesRemaining = 10;

  var getImages = function() {
    $.ajax({
      url: "/api/annotations/textual/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImages(data)
      }
    })
  };

  var renderImages = function(images) {
    for(var i = 0; i < images.length; i++) {
      var image = images[i];
      $("#main").append(template(image));
    }

    $(".save").click(function() {
      // ew gross this is such a hack
      var textbox = $(this).siblings()[0];
      var imageId = textbox.id;
      annotate(imageId);
    });

  };

  var annotate = function(imageId) {
    var annotation = $("#" + imageId).val();

    var json = {
      "imageId": imageId,
      "annotation": annotation
    };

    $.ajax({
      url: "/api/annotations/textual/annotate",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify(json),
      success: function() {
        $("#item-" + imageId).remove();
        imagesRemaining--;
        if (imagesRemaining === 0) {
          imagesRemaining = 10;
          getImages();
        }
      }
    })
  };

  getImages();
});
