
var sessionId = '';

function getUrl() {
  console.log("Button pressed");
  generateUrl(document.getElementById('id').value);
}

function generateUrl(url) {
    console.log("Generate url: url = " + url);
    var path = '/genUrl?url=' + url;

    $.ajax({
      type:    "POST",
      url:     path,
      success: function(data) {
            var response = jQuery.parseJSON(data);
            returnUrl = response.url;
            console.log(returnUrl);
            window.location = returnUrl
      }
    });
}

