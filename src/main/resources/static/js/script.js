$(document).ready(function () {
  $('[data-toggle="tooltip"]').tooltip();
});

window.onload = function () {
  // register link click
  document
    .getElementById("registerLink")
    .addEventListener("click", function () {
      event.preventDefault(); // Prevent the default action of the link
      document.getElementById("loginSection").style.display = "none";
      document.getElementById("registerSection").style.display = "block";
    });

  // login link click
  document.getElementById("loginLink").addEventListener("click", function () {
    event.preventDefault(); // Prevent the default action of the link
    document.getElementById("loginSection").style.display = "block";
    document.getElementById("registerSection").style.display = "none";
  });

  //register function
  document
    .getElementById("registerButton")
    .addEventListener("click", function (event) {
      event.preventDefault(); // Prevent the form from submitting normally
      // Get form elements
      var username = document.getElementById("username").value;
      var password = document.getElementById("password").value;

      // Create an object to store the form data
      var data = {
        username: username,
        password: password,
        userType: "customer",
      };

      // Convert the object to a JSON string
      var json = JSON.stringify(data);

      // Send a POST request to the server
      fetch("http://localhost:8081/users/new", {
        method: "POST",
        "no-cors": true,
        headers: {
          "Content-Type": "application/json",
        },
        body: json,
      })
        .then(function (response) {
          if (response.ok) {
            return response.text();
          } else if (response.status === 409) {
            throw new Error("username exist!" + response.statusText);
          } else if (response.status === 401) {
            throw new Error("Unauthorized !" + response.statusText);
          } else {
            throw new Error("Something wrong ! " + response.statusText);
          }
        })
        .then(function (data) {
          // Open a pop-up window to display the success message
          var popupWindow = window.open("", "_blank", "width=400,height=200");
          popupWindow.document.write("<h1>Success</h1>");
          popupWindow.document.write(
            "<p>" + data + "</p>" + "<p> Please login to continue </p>"
          );
          popupWindow.document.write(
            "<button onclick='window.close()'>Close</button>"
          );
          popupWindow.document.close();
          document.getElementById("loginSection").style.display = "block";
          document.getElementById("registerSection").style.display = "none";
        })
        .then(function () {
          // Clear the form
          document.getElementById("username").value = "";
          document.getElementById("password").value = "";
          document.getElementById("confirmPassword").value = "";
        })
        .catch(function (error) {
          // Open a pop-up window to display the error message
          var popupWindow = window.open("", "_blank", "width=400,height=200");
          popupWindow.document.write("<h1>Error</h1>");
          popupWindow.document.write("<p>" + error.message + "</p>");
          popupWindow.document.write(
            "<button onclick='window.close()'>Close</button>"
          );
          popupWindow.document.close();
        });
    });
};
