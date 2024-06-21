const saveProductButton = document.getElementById('save-product');
const productTitleInput = document.getElementById('productTitle');
const productDescriptionInput = document.getElementById('productDescription');
const productId = document.getElementById('productId');
const brandInput = document.getElementById('brand');
const amountInput = document.getElementById('amount');
const priceInput = document.getElementById('price');
const sizeInput = document.getElementById('size');
const categoriesInput = document.getElementById('categories');
const colorInput = document.getElementById('color');
const lineIdInput = document.getElementById('lineId');
const messageElement = document.getElementById('message');

saveProductButton.addEventListener('click', async () => {
    // Prepare product data object
    const productData = {
        name: productTitleInput.value,
        id_sneakers: productId.value,
        description: productDescriptionInput.value,
        brand: {
            id_brand: parseInt(brandInput.value)
        },
        amount: parseInt(amountInput.value),
        price: parseFloat(priceInput.value),
        size: parseFloat(sizeInput.value),
        category: categoriesInput.value,
        color: colorInput.value,
        iconicLine: {
            id_IconicLine: parseInt(lineIdInput.value)
        },
    };

    try {
        const response = await axios.post('/api/sneakers/add-sneakers', productData);
        messageElement.textContent = 'Product saved successfully';

    } catch (error) {
        console.error('Error saving product:', error);
        messageElement.textContent = 'Error saving product: ' + error.message;
    }
});