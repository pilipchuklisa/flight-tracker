document.addEventListener("DOMContentLoaded", function() {

    const params = new URLSearchParams(window.location.search);

    document.getElementById("add-to-favorites").addEventListener("click", function() {
        let data = {};
        params.forEach((value, key) => {
            data[key] = value;
        });
        addToFavorites(data);
    });
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
