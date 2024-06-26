const searchIconicLine = document.getElementById('search-iconic');
const updateIconicLine = document.getElementById('update-iconic');
const idIconicLine = document.getElementById('id-iconic');
const nameIconicLine = document.getElementById('name-iconicLine');
const brandIconicLine = document.getElementById('brand-iconicLine');
const messageElement = document.getElementById('message');

searchIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if(id == null) {
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a Iconic Line ID.',
            timeout: 3000
          }).show();
        return;
    }

    try {
        const response = await axios.get(`/api/iconic-lines/get-iconic-line/${id}`);
        if (response.status === 200) {
            const iconicLine = response.data;
            nameIconicLine.value = iconicLine.name;
            brandIconicLine.value = iconicLine.brand.id_brand;
        } else {
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Iconic Line not found',
                timeout: 3000
              }).show();
        }

    }catch (error) {
        console.error('Error fetching Iconic Line: ', error);
        if (error.response && error.response.status === 404) {
            console.error(messageElement.textContent = 'Iconic Line not found');
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Iconic Line not found, look at the console',
                timeout: 3000
              }).show();
        } else {
             console.error(messageElement.textContent = 'Error fetching Iconic Line: ' + error.message);
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Error fetching Iconic Line, look at the console',
                timeout: 3000
              }).show();
        }
    }
});

updateIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    const updateIconicLine = {
        iconicLine: {
            id_IconicLine: parseInt(id)
        },
        brand: {
            id_brand: parseInt(brandIconicLine.value)
        },
        name: nameIconicLine.value
    }

    try {
        const response = await axios.put(`/api/iconic-lines/update-iconic-line/${id}`, updateIconicLine);

        if (response.status === 200) {
           
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'Iconic Line updated successfully.',
                timeout: 3000    
              }).show();

        } else {
            
            new Noty({
                type: 'error',
                layout: 'topRight',
                text: 'Failed to update Iconic Line',
                timeout: 3000
              }).show();
        }

    }catch (error){
        console.error('Error updating Iconic Line: ', error.message);   
        new Noty({
            type: 'warning',
            layout: 'topRight',
            text: 'Error updating Iconic Line',
            timeout: 3000
          }).show();
    }
});