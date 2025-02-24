document.addEventListener("DOMContentLoaded", function() {

    const urlParams = new URLSearchParams(window.location.search);

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

        fetch(`/api/v1/flights?${params}`)
            .then(response => response.json())
            .then(data => {
                saveSearchHistory(params);
                displayFlights(data);
            })
            .catch(error => {
                console.error('Ошибка при выполнении запроса:', error);
                alert('Произошла ошибка при поиске!');
            });
    });
});

function displayFlights(flights) {
    const container = document.getElementById("flightsContainer");
    container.innerHTML = '';

    flights.forEach(flight => {
        const flightCard = document.createElement("div");
        flightCard.classList.add("flight-card");

        flightCard.innerHTML = `
            <h3>${flight.model} (${flight.flight_number})</h3>
            <p><strong>Откуда:</strong> ${flight.dep_iata} → <strong>Куда:</strong> ${flight.arr_iata}</p>
            <p><strong>Время вылета:</strong> ${flight.dep_time}</p>
            <p><strong>Время прилета:</strong> ${flight.arr_time}</p>
            <p><strong>Фактический вылет:</strong> ${flight.dep_actual || '-'}</p>
            <p><strong>Фактический прилет:</strong> ${flight.arr_actual || '-'}</p>
            <p><strong>Продолжительность:</strong> ${flight.duration}</p>
            <p><strong>Статус:</strong> ${flight.status}</p>
            <button class="add-to-favorites-btn">Добавить в избранное</button>
        `;

        flightCard.addEventListener('click', function() {
            viewFlightDetails(flight);
        });

        const button = flightCard.querySelector(".add-to-favorites-btn");
        button.addEventListener('click', function() {
            event.stopPropagation();
            addToFavorites(flight);
        });

        container.appendChild(flightCard);
    });
}

function addToFavorites(data) {
    let jsonData;
    if (typeof data === 'string') {
        try {
            jsonData = JSON.parse(data);
        } catch (e) {
            console.error('Invalid JSON string:', e);
            return;
        }
    } else {
        jsonData = data;
    }

    const updateData = Object.keys(jsonData).reduce((acc, key) => {
        acc[key] = jsonData[key] === null ? '' : jsonData[key];
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
