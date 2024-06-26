const searchUser = document.getElementById('search-user');
const deleteUser = document.getElementById('delete-user');
const idUser = document.getElementById('userId');
const messageElement = document.getElementById('message');

searchUser.addEventListener('click', async (event) => {
    event.preventDefault();

    const id = document.getElementById('userId').value;

    try {
        const response = await axios.get(`/api/users/get-user/${id}`);
        const userData = response.data;

        document.getElementById('email').value = userData.email;

        document.getElementById('user-details').style.display = 'block';
    } catch (error) {
        console.error('Error fetching user data', error);
    }
});

deleteUser.addEventListener('click', async (event) => {
    event.preventDefault();

    const id = idUser.value;

    if (!id) {
        messageElement.textContent = 'Please enter a user ID';
        return;
    }

    try{
        const response = await axios.delete(`/api/users/delete-user/${id}`);
        if (response.status === 204) {
            messageElement.textContent = 'User deleted successfully';
        } else {
            messageElement.textContent = 'Failed to delete user';
        }
    } catch (error) {
        console.error('Error deleting user: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'user not found';
        } else {
            messageElement.textContent = 'Error deleting user: ' + error.message;
        }
    }
});
