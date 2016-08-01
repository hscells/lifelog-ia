$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var resizedWindow = false;

  var getImage = function() {
    $.ajax({
      url: "/api/annotations/textual/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImage(data)
      }
    })
  };

  var renderImage = function(image) {
    // resize the size of the screen so annotators don't have to scroll
    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    // render the item in the interface
    $("#main").html(template(image)).fadeIn('fast');

    // add an event listener to the save button to move onto the next image to be annotated
    $(".save").click(function() {
      // ew gross this is such a hack
      var textbox = $(this).prev()[0];
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
      async: false,
      data: JSON.stringify(json),
      success: function() {
        $("#main").fadeOut('fast');
        getImage();
      }
    })
  };

  getImage();
});
