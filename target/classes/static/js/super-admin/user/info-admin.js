document.addEventListener("DOMContentLoaded", function () {
    const userId = localStorage.getItem("userId");

    if (userId) {
        fetchUserInfo(userId); // Obtener la información del usuario al cargar la página
    } else {
        console.error('No se encontró userId en localStorage.');
    }

    document.getElementById('editSAbtn').addEventListener('click', async function() {
        try {
            const userId = localStorage.getItem('userId');
            const response = await axios.get(`/api/users/get-user/${userId}`);

            if (response.status === 200) {
                const userData = response.data;
                console.log(userData); // Verifica la estructura de userData en la consola
                document.getElementById('name').value = userData.name || '';
                document.getElementById('lastName').value = userData.lastName || '';
                document.getElementById('email').value = userData.email || '';
                document.getElementById('birthDate').value = userData.birthDate || '';
                document.getElementById('dni').value = userData.dni || '';

                mostrarContenido('settings'); // Mostrar formulario de edición
            } else {
                console.error('Error al obtener los datos del usuario');
            }
        } catch (error) {
            console.error('Error en la solicitud HTTP:', error);
        }
    });

    document.getElementById('update-user').addEventListener('click', async function() {
        const userId = localStorage.getItem('userId');
        const name = document.getElementById('name').value;
        const lastname = document.getElementById('lastName').value;
        const email = document.getElementById('email').value;
        const birthdate = document.getElementById('birthDate').value;
        const dni = document.getElementById('dni').value;

        try {
            const response = await axios.post(`/api/users/update-user/${userId}`, {
                name: name,
                lastName: lastname,
                email: email,
                birthDate: birthdate,
                dni: dni
            });

            if (response.status === 200) {
                console.log('Usuario actualizado correctamente');
                fetchUserInfo(userId); // Actualizar la información mostrada
                mostrarContenido('opcion1'); // Mostrar perfil nuevamente
            } else {
                console.error('Error al actualizar el usuario');
            }
        } catch (error) {
            console.error('Error en la solicitud HTTP:', error);
        }
    });
});

function fetchUserInfo(userId) {
    axios.get(`/api/users/get-user/${userId}`)
        .then(response => {
            const userData = response.data;
            console.log(userData); // Verifica la estructura de userData en la consola
            document.getElementById('user-name').textContent = userData.name || '';
            document.getElementById('user-lastName').textContent = userData.lastName || '';
            document.getElementById('user-email').textContent = userData.email || '';
            document.getElementById('user-birthDate').textContent = userData.birthDate || '';

        })
        .catch(error => {
            console.error('Error al obtener información del usuario:', error);
        });
}