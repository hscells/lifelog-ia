$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var resizedWindow = false;

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

    var json = {
      "imageId": imageId,
      "annotation": annotation
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
