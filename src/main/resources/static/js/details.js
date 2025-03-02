document.addEventListener("DOMContentLoaded", function() {

    const params = new URLSearchParams(window.location.search);

    const id = document.getElementById("button-container").getAttribute("data-id");

    if (id === null) {
        document.getElementById("button-container").innerHTML =
            `<button class="btn btn-primary button" id="add-to-favorites">Добавить в избранное</button>`;

        document.getElementById("add-to-favorites").addEventListener("click", function() {
            let data = {};
            params.forEach((value, key) => {
                data[key] = value;
            });
            addToFavorites(data);
        });
    } else {
        document.getElementById("button-container").innerHTML =
            `<button class="btn btn-delete button" id="delete">Удалить</button>`;

        document.getElementById("delete").addEventListener("click", function() {
            removeFromFavorites(id);
        });
    }
});

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

function removeFromFavorites(id) {
    fetch(`/api/v1/favorites/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.status === 401) {
             window.location.href = '/account/sign-in';
             return;
        }
        if (response.ok) {
            location.href = "/favorites";
        } else {
            console.error('Ошибка при удаление:', response.statusText);
            alert('Произошла ошибка при удалении!');
        }
    })
    .catch(error => {
        console.error('Ошибка при удаление:', response.statusText);
        alert('Произошла ошибка при удалении!');
    });
}
