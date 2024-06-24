const saveProductButton = document.getElementById('save-product');

saveProductButton.addEventListener('click', async () => {
    const formData = new FormData();
    formData.append('name', document.getElementById('productTitle').value);
    formData.append('id_sneakers', document.getElementById('productId').value);
    formData.append('description', document.getElementById('productDescription').value);
    formData.append('brand.id_brand', parseInt(document.getElementById('brand').value));
    formData.append('amount', parseInt(document.getElementById('amount').value));
    formData.append('price', parseFloat(document.getElementById('price').value));
    formData.append('size', parseFloat(document.getElementById('size').value));
    formData.append('category', document.getElementById('categories').value);
    formData.append('color', document.getElementById('color').value);
    formData.append('iconicLine.id_IconicLine', parseInt(document.getElementById('lineId').value));
    formData.append('file', document.getElementById('file1').files[0]);

    try {
        const response = await axios.post('/api/sneakers/add-sneakers', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        document.getElementById('message').textContent = 'Product saved successfully';
    } catch (error) {
        console.error('Error saving product:', error);
        document.getElementById('message').textContent = 'Error saving product: ' + error.message;
    }
});
