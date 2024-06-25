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
const productImage = document.getElementById('file1');
const messageElement = document.getElementById('message');

searchProduct.addEventListener('click', async () => {
    const id = productId.value;

    if (!id) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a sneaker ID',
            timeout: 3000
        }).show();
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
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Sneaker not found',
                timeout: 3000
            }).show();
        }
    } catch (error) {
        console.error('Error fetching product: ', error);
        if (error.response && error.response.status === 404) {
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Sneaker not found',
                timeout: 3000
            }).show();
        } else {
            messageElement.textContent = 'Error fetching product: ' + error.message;
        }
    }
});

updateProduct.addEventListener('click', async () => {
    const id = productId.value;

    const formData = new FormData();
    formData.append('description', productDescription.value);
    formData.append('brand.id_brand', parseInt(productMark.value));
    formData.append('amount', parseInt(productAmount.value));
    formData.append('price', parseFloat(productPrice.value));
    formData.append('size', parseFloat(productSize.value));
    formData.append('color', productColor.value);
    formData.append('iconicLine.id_IconicLine', parseInt(productIdLine.value));
    formData.append('name', productTitle.value);
    formData.append('category', productCategory.value);
    if (productImage.files.length > 0) {
        formData.append('image', productImage.files[0]);
    }

    if (!id || !formData.get('description') || !formData.get('brand.id_brand') || !formData.get('amount') ||
        !formData.get('price') || !formData.get('size') || !formData.get('color') ||
        !formData.get('iconicLine.id_IconicLine') || !formData.get('name') || !formData.get('category')) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please fill in all fields.',
            timeout: 3000
        }).show();
        return;
    }

    try {
        const response = await axios.put(`/api/sneakers/update-sneakers/${id}`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        if (response.status === 200) {
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'The sneakers updated successfully.',
                timeout: 3000
            }).show();
        } else {
            new Noty({
                type: 'error',
                layout: 'topRight',
                text: 'Failed to update product the sneakers',
                timeout: 3000
            }).show();
        }
    } catch (error) {
        console.error('Error updating product: ', error.message);
        new Noty({
            type: 'error',
            layout: 'topRight',
            text: 'Error updating the sneakers',
            timeout: 3000
        }).show();
    }
});