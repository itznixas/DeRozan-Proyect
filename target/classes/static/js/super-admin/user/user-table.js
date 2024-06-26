document.addEventListener("DOMContentLoaded", function() {
    const tableBody = document.querySelector('.table-data .order tbody');

    function updateTable(data) {
        tableBody.innerHTML = '';  // limpiar las filas
        data.forEach(users => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <p>${users.id}</p>
                </td>
                <td>${users.email}</td>
                <td>${users.status}</td>
                <td>${users.role ? users.role.name : ''}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function fetchSneakers() {
        axios.get('/api/users/get-all-users')
            .then(response => {
                updateTable(response.data);
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    fetchSneakers();

    setInterval(fetchSneakers, 5000);
});