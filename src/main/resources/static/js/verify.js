document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const email = params.get('email');

    document.getElementById("verifyForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let formData = {
            email: email,
            verificationCode: document.getElementById("verificationCode").value
        };

        fetch("/api/v1/auth/verify?email=" + encodeURIComponent(formData.email) + "&code=" + encodeURIComponent(formData.verificationCode), {
            method: "GET",
            credentials: "include"
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error("Ошибка подтверждения");
        })
        .then(data => {
            console.log(data);
            window.location.href = "/account/sign-in";
        })
        .catch(error => {
            console.error("Ошибка:", error);
            alert("Ошибка подтверждения. Попробуйте снова.");
        });
    });
});