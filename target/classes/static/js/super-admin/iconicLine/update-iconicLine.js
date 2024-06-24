const searchIconicLine = document.getElementById('search-iconic');
const updateIconicLine = document.getElementById('update-iconic');
const idIconicLine = document.getElementById('id-iconic');
const nameIconicLine = document.getElementById('name-iconicLine');
const brandIconicLine = document.getElementById('brand-iconicLine');
const messageElement = document.getElementById('message');

searchIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if(id == null) {
        messageElement.textContent = 'Please enter a Iconic Line ID';
        return;
    }

    try {
        const response = await axios.get(`/api/iconic-lines/get-iconic-line/${id}`);
        if (response.status === 200) {
            const iconicLine = response.data;
            nameIconicLine.value = iconicLine.name;
            brandIconicLine.value = iconicLine.brand.id_brand;
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

updateIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    const updateIconicLine = {
        iconicLine: {
            id_IconicLine: parseInt(id)
        },
        brand: {
            id_brand: parseInt(brandIconicLine.value)
        },
        name: nameIconicLine.value
    }

    try {
        const response = await axios.put(`/api/iconic-lines/update-iconic-line/${id}`, updateIconicLine);

        if (response.status === 200) {
            messageElement.textContent = 'Iconic Line updated successfully';
        } else {
            messageElement.textContent = 'Failed to update Iconic Line';
        }

    }catch (error){
        console.error('Error updating Iconic Line: ', error);
        messageElement.textContent = 'Error updating Iconic Line: ' + error.message;
    }
});