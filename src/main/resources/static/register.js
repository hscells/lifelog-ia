$("#register").click(function() {

  var alias = $("#alias").val();
  var email = $("#email").val();
  var password = $("#password").val();

  var json = {
    "alias": alias,
    "email": email,
    "password": password
  };

  $.ajax({
    url: "/api/people/register",
    data: JSON.stringify(json),
    contentType: "application/json",
    method: "POST",
    success: function() {
      toastr.success("You have registered. Please wait to be approved.");
      window.location = "/"
    },
    error: function() {
      toastr.error("You could not be registered")
    }
  })

});