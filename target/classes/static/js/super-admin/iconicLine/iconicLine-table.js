document.addEventListener("DOMContentLoaded", function() {
    const tableBody = document.querySelector('.table-data .order tbody');

    function updateTable(data) {
        tableBody.innerHTML = '';  // limpiar las filas
        data.forEach(iconicLine => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <p>${iconicLine.id_IconicLine}</p>
                </td>
                <td>${iconicLine.name}</td>
                <td>${iconicLine.brand ? iconicLine.brand.name : ''}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function fetchSneakers() {
        axios.get('/api/iconic-lines/get-all-iconicLines')
            .then(response => {
                updateTable(response.data);
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    fetchSneakers();

    setInterval(fetchSneakers, 5000);
});