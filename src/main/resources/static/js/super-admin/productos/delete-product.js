const deleteProduct = document.getElementById('delete-product');
const productId = document.getElementById('id-producto');
const messageElement = document.getElementById('message');

deleteProduct.addEventListener('click', async () => {
    const id = productId.value;

    if (!id) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please fill in all fields.',
            timeout: 3000
          }).show();
        return;
    }

    try {
        const response = await axios.delete(`/api/sneakers/delete/${id}`);
        if (response.status === 204) {
           
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'The sneakers deleted successfully.',
                timeout: 3000    
              }).show();
        } else {
            
            new Noty({
                type: 'error',
                layout: 'topRight',
                text: 'Failed to delete the sneakers',
                timeout: 3000
              }).show();
        }
    } catch (error) {
        console.error('Error deleting product: ', error);
        if (error.response && error.response.status === 404) {
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Sneaker not found',
                timeout: 3000
              }).show();
        } else {
            console.error('Error deleting product: ' + error.message);
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Sneaker error deleting',
                timeout: 3000
              }).show();
        }
    }
});