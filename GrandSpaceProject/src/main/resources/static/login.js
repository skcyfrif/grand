document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the default form submission

    // Get form data
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Create the payload
    const payload = {
        email: email,
        password: password,
    };

    console.log("Submitting login form with payload:", payload); // Debug: Check the payload

    // Send the POST request to the API
    fetch("${API_BASE_URL}/api/login", {
    // fetch("http://localhost:9090/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
    })
        .then((response) => {
            console.log("Response Status:", response.status); // Debug: Log the response status
            if (response.ok) {
                return response.json(); // Parse the JSON response
            } else {
                throw new Error("Invalid login credentials, status code: " + response.status);
            }
        })
        .then((data) => {
            console.log("API Response Data:", data); // Debug: Log the response data

            const jwtToken = data["jwt-token"]; // Assuming the token key is 'jwt-token'
            if (!jwtToken) {
                throw new Error("JWT token is missing from the response");
            }

            // Store the token in session storage
            sessionStorage.setItem("authToken", jwtToken);

            // Decode the token to extract the payload
            const base64Payload = jwtToken.split(".")[1]; // Get the payload part of the token
            const decodedPayload = JSON.parse(atob(base64Payload)); // Decode Base64 to JSON
            console.log("Decoded Payload:", decodedPayload);

            // Extract the role and userId from the payload
            const userRole = decodedPayload.role; // Assuming 'role' is the claim key for user roles
            const userId = decodedPayload.id; // Assuming 'id' is the claim key for user ID

            console.log("User Role:", userRole);
            console.log("User ID:", userId);

            // Store the role and user ID in session storage
            sessionStorage.setItem("userRole", userRole);
            sessionStorage.setItem("userId", userId); // Save the user ID

            // Show success alert
            showFlashMessage("Login Successful!");

            // Display the modal after flash message
            setTimeout(() => {
                const modal = new bootstrap.Modal(document.getElementById("multiPageModal"), {
                    keyboard: false,
                });
                modal.show();

                // Redirect after the modal is closed
                document
                    .getElementById("multiPageModal")
                    .addEventListener("hidden.bs.modal", () => {
                        redirectToDashboard(userRole);
                    });
            }, 2000); // Delay modal display by 2 seconds
        })
        .catch((error) => {
            console.error("Error during login:", error); // Debug: Log the error
            alert("Please Provide Valid Email or Password");
        });
});
function showFlashMessage(message) {
    const flashMessage = document.createElement("div");
    flashMessage.className = "alert alert-success position-fixed top-0 start-50 translate-middle-x mt-3";
    flashMessage.style.zIndex = 1055; // Ensure it appears above other elements
    flashMessage.innerText = message;

    document.body.appendChild(flashMessage);

    // Remove the flash message after 2 seconds
    setTimeout(() => {
        flashMessage.remove();
    }, 2000);
}

    document.body.appendChild(flashMessage);

    // Remove the flash message after 2 seconds
    setTimeout(() => {
        flashMessage.remove();
    }, 2000);

// Redirect based on user role
function redirectToDashboard(userRole) {
    console.log("Redirecting based on user role:", userRole); // Debug: Log the user role
    if (userRole === "CLIENT") {
        window.location.href = "Dashbord/xhtml/client-dashboard.html";
    } else if (userRole === "MANAGER") {
        window.location.href = "Dashbord/xhtml/manegerdashbord.html";
    } else if (userRole === "ADMIN") {
        window.location.href = "Dashbord/xhtml/index2.html";
    } else {
        alert("Invalid user role. Please contact support.");
    }
}



///Changes Here
// fetch("http://88.222.241.45:9080/api/login", {
//     method: "POST",
//     headers: {
//         "Content-Type": "application/json",
//     },
//     body: JSON.stringify(payload),
// })
// window.location.href = "Dashbord/xhtml/manegerdashbord";