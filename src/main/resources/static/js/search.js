document.addEventListener("DOMContentLoaded", function() {

    const urlParams = new URLSearchParams(window.location.search);

    if (history.state && history.state.fromDetails) {
        const savedResults = sessionStorage.getItem("lastSearchResults");

        if (savedResults) {
            displayFlights(JSON.parse(savedResults));
        }

        history.replaceState(null, "");
    } else {
        sessionStorage.removeItem("lastSearchResults");
    }

    const flightNumber = urlParams.get("flight_number") || "";
    const depIata = urlParams.get("dep_iata") || "";
    const arrIata = urlParams.get("arr_iata") || "";
    const depTime = urlParams.get("dep_time") || "";

    if (flightNumber) document.getElementById("flightNumber").value = flightNumber;
    if (depIata) document.getElementById("depIata").value = depIata;
    if (arrIata) document.getElementById("arrIata").value = arrIata;
    if (depTime) document.getElementById("depTime").value = depTime;

    document.getElementById("searchForm").addEventListener("submit", function(event) {
        event.preventDefault();

        const params = new URLSearchParams(new FormData(this)).toString();

        sessionStorage.removeItem("lastSearchResults");

        fetch(`/api/v1/flights?${params}`)
            .then(response => response.json())
            .then(data => {
                saveSearchHistory(params);
                sessionStorage.setItem("lastSearchResults", JSON.stringify(data));
                displayFlights(data);
            })
            .catch(error => {
                console.error('Ошибка при выполнении запроса:', error);
                alert('Произошла ошибка при поиске!');
            });
    });
});

function displayFlights(flights) {
    const container = document.getElementById("card-group");
    container.innerHTML = "";

    if (flights.length === 0) {
        container.innerHTML = `<p class="no-results">Ничего не найдено</p>`;
        return;
    }

    flights.forEach(flight => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.setAttribute("data-id", flight.id);
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
                <button class="btn btn-primary add-to-favorite-btn"
                        data-flight="${encodeURIComponent(JSON.stringify(flight))}">
                    Добавить в избранное
                </button>
            </div>
        `;
        container.appendChild(card);
    });

    document.querySelectorAll(".card").forEach(card => {
        card.addEventListener("click", function () {
            const flightData = this.querySelector(".add-to-favorite-btn").getAttribute("data-flight");
            try {
                const flight = JSON.parse(decodeURIComponent(flightData));
                viewFlightDetails(flight);
            } catch (e) {
                console.error("Ошибка парсинга данных рейса:", e);
            }
        });
    });

    document.querySelectorAll(".add-to-favorite-btn").forEach(button => {
        button.addEventListener("click", function (event) {
            event.stopPropagation();
            const flightData = this.getAttribute("data-flight");

            try {
                const flight = JSON.parse(decodeURIComponent(flightData));
                addToFavorites(flight);
            } catch (e) {
                console.error("Ошибка парсинга данных рейса:", e);
            }
        });
    });
}

function addToFavorites(data) {
    const updateData = Object.keys(data).reduce((acc, key) => {
        acc[key] = data[key] === null ? '' : data[key];
        return acc;
    }, {});

    fetch('/api/v1/favorites', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateData)
    })
    .then(response => {
        if (response.status === 401) {
             window.location.href = '/account/sign-in';
             return;
        }
        if (response.ok) {
            alert('Рейс добавлен в избранное!');
        } else {
            console.error('Ошибка при добавлении в избранное:', response.statusText);
            alert('Произошла ошибка при добавлении в избранное!');
        }
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
        alert('Произошла ошибка при добавлении в избранное!');
    });
}

function viewFlightDetails(flight) {
    const updatedFlight = Object.keys(flight).reduce((acc, key) => {
        acc[key] = flight[key] === null ? '' : flight[key];
        return acc;
    }, {});

    history.pushState({ fromDetails: true }, "");

    sessionStorage.setItem("lastSearchResults", sessionStorage.getItem("lastSearchResults") || "[]");

    const params = new URLSearchParams(updatedFlight);
    window.location.href = `/flight-details?${params.toString()}`;
}

function saveSearchHistory(params) {
    fetch("/api/v1/histories", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify(Object.fromEntries(new URLSearchParams(params))),
    })
    .catch(error => console.error("Ошибка сохранения истории:", error));
}
