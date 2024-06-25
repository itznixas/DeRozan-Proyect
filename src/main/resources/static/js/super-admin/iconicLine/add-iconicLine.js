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
        
        new Noty({
            type: 'success',
            layout: 'topRight',
            text: 'The iconic Line have been created correctly.',
            timeout: 3000    
          }).show();
    }catch (error) {
        console.error('Error saving Iconic Line:', error.message);
        new Noty({
            type: 'error',
            layout: 'topRight',
            text: 'The iconic Line could not be created correctly',
            timeout: 3000
          }).show();
    }
});