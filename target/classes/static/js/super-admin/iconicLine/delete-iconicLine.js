const searchIconicLine = document.getElementById('search-iconic');
const deleteIconicLine = document.getElementById('delete-iconic');
const idIconicLine = document.getElementById('id-iconic');
const nameIconicLine = document.getElementById('name-iconic');
const messageElement = document.getElementById('message');

searchIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if (!id) {
        
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a iconic line ID.',
            timeout: 3000
          }).show();
        return;
    }

    try{
        const response = await axios.get(`/api/iconic-lines/get-iconic-line/${id}`);
        if (response.status === 200) {
            const iconicLine = response.data;
            nameIconicLine.value = iconicLine.name;
        } else {
            new Noty({
                type: 'info',
                layout: 'topLeft',
                text: 'Iconic Line not found.',
                timeout: 3000
              }).show();
        }

    }catch (error) {
        console.error('Error fetching Iconic Line: ', error);
        new Noty({
            type: 'warning',
            layout: 'topRight',
            text: 'Error fetching Iconic Line, look at the console',
            timeout: 3000
          }).show();
        if (error.response && error.response.status === 404) {
            console.error(messageElement.textContent = 'Iconic Line not found');
            new Noty({
                type: 'info',
                layout: 'topLeft',
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

deleteIconicLine.addEventListener('click', async () => {
    const id = idIconicLine.value;

    if (!id) {
    
        new Noty({
            type: 'info',
            layout: 'topLeft',
            text: 'Please enter a iconic line ID.',
            timeout: 3000
          }).show();
        return;
    }

    try{
        const response = await axios.delete(`/api/iconic-lines/delete-iconic-line/${id}`);
        if (response.status === 204) {
            messageElement.textContent = 'Iconic Line deleted successfully';
            new Noty({
                type: 'success',
                layout: 'topRight',
                text: 'The iconic Line have been deleted correctly.',
                timeout: 3000    
              }).show();

        } else {
            new Noty({
                type: 'error',
                layout: 'topRight',
                text: 'Failed to delete Iconic Line',
                timeout: 3000
              }).show();
        }
    } catch (error) {
        console.error('Error deleting Iconic Line: ', error);
        if (error.response && error.response.status === 404) {

            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Iconic Line not found',
                timeout: 3000
              }).show();
        } else {
            console.error(messageElement.textContent = 'Error deleting Iconic Line: ' + error.message);
            new Noty({
                type: 'warning',
                layout: 'topRight',
                text: 'Error deleting Iconic Line, look at the console',
                timeout: 3000
              }).show();
        }
    }
});