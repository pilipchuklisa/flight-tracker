document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("registerForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let formData = {
            username: document.getElementById("username").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        };

        fetch("/api/v1/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData),
            credentials: "include"
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("Ошибка регистрации");
        })
        .then(data => {
            console.log("Пользователь зарегистрирован:", data);
            window.location.href = "/account/verify?email=" + encodeURIComponent(formData.email);
        })
        .catch(error => {
            console.error("Ошибка:", error);
            alert("Ошибка регистрации. Попробуйте снова.");
        });
    });
});