document.addEventListener("DOMContentLoaded", async function () {
    await loadSearchHistory();

    document.querySelector(".history-delete-all-btn").addEventListener("click", async function () {
        try {
            const response = await fetch("/api/v1/histories", { method: "DELETE" });
            if (!response.ok) throw new Error("Ошибка удаления истории");

            document.getElementById("history-container").innerHTML = "";
            checkHistory();
        } catch (error) {
            console.error("Ошибка при удалении истории:", error);
        }
    });
});

async function loadSearchHistory() {
    try {
        const response = await fetch("/api/v1/histories");
        if (!response.ok) throw new Error("Ошибка загрузки истории поиска");

        const data = await response.json();
        displayHistory(data);
    } catch (error) {
        console.error("Ошибка при загрузке истории поиска:", error);
    }
}

function displayHistory(histories) {
    const container = document.getElementById("history-container");
    container.innerHTML = "";

    histories.forEach(history => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.setAttribute("data-id", history.id);
        card.setAttribute("data-search-history", JSON.stringify(history));

        card.innerHTML = `
            <div class="card-body">
                <p>${history.dep_iata} → ${history.arr_iata}</p>
                <p><strong>Номер рейса:</strong> ${history.flight_number}</p>
                <p><strong>Дата:</strong> ${history.dep_time}</p>
                <button class="btn btn-delete history-delete-btn"
                        onclick="event.stopPropagation(); removeFromHistory('${history.id}')">
                    Удалить
                </button>
            </div>
        `;

        card.addEventListener("click", function () {
            const historyData = this.getAttribute("data-search-history");
            try {
                const history = JSON.parse(historyData);
                redirectToSearch(history);
            } catch (e) {
                console.error("Ошибка парсинга данных рейса:", e);
            }
        });
        container.appendChild(card);
    });

    checkHistory();
}

async function removeFromHistory(id) {
    try {
        const response = await fetch(`/api/v1/histories/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Ошибка удаления записи");

        document.querySelector(`.card[data-id="${id}"]`)?.remove();
        checkHistory();
    } catch (error) {
        console.error("Ошибка при удалении элемента истории:", error);
    }
}

function checkHistory() {
    const historyList = document.getElementById("history-container");
    const noHistoryMessage = document.getElementById("no-history");
    const deleteAllButton = document.querySelector(".history-delete-all-btn");

    if (historyList.children.length === 0) {
        noHistoryMessage.style.display = "block";
        deleteAllButton.style.display = "none";
    } else {
        noHistoryMessage.style.display = "none";
        deleteAllButton.style.display = "block";
    }
}

function redirectToSearch(history) {
    const params = new URLSearchParams(history);
    window.location.href = `/search?${params.toString()}`;
}
