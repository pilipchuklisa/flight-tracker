document.addEventListener("DOMContentLoaded", async function () {
    try {
        const response = await fetch("/api/v1/favorites");
        if (!response.ok) throw new Error("Ошибка загрузки данных");

        const data = await response.json();
        displayFlights(data);
    } catch (error) {
        console.error("Ошибка: ", error);
    }
});

function displayFlights(flights) {
    const container = document.getElementById("card-group");
    container.innerHTML = "";

    flights.forEach(flight => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.setAttribute("data-id", flight.id);
        card.setAttribute("data-flight", JSON.stringify(flight));
        card.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">${flight.model} (${flight.flight_number})</h5>
                <span class="badge">${flight.status}</span>
                <p class="card-text">${flight.dep_iata} → ${flight.arr_iata}</p>
                <div class="section-container">
                    <div class="section">
                        <p class="card-text"><strong>Время вылета:</strong> ${flight.dep_time}</p>
                        <p class="card-text"><strong>Фактический вылет:</strong> ${flight.dep_actual || '-'}</p>
                    </div>
                    <div class="section">
                        <p class="card-text"><strong>Время прилета:</strong> ${flight.arr_time}</p>
                        <p class="card-text"><strong>Фактический прилет:</strong> ${flight.arr_actual || '-'}</p>
                    </div>
                </div>
                <button class="btn btn-delete flight-delete-btn"
                        data-id="${flight.id}">
                    Удалить
                </button>
            </div>
        `;
        container.appendChild(card);
    });

    document.querySelectorAll(".card").forEach(card => {
        card.addEventListener("click", function () {
            const flightData = this.getAttribute("data-flight");
            try {
                const flight = JSON.parse(flightData);
                viewFlightDetails(flight);
            } catch (e) {
                console.error("Ошибка парсинга данных рейса:", e);
            }
        });
    });

    document.querySelectorAll(".flight-delete-btn").forEach(button => {
        button.addEventListener("click", async function () {
            event.stopPropagation();
            await removeFromFavorites(this.getAttribute("data-id"));
        });
    });
}

function viewFlightDetails(flight) {
    const updatedFlight = Object.keys(flight).reduce((acc, key) => {
        acc[key] = flight[key] === null ? '' : flight[key];
        return acc;
    }, {});
    const params = new URLSearchParams(updatedFlight);
    window.location.href = `/flight-details?${params.toString()}`;
}

async function removeFromFavorites(id) {
    try {
        const response = await fetch(`/api/v1/favorites/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) throw new Error("Ошибка удаления рейса");

        document.querySelector(`.card[data-id="${id}"]`)?.remove();

    } catch (error) {
        console.error("Ошибка: ", error);
    }
}
