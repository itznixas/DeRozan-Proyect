document.addEventListener("DOMContentLoaded", function() {
    const tableBody = document.querySelector('.table-data .order tbody');

    function updateTable(data) {
        tableBody.innerHTML = '';  // limpiar las filas
        data.forEach(brand => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <p>${brand.id_brand}</p>
                </td>
                <td>${brand.name}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function fetchSneakers() {
        axios.get('/api/brands/get-all-brands')
            .then(response => {
                updateTable(response.data);
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    fetchSneakers();

    setInterval(fetchSneakers, 5000);
});