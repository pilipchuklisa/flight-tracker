document.addEventListener("DOMContentLoaded", async () => {
    try {
        const params = new URLSearchParams(window.location.search);

        const flight = {};
        params.forEach((value, key) => {
          flight[key] = value;
        });

        setButton(flight);
        updateFlightDetails(flight);
    } catch (error) {
        console.error("Ошибка загрузки данных о рейсе:", error);
    }
});

function setButton(flight) {
    const buttonContainer = document.getElementById("button-container");

    if (flight.id) {
        buttonContainer.innerHTML = '<button class="btn btn-delete button" id="delete">Удалить</button>';
        document.getElementById("delete").addEventListener("click", async () => {
            await removeFromFavorites(flight.id);
        });
    } else {
        buttonContainer.innerHTML =
            '<button class="btn btn-primary button" id="add-to-favorites">Добавить в избранное</button>';
        document.getElementById("add-to-favorites").addEventListener("click", async () => {
            await addToFavorites(flight);
        });
    }
}

function updateFlightDetails(flight) {
    document.getElementById("flight-model").textContent = flight.model;
    document.getElementById("flight-number").textContent = flight.flight_number;
    document.getElementById("flight-status").textContent = flight.status;
    document.getElementById("flight-duration").textContent = flight.duration;
    document.getElementById("dep-country").textContent = flight.dep_country;
    document.getElementById("dep-city").textContent = flight.dep_city;
    document.getElementById("dep-name").textContent = flight.dep_name;
    document.getElementById("dep-iata").textContent = flight.dep_iata;
    document.getElementById("arr-country").textContent = flight.arr_country;
    document.getElementById("arr-city").textContent = flight.arr_city;
    document.getElementById("arr-name").textContent = flight.arr_name;
    document.getElementById("arr-iata").textContent = flight.arr_iata;

    setTimeField("dep-time-local", flight.dep_time_utc, flight.dep_actual_utc);
    setTimeField("dep-time", flight.dep_time, flight.dep_actual, flight.dep_time_zone);
    setDelayField("dep-delay", flight.dep_delayed, "Задержка вылета");

    setTimeField("arr-time-local", flight.arr_time_utc, flight.arr_actual_utc);
    setTimeField("arr-time", flight.arr_time, flight.arr_actual, flight.arr_time_zone);
    setDelayField("arr-delay", flight.arr_delayed, "Задержка посадки");
}

function setTimeField(elementId, scheduled, actual, timeZone) {
    const element = document.getElementById(elementId);

    if (!timeZone) {
        timeZone = moment.tz.guess();
        scheduled = moment.utc(new Date(scheduled)).tz(timeZone).format('MM/DD/YYYY HH:mm');
        if (actual) {
            actual = moment.utc(new Date(actual).toISOString()).tz(timeZone).format('MM/DD/YYYY HH:mm');
        }
    }

    scheduled = moment(scheduled).format('MM/DD/YYYY HH:mm');
    if (actual) {
        actual = moment(actual).format('MM/DD/YYYY HH:mm');
    }

    element.innerHTML = actual ?
        `<span>${timeZone}</span> <s>${scheduled}</s> <span>${actual}</span>` :
        `<span>${timeZone}</span> <span>${scheduled}</span>`;
}

function setDelayField(elementId, delay, label) {
    const element = document.getElementById(elementId);
    element.innerHTML = delay ? `<strong>${label}:</strong> ${delay} минут` : "";
}

async function addToFavorites(data) {
    try {
        const updateData = Object.keys(data).reduce((acc, key) => {
            acc[key] = data[key] === null ? '' : data[key];
            return acc;
        }, {});

        const response = await fetch('/api/v1/favorites', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updateData)
        });

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
    } catch (error) {
        console.error('Ошибка при выполнении запроса:', error);
        alert('Произошла ошибка при добавлении в избранное!');
    }
}

async function removeFromFavorites(id) {
    try {
        const response = await fetch(`/api/v1/favorites/${id}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' }
        });

        if (response.status === 401) {
            window.location.href = '/account/sign-in';
            return;
        }
        if (response.ok) {
            location.href = "/favorites";
        } else {
            console.error('Ошибка при удалении:', response.statusText);
            alert('Произошла ошибка при удалении!');
        }
    } catch (error) {
        console.error('Ошибка при удалении:', error);
        alert('Произошла ошибка при удалении!');
    }
}
