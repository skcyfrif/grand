document.getElementById("submitBtn").addEventListener("click", function () {
    const formData = new FormData(document.getElementById("registrationForm"));
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    fetch(`${API_BASE_URL}/api/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
    .then((response) => {
        if (response.ok) {
            return response.json(); // Parse the response JSON
        } else {
            throw new Error("Registration failed!");
        }
    })
    .then((responseData) => {
        console.log("Response Data:", responseData);
        showFlashMessage("Registration successful! Redirecting to sign-in page...", "success");
        
        // Redirect to sign-in page after 2 seconds
        setTimeout(() => {
            window.location.href = "gs-login.html";
        }, 2000);
    })
    .catch((error) => {
        console.error("Error:", error);
        showFlashMessage("An error occurred during registration!", "error");
    });
});

function showFlashMessage(message, type) {
    const flashMessage = document.getElementById("flashMessage");
    flashMessage.textContent = message;
    flashMessage.className = `flash-message ${type === "error" ? "error" : ""}`;
    flashMessage.style.display = "block";

    // Hide the message after 3 seconds
    setTimeout(() => {
        flashMessage.style.display = "none";
    }, 3000);
}
