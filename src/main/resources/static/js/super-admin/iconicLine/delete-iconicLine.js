const searchIconicLine = document.getElementById('search-iconic');
const deleteIconicLine = document.getElementById('delete-iconic');
const idIconicLine = document.getElementById('id-iconic');
const nameIconicLine = document.getElementById('name-iconic');
const messageElement = document.getElementById('message');

searchIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if (!id) {
        messageElement.textContent = 'Please enter a product ID';
        return;
    }

    try{
        const response = await axios.get(`/api/iconic-lines/get-iconic-line/${id}`);
        if (response.status === 200) {
            const iconicLine = response.data;
            nameIconicLine.value = iconicLine.name;
        } else {
            messageElement.textContent = 'Iconic Line not found';
        }

    }catch (error) {
        console.error('Error fetching Iconic Line: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Iconic Line not found';
        } else {
            messageElement.textContent = 'Error fetching Iconic Line: ' + error.message;
        }
    }
});

deleteIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if (!id) {
        messageElement.textContent = 'Please enter a product ID';
        return;
    }

    try{
        const response = await axios.delete(`/api/iconic-lines/delete-iconic-line/${id}`);
        if (response.status === 204) {
            messageElement.textContent = 'Iconic Line deleted successfully';
        } else {
            messageElement.textContent = 'Failed to delete Iconic Line';
        }
    } catch (error) {
        console.error('Error deleting Iconic Line: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Iconic Line not found';
        } else {
            messageElement.textContent = 'Error deleting Iconic Line: ' + error.message;
        }
    }
});