document.addEventListener("DOMContentLoaded", function() {
    checkHistory();

    document.getElementById("delete-all-history").addEventListener("click", function() {
        fetch("/api/v1/histories", { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    document.getElementById("history-list").innerHTML = "";
                    checkHistory();
                }
            })
            .catch(error => console.error("Ошибка при удалении истории:", error));
    });
});

function removeFromHistory(id) {
    fetch(`/api/v1/histories/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                document.querySelector(`button[onclick="removeFromHistory('${id}')"]`).parentElement.remove();
                checkHistory();
            }
        })
        .catch(error => console.error("Ошибка при удалении элемента истории:", error));
}

function checkHistory() {
    let historyContainer = document.getElementById("history-container");
    let historyList = document.getElementById("history-list");
    let noHistoryMessage = document.getElementById("no-history");
    let deleteAllButton = document.getElementById("delete-all-history");

    if (historyList.children.length === 0) {
        noHistoryMessage.style.display = "block";
        deleteAllButton.style.display = "none";
    } else {
        noHistoryMessage.style.display = "none";
        deleteAllButton.style.display = "block";
    }
}