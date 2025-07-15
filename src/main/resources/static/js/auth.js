// ✅ LOGIN HANDLER
const loginForm = document.getElementById("loginForm");
if (loginForm) {
  loginForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password })
    })
      .then(response => {
        if (!response.ok) throw new Error("Invalid credentials");
        return response.json();
      })
      .then(data => {
        localStorage.setItem("token", data.token);
        document.getElementById("message").textContent = "Login successful!";
        window.location.href = "index.html";
      })
      .catch(error => {
        document.getElementById("message").textContent = error.message;
      });
  });
}

// ✅ REGISTRATION HANDLER
const registerForm = document.getElementById("registerForm");
if (registerForm) {
  registerForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password })
    })
      .then(response => response.text())
      .then(message => {
        document.getElementById("message").textContent = message;
        if (message.includes("success")) {
          setTimeout(() => window.location.href = "login.html", 1000);
        }
      })
      .catch(() => {
        document.getElementById("message").textContent = "Registration failed.";
      });
  });
}
