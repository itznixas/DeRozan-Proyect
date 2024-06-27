const deleteBrand = document.getElementById('delete-brand');
const searchBrand = document.getElementById('search-brand');
const idBrand = document.getElementById('id-brand');
const brandName = document.getElementById('brand-name');
const messageElement = document.getElementById('message');

searchBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if (!id) {
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
                type: 'warning',
                layout: 'topRight',
                text: 'Brand not found',
                timeout: 3000
            }).show();
        }
    } catch (error) {
        console.error('Error fetching Brand: ', error);
        if (error.response && error.response.status === 404) {
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Brand not found',
                timeout: 3000
            }).show();
        } else {
            console.error('Error fetching Brand: ' + error.message);
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Error fetching Brand, please check the console',
                timeout: 3000
            }).show();
        }
    }
});

deleteBrand.addEventListener('click', async () => {
    const id = idBrand.value;

    if (!id) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a brand ID.',
            timeout: 3000
        }).show();
        return;
    }

    try {
        const response = await axios.delete(`/api/brands/delete-brand/${id}`);

        if (response.status === 204) {
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'Brand deleted successfully.',
                timeout: 3000
            }).show();
            // Clear the brand details
            idBrand.value = '';
            brandName.value = '';
        } else {
            new Noty({
                type: 'error',
                layout: 'topRight',
                text: 'Failed to delete the brand.',
                timeout: 3000
            }).show();
        }
    } catch (error) {
        console.error('Error deleting Brand: ', error);
        if (error.response && error.response.status === 404) {
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Brand not found',
                timeout: 3000
            }).show();
        } else {
            console.error('Error deleting Brand: ' + error.message);
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Error deleting Brand, please check the console',
                timeout: 3000
            }).show();
        }
    }
});
