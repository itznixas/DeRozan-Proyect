const addBrand = document.getElementById('add-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

addBrand.addEventListener('click', async () => {
    const addBrand = {
        name: brandName.value
    }

    try {
        const response = await axios.post('/api/brands/add-brand', addBrand);
        messageElement.textContent = 'Brand saved successfully';
    }catch (error) {
        console.error('Error saving brand:', error);
        messageElement.textContent = 'Error saving brand: ' + error.message;
    }
});