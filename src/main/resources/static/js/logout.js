document.addEventListener("DOMContentLoaded", function () {
    const logoutButton = document.querySelector("button[type='submit']");

    if (logoutButton) {
        logoutButton.addEventListener("click", async function () {
            try {
                const response = await fetch("/api/v1/auth/logout", {
                    method: "POST",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });

                if (response.ok) {
                    window.location.href = "/home";
                } else {
                    console.error("Ошибка выхода:", response.status);
                }
            } catch (error) {
                console.error("Ошибка сети:", error);
            }
        });
    }
});
