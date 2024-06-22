const deleteProduct = document.getElementById('delete-product');
const productId = document.getElementById('id-producto');
const messageElement = document.getElementById('message');

deleteProduct.addEventListener('click', async () => {
    const id = productId.value;

    if (!id) {
        messageElement.textContent = 'Please enter a product ID';
        return;
    }

    try {
        const response = await axios.delete(`/api/sneakers/delete/${id}`);
        if (response.status === 204) {
            messageElement.textContent = 'Product deleted successfully';
        } else {
            messageElement.textContent = 'Failed to delete product';
        }
    } catch (error) {
        console.error('Error deleting product: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Product not found';
        } else {
            messageElement.textContent = 'Error deleting product: ' + error.message;
        }
    }
});