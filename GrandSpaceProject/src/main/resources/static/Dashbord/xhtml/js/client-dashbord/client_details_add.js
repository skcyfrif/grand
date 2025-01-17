// // URL to register client
// // const registerUrl = "http://88.222.241.45:9080/api/register";
// const registerUrl = "http://localhost:9090/api/register";

// // Function to handle form submission
// async function registerClient(event) {
//     event.preventDefault(); // Prevent default form submission

//     // Get form data
//     const formData = {
//         firstName: document.getElementById("firstName").value.trim(),
//         lastName: document.getElementById("lastName").value.trim(),
//         email: document.getElementById("email").value.trim(),
//         phone: document.getElementById("phone").value.trim(),
//         password: document.getElementById("password").value.trim(),
//         state: document.getElementById("state").value.trim(),
//         city: document.getElementById("city").value.trim(),
//         currentAddress: document.getElementById("currentAddress").value.trim(),
//     };

//     try {
//         // Send POST request to the server
//         const response = await fetch(registerUrl, {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify(formData),
//         });

//         // Check if the response is successful
//         if (!response.ok) {
//             const errorData = await response.json();
//             throw new Error(errorData.message || "Failed to register client.");
//         }

//         // Handle successful response
//         const result = await response.json();
//         alert("Registration successful! Client ID: " + result.id);

//         // Optionally reset the form
//         document.getElementById("registrationForm").reset();
//     } catch (error) {
//         // Handle errors
//         console.error("Error:", error.message);
//         alert("Error: " + error.message);
//     }
// }

// // Attach event listener to the form
// document.getElementById("registrationForm").addEventListener("submit", registerClient);


// // Add event listener to the Cancel button
// document.getElementById("cancelButton").addEventListener("click", function () {
//     // Redirect to the clidetails page
//     window.location.href = "client_details.html";
// });
