// URL to register client
const registerUrl = "${API_BASE_URL}/api/manager/register";
// const registerUrl = "http://localhost:9090/api/manager/register";

// Function to handle form submission
async function registerManager(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form data
    const formData = {
        firstName: document.getElementById("firstName").value.trim(),
        lastName: document.getElementById("lastName").value.trim(),
        email: document.getElementById("email").value.trim(),
        phone: document.getElementById("phone").value.trim(),
        password: document.getElementById("password").value.trim(),
        state: document.getElementById("state").value.trim(),
        city: document.getElementById("city").value.trim(),
        experience: document.getElementById("experience").value.trim(),
        qualifications: document.getElementById("qualifications").value.trim(),
    };

    try {
        // Send POST request to the server
        const response = await fetch(registerUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        });

        // Check if the response is successful
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Failed to register manager.");
        }

        // Handle successful response
        const result = await response.json();
        alert("Registration successful! Manager ID: " + result.id);

        // Optionally reset the form
        document.getElementById("registrationForm").reset();
    } catch (error) {
        // Handle errors
        console.error("Error:", error.message);
        alert("Error: " + error.message);
    }
}

// Attach event listener to the form
document.getElementById("registrationForm").addEventListener("submit", registerManager);


// Add event listener to the Cancel button
document.getElementById("cancelButton").addEventListener("click", function () {
    // Redirect to the clidetails page
    window.location.href = "maneger.html";
});
