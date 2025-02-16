document.addEventListener("DOMContentLoaded", function() {
    fetch('/api/v1/favorites')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("flights-container");
            container.innerHTML = "";
            data.forEach(flight => {
                const card = document.createElement("div");
                card.classList.add("card");
                card.innerHTML = `
                    <h3>${flight.model} (${flight.flightNumber})</h3>
                    <p><strong>Откуда:</strong> ${flight.depIata} → <strong>Куда:</strong> ${flight.arrIata}</p>
                    <p><strong>Время вылета:</strong> ${flight.depTime}</p>
                    <p><strong>Время прилета:</strong> ${flight.arrTime}</p>
                    <p><strong>Фактический вылет:</strong> ${flight.depActual || '-'}</p>
                    <p><strong>Фактический прилет:</strong> ${flight.arrActual || '-'}</p>
                    <p><strong>Продолжительность:</strong> ${flight.duration}</p>
                    <p><strong>Статус:</strong> ${flight.status}</p>
                    <button class="delete-btn" onclick="removeFromFavorites('${flight.id}')">Удалить</button>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => console.error("Ошибка загрузки данных: ", error));
});

function removeFromFavorites(id) {
    fetch(`/api/v1/favorites/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            console.error("Ошибка удаления рейса");
        }
    })
    .catch(error => console.error("Ошибка: ", error));
}
