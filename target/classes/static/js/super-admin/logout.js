document.getElementById('logout-btnSA').addEventListener('click', function() {
   
  // Borrar el estado de sesión de local storage
    localStorage.removeItem('isLoggedInSA');
  
    // Cambiar el navbar para el estado de no usuario
    document.getElementById('guest-nav').style.display = 'block';
    document.getElementById('super-nav').style.display = 'none';
  });
  