const addIconicLine = document.getElementById('add-iconic');
const idBrand = document.getElementById('id-brand');
const nameIconicLine = document.getElementById('id-iconic');
const messageElement = document.getElementById('message');

addIconicLine.addEventListener('click', async () => {
    const iconicLineData = {
        name: nameIconicLine.value,
        brand: {
            id_brand: parseInt(idBrand.value)
        }
    };

    try{
       const response = await axios.post('/api/iconic-lines/add-iconic-line', iconicLineData)
        messageElement.textContent = 'Iconic Line saved successfully';
    }catch (error) {
        console.error('Error saving Iconic Line:', error);
        messageElement.textContent = 'Error saving Iconic Line: ' + error.message;
    }
});