const deleteBrand = document.getElementById('delete-brand');
const searchBrand = document.getElementById('search-brand');
const idBrand = document.getElementById('id-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

searchBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if(!id) {
        messageElement.textContent = 'Please enter a brand ID';
        return;
    }

    try{
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

deleteBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if(!id) {
        messageElement.textContent = 'Please enter a brand ID';
        return;
    }

    try{
        const response = await axios.delete(`/api/brands/delete-brand/${id}`);
        if (response.status === 204) {
            messageElement.textContent = 'Brand deleted successfully';
        } else {
            messageElement.textContent = 'Failed to delete Brand';
        }
    } catch (error) {
        console.error('Error deleting Brand: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Brand not found';
        } else {
            messageElement.textContent = 'Error deleting Brand: ' + error.message;
        }
    }

});