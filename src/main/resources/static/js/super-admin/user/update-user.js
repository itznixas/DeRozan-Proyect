const searchUserBtn = document.getElementById('search-user');
const updateUserForm = document.getElementById('update-user-form');

searchUserBtn.addEventListener('click', async (event) => {
    event.preventDefault();

    const id = document.getElementById('userId').value;

    try {
        const response = await axios.get(`/api/users/get-user/${id}`);
        const userData = response.data;

        document.getElementById('name').value = userData.name;
        document.getElementById('lastName').value = userData.lastName;
        document.getElementById('dni').value = userData.dni;
        document.getElementById('email').value = userData.email;
        document.getElementById('birthDate').value = userData.birthDate;
        document.getElementById('password').value = userData.password;
        document.getElementById('status').value = userData.status;
        document.getElementById('role').value = userData.role.id_role;

        document.getElementById('user-details').style.display = 'block';
    } catch (error) {
        console.error('Error fetching user data', error);
    }
});

updateUserForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const userId = document.getElementById('userId').value;
    const userName = document.getElementById('name').value;
    const lastName = document.getElementById('lastName').value;
    const dni = document.getElementById('dni').value;
    const email = document.getElementById('email').value;
    const birthDate = document.getElementById('birthDate').value;
    const password = document.getElementById('password').value;
    const status = document.getElementById('status').value;
    const roleId = document.getElementById('role').value;

    const userData = {
        id: userId,
        name: userName,
        lastName: lastName,
        dni: dni,
        email: email,
        birthDate: birthDate,
        password: password,
        status: status,
        role: {
            id_role: roleId
        }
    };

    try {
        const response = await axios.post(`/api/users/update-user/${userId}`, userData);
        console.log('User updated successfully:', response.data);
        document.getElementById('message').innerText = 'User updated successfully';

    } catch (error) {
        console.error('Error updating user', error);
        document.getElementById('message').innerText = 'Error updating user. Please try again.';
    }
});
