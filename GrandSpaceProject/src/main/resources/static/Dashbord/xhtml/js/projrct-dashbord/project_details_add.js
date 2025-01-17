document.addEventListener("DOMContentLoaded", function () {
    // Retrieve userId (clientId) from sessionStorage
    const userId = sessionStorage.getItem("userId");

    if (!userId) {
        // If no userId is found in sessionStorage, exit (you can handle this case if needed)
        return;
    }

    // Check if the form has already been submitted by checking localStorage for the current userId
    const formSubmitted = localStorage.getItem(`formSubmitted_${userId}`);

    // If the form has already been submitted, skip showing the modal
    if (formSubmitted) {
        return; // Exit if form has been submitted
    }

    // Set the clientId (userId) in the hidden input field
    document.getElementById("clientId").value = userId;

    // Initialize Bootstrap modal
    const mainModalElement = document.getElementById("mainModal");
    const mainModal = new bootstrap.Modal(mainModalElement);

    // Show the modal after 1 second (1000 milliseconds)
    setTimeout(() => {
        mainModal.show();
    }, 1000);

    // Handle form submission
    document.querySelector(".next-btn").addEventListener("click", async function (event) {
        event.preventDefault(); // Prevent default form submission behavior

        // Get form data
        const formData = {
            name: document.getElementById("name").value.trim(),
            description: document.getElementById("description").value.trim(),
            areaInSquareFeet: document.getElementById("areaInSquareFeet").value.trim(),
            budget: document.getElementById("budget").value.trim(),
            clientId: document.getElementById("clientId").value.trim(), // clientId from hidden input
        };

        try {
            // Send POST request to the server
            // const response = await fetch("http://localhost:9090/api/project/submit", {
            const response = await fetch("http://88.222.241.45:6070/api/project/submit", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            // Check if the response is successful
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Failed to submit project details.");
            }

            // Handle successful response
            const result = await response.json();
            alert("Project submitted successfully! Project ID: " + result.id);

            // Hide the modal after submission
            mainModal.hide();

            // Store the flag in localStorage to prevent showing the modal again for this user
            localStorage.setItem(`formSubmitted_${userId}`, "true");

            // Optionally, redirect the user to another page after submission
            // window.location.href = "your-redirect-page.html";

        } catch (error) {
            // Handle errors
            console.error("Error:", error.message);
            alert("Error: " + error.message);
        }
    });
});
