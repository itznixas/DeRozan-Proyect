document.addEventListener("DOMContentLoaded", function() {
    const tableBody = document.querySelector('.table-data .order tbody');

    function updateTable(data) {
        tableBody.innerHTML = '';  // limpiar las filas
        data.forEach(sneaker => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <img src="/img/${sneaker.image}" alt="Sneaker Image">
                    <p>${sneaker.name}</p>
                </td>
                <td>${sneaker.description}</td>
                <td>${sneaker.size}</td>
                <td>${sneaker.color}</td>
                <td>${sneaker.category}</td>
                <td>${sneaker.amount}</td>
                <td>${new Date(sneaker.registration).toLocaleDateString()}</td>
                <td>${sneaker.status}</td>
                <td>${sneaker.brand ? sneaker.brand.name : ''}</td>
                <td>${sneaker.iconicLine ? sneaker.iconicLine.name : ''}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function fetchSneakers() {
        axios.get('/api/sneakers/get-all-sneakers')
            .then(response => {
                updateTable(response.data);
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    fetchSneakers();

    setInterval(fetchSneakers, 5000);
});