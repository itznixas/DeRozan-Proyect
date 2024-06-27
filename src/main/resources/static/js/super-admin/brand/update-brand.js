const updateBrand = document.getElementById('update-brand');
const searchBrand = document.getElementById('search-brand');
const idBrand = document.getElementById('id-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

searchBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if(id == null) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a brand ID.',
            timeout: 3000
          }).show();
        return;
    }

    try {
        const response = await axios.get(`/api/brands/get-brand/${id}`);

        if (response.status === 200) {
            const brand = response.data;
            brandName.value = brand.name;
        } else {
            
            new Noty({
                type: 'info',
                layout: 'topLeft',
                text: 'Brand not found.',
                timeout: 3000
              }).show();
        }

    }catch (error) {

        console.error('Error fetching Brand: ', error);
        if (error.response && error.response.status === 404) {
           
            new Noty({
                type: 'info',
                layout: 'topLeft',
                text: 'Brand not found.',
                timeout: 3000
              }).show();
        } else {
            messageElement.textContent = 'Error fetching Brand: ' + error.message;
            new Noty({
                type: 'info',
                layout: 'topLeft',
                text: 'Error fetching Brand, please check in the console',
                timeout: 3000
              }).show();
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
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'Brand updated successfully.',
                timeout: 3000    
              }).show();
        } else {
            messageElement.textContent = 'Failed to update Brand';
        }

    }catch (error) {
        console.error('Error updating Brand: ' + error.message);
        new Noty({
            type: 'error',
            layout: 'topRight',
            text: 'Error updating Brand.',
            timeout: 3000    
          }).show();

    }
});
