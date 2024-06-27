const addBrand = document.getElementById('add-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

addBrand.addEventListener('click', async () => {
    const addBrand = {
        name: brandName.value
    }

    try {
        const response = await axios.post('/api/brands/add-brand', addBrand);
        new Noty({
            type: 'success',
            layout: 'topRight',
            text: 'The brand have been created correctly.',
            timeout: 3000    
          }).show();
    }catch (error) {
        console.error('Error saving brand:', error);
        new Noty({
            type: 'error',
            layout: 'topRight',
            text: 'The brand could not be created correctly',
            timeout: 3000
          }).show();
    }
});