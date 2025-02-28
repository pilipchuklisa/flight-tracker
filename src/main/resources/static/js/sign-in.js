document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let formData = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        };

        fetch("/api/v1/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData),
            credentials: "include"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Ошибка аутентификации");
            }
            return response.json();
        })
        .then(data => {
            console.log("Аутентификация успешна:", data);
            window.location.href = "/home";
        })
        .catch(error => {
            console.error("Ошибка:", error);
            alert("Ошибка аутентификации. Попробуйте снова.");
        });
    });
});
