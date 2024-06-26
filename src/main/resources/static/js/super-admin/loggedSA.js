window.addEventListener('load', function() {
    const isLoggedInSA = localStorage.getItem('isLoggedInSA');
  
    if (isLoggedInSA === 'true') {
      document.getElementById('guest-nav').style.display = 'none';
      document.getElementById('super-nav').style.display = 'block';
    } else {
      document.getElementById('guest-nav').style.display = 'block';
      document.getElementById('super-nav').style.display = 'none';
    }
  });
  