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
            return response.json();
          } else if (response.status === 409) {
            return response.json().then((error) => {
              throw new Error("username exist!" + response.statusText);
            });
          } else if (response.status === 401) {
            return response.json().then((error) => {
              throw new Error("Unauthorized !" + response.statusText);
            });
          } else {
            return response.json().then((error) => {
              throw new Error("Something wrong ! " + response.statusText);
            });
          }
        })
        .then(function (data) {
          alert(data.message);
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
          alert("error: " + error.message);
        });
    });

  //order function - read info from card
  $(document).ready(function () {
    $(".add-to-cart").on("click", function () {
      var card = $(this).closest(".card");
      var coffeeName = card.find(".card-title").text();
      var coffeePrice = card.find(".card-price").text();

      $("#orderModal #coffeeName").val(coffeeName);
      $("#orderModal #coffeePrice").val(coffeePrice);
    });
  });

  // order function - read info from modal and manage order table
  $(document).ready(function () {
    var rowNumber = 1;
    var totalPrice = 0;

    $(".add-to-cart").on("click", function () {
      var coffeeName = $(this).data("coffee-name");
      var coffeePrice = parseFloat($(this).data("coffee-price").substring(1));

      // populate the modal with the coffee name and price
      $("#orderModal #coffeeName").val(coffeeName);
      $("#orderModal #coffeePrice").val(coffeePrice);

      // show the modal
      $("#orderModal").modal("show");
    });

    function updatePlaceOrderButton() {
      if ($("#orderTable tbody tr").length > 0) {
        $("#placeOrder").show();
      } else {
        $("#placeOrder").hide();
      }
    }

    updatePlaceOrderButton();

    $("#orderModal .btn-primary").on("click", function () {
      var coffeeName = $("#orderModal #coffeeName").val();
      var coffeePrice = parseFloat(
        $("#orderModal #coffeePrice").val().substring(1)
      );
      var sizeOption = $("#orderModal #sizeOption").val();
      var sugarOption = $("#orderModal #sugarOption").val();
      var milkOption = $("#orderModal #milkOption").val();

      var newRow =
        "<tr><td class='row-number'>" +
        rowNumber +
        "</td><td>" +
        coffeeName +
        "</td><td>" +
        sizeOption +
        "</td><td>" +
        sugarOption +
        "</td><td>" +
        milkOption +
        "</td><td class='count'>1</td><td><button class='increase'>+</button><button class='decrease'>-</button><button class='delete'>x</button></td><td class='price' hidden>" +
        coffeePrice +
        "</td></tr>";

      $("#orderTable tbody").append(newRow);

      totalPrice += coffeePrice;
      $("#totalPrice").text("€" + totalPrice.toFixed(2));

      $("#orderModal").modal("hide");

      rowNumber++;

      updatePlaceOrderButton();
    });

    $("#orderTable").on("click", ".increase", function () {
      var countCell = $(this).closest("tr").find(".count");
      var count = parseInt(countCell.text());
      countCell.text(count + 1);

      var price = parseFloat($(this).closest("tr").find(".price").text());
      totalPrice += price;
      $("#totalPrice").text("€" + totalPrice.toFixed(2));
    });

    $("#orderTable").on("click", ".decrease", function () {
      var countCell = $(this).closest("tr").find(".count");
      var count = parseInt(countCell.text());
      if (count > 1) {
        countCell.text(count - 1);

        var price = parseFloat($(this).closest("tr").find(".price").text());
        totalPrice -= price;
        $("#totalPrice").text("€" + totalPrice.toFixed(2));
      }
    });

    $("#orderTable").on("click", ".delete", function () {
      var count = parseInt($(this).closest("tr").find(".count").text());
      var price = parseFloat($(this).closest("tr").find(".price").text());
      totalPrice -= price * count;
      $("#totalPrice").text("€" + totalPrice.toFixed(2));

      $(this).closest("tr").remove();

      updateRowNumbers();
      updatePlaceOrderButton();
    });

    function updateRowNumbers() {
      $("#orderTable tbody tr").each(function (index) {
        $(this)
          .find(".row-number")
          .text(index + 1);
      });
      rowNumber = $("#orderTable tbody tr").length + 1;
    }
  });

  // login-function
  document
    .getElementById("custLoginForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();

      var username = document.getElementById("customer-username").value;
      var password = document.getElementById("customer-password").value;

      fetch("http://localhost:8081/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username: username, password: password }),
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else if (response.status === 409) {
            return response.json().then((error) => {
              throw new Error(response.json());
            });
          } else if (response.status === 401) {
            return response.json().then((error) => {
              throw new Error(error.message);
            });
          } else {
            return response.json().then((error) => {
              throw new Error("Something wrong ! " + response.jso);
            });
          }
        })
        .then((data) => {
          alert(data.message);
          document.getElementById("loginLink").text = username;
          document.getElementById("loginLink").style.pointerEvents = "none";
          document.getElementById("loginSection").style.display = "none";
          document.getElementById("makeOrderSection").style.display = "block";
        })
        .catch(function (error) {
          alert("error: " + error.message);
        });
    });

  // place order function
};
