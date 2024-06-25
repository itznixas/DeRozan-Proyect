const updateBrand = document.getElementById('update-brand');
const searchBrand = document.getElementById('search-brand');
const idBrand = document.getElementById('id-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

searchBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if(id == null) {
        messageElement.textContent = 'Please enter a brand ID';
        return;
    }

    try {
        const response = await axios.get(`/api/brands/get-brand/${id}`);

        if (response.status === 200) {
            const brand = response.data;
            brandName.value = brand.name;
        } else {
            messageElement.textContent = 'Brand not found';
        }

    }catch (error) {

        console.error('Error fetching Brand: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Brand not found';
        } else {
            messageElement.textContent = 'Error fetching Brand: ' + error.message;
        }

    }

});

updateBrand.addEventListener('click', async () => {
   const id = idBrand.value;

    const updateBrand = {
        brand: {
            id_brand: parseInt(idBrand.value)
        },
        name: brandName.value
    }

    try {
        const response = await axios.put(`/api/brands/update-brand/${id}`, updateBrand);

        if (response.status === 200) {
            messageElement.textContent = 'Brand updated successfully';
        } else {
            messageElement.textContent = 'Failed to update Brand';
        }

    }catch (error) {

        console.error('Error updating Brand: ', error);
        messageElement.textContent = 'Error updating Brand: ' + error.message;

    }
});
