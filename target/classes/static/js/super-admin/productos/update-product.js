const updateProduct = document.getElementById('save-product');
const searchProduct = document.getElementById('btn-busqueda-id');
const productId = document.getElementById('id-producto');
const productDescription = document.getElementById('description');
const productMark = document.getElementById('mark');
const productAmount = document.getElementById('amount');
const productPrice = document.getElementById('price');
const productSize = document.getElementById('size');
const productCategory = document.getElementById('categories');
const productColor = document.getElementById('color');
const productIdLine = document.getElementById('id-linea');
const productTitle = document.getElementById('name');
const messageElement = document.getElementById('message');

searchProduct.addEventListener('click', async () => {
    const id = productId.value;

    if (!id) {
        messageElement.textContent = 'Please enter a product ID';
        return;
    }

    try {
        const response = await axios.get(`/api/sneakers/get-sneaker/${id}`);
        if (response.status === 200) {
            const product = response.data;
            productDescription.value = product.description;
            productMark.value = product.brand.id_brand;
            productAmount.value = product.amount;
            productPrice.value = product.price;
            productSize.value = product.size;
            productColor.value = product.color;
            productIdLine.value = product.iconicLine.id_IconicLine;
            productTitle.value = product.name;
            productCategory.value = product.category;
        } else {
            messageElement.textContent = 'Product not found';
        }
    } catch (error) {
        console.error('Error fetching product: ', error);
        if (error.response && error.response.status === 404) {
            messageElement.textContent = 'Product not found';
        } else {
            messageElement.textContent = 'Error fetching product: ' + error.message;
        }
    }
});

updateProduct.addEventListener('click', async () => {
    const id = productId.value;

    const updateProductData = {
        id_sneakers: parseInt(id),
        description: productDescription.value,
        brand: {
            id_brand: parseInt(productMark.value)
        },
        amount: parseInt(productAmount.value),
        price: parseFloat(productPrice.value),
        size: parseFloat(productSize.value),
        color: productColor.value,
        iconicLine: {
            id_IconicLine: parseInt(productIdLine.value)
        },
        name: productTitle.value,
        category: productCategory.value,
        status: "activo"
    };

    if (!id || !updateProductData.description || !updateProductData.brand.id_brand || !updateProductData.amount ||
        !updateProductData.price || !updateProductData.size || !updateProductData.color ||
        !updateProductData.iconicLine.id_IconicLine || !updateProductData.name || !updateProductData.category) {
        messageElement.textContent = 'Please fill in all required fields';
        return;
    }

    try {
        const response = await axios.put(`/api/sneakers/update-sneakers/${id}`, updateProductData);
        if (response.status === 200) {
            messageElement.textContent = 'Product updated successfully';
        } else {
            messageElement.textContent = 'Failed to update product';
        }
    } catch (error) {
        console.error('Error updating product: ', error);
        messageElement.textContent = 'Error updating product: ' + error.message;
    }
});
