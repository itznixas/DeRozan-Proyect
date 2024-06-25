const addUser = document.getElementById('new-user-btn');

addUser.addEventListener('click', async (event) => {
    event.preventDefault(); // Prevenir el comportamiento por defecto del formulario

    const userName = document.getElementById('name').value;
    const lastName = document.getElementById('lastname').value;
    const userEmail = document.getElementById('email-user').value;
    const userRole = document.getElementById('select-rol').value;
    const userBirthday = document.getElementById('birth-date').value;
    const userPassword = document.getElementById('new-password').value;
    const messageElement = document.getElementById('message');

    const userData = {
        name: userName,
        lastName: lastName,
        email: userEmail,
        role: {
            id_role: parseInt(userRole)
        },
        birthDate: userBirthday,
        password: userPassword
    };

    try {
        const response = await axios.post('/api/users/register-admin', userData);
        console.log('User registration successful:', response.data); // Puedes manejar la respuesta según sea necesario
        messageElement.textContent = 'user saved successfully';
    } catch (error) {
        console.error('Error saving user:', error);
        messageElement.textContent = 'Error saving user: ' + error.message;
    }
});
