$(document).ready(function() {

  var template = _.template($("#item-template").text());
  var tagTemplate = _.template($("#tag-template").text());
  var resizedWindow = false;
  var imagesRemaining = 1;

  var tags = [];
  var suggestions = [];
  var imageId;

  var getTags = function() {
    $.ajax({
      url: "/api/annotations/tag/tags",
      dataType: "json",
      method: "GET",
      success: function(data) {
        suggestions = data;
      }
    });
  };

  var getImages = function() {
    $.ajax({
      url: "/api/annotations/tag/images",
      dataType: "json",
      method: "GET",
      success: function(data) {
          renderImages(data)
      }
    });
  };

  var renderImages = function(images) {
    tags = [];

    if (!resizedWindow) {
      $("#lifelog-app").height($(document).height());
      resizedWindow = true;
    }

    for(var i = 0; i < images.length; i++) {
      var image = images[i];
      imageId = image["id"];
      $("#main").append(template(image));

      $("#input-" + image["id"]).typeahead({ source: suggestions });

      document.querySelector("#input-" + image["id"]).addEventListener("keypress", function(e) {
        var key = e.which || e.keyCode;
        if (key === 13) {
          var el = this;
          var imageId = el.id.replace("input-", "");
          var tag = $(el).val();
          addTag(imageId, tag);
          $(el).val("");
        }
      });

    }

    $(".save").click(function() {
      annotate(imageId);
    });

    $(".add-tag").click(function() {
      var inputEl = $("#input-" + imageId);
      var tag = $(inputEl).val();
      addTag(imageId, tag);
      $(inputEl).val("");
    });

    $("#input-" + imageId).focus();
  };

  var addTag = function(imageId, tag) {
    var tagEl = tagTemplate({name: tag});
    if (tags.indexOf(tag) === -1 && tag != "") {
      tags.push(tag);
      $(tagEl).appendTo($("#tags-" + imageId)).click(function() {
        tags = _.without(tags, $(this).text());
        this.remove();
      });
    } else {
      toastr.info("Image already contains this tag")
    }
  };

  var annotate = function(imageId) {

    var json = {
      "imageId": imageId,
      "annotation": tags
    };

    $.ajax({
      url: "/api/annotations/tag/annotate",
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
          getTags();
        }
      }
    })
  };

  getImages();
  getTags();

});
