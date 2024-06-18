window.addEventListener('load', function() {
  const isLoggedIn = localStorage.getItem('isLoggedIn');
  const isLoggedInSA = localStorage.getItem('isLoggedInSA');
  const isLoggedInAD = localStorage.getItem('isLoggedInAD');
  
  
  if (isLoggedInSA === 'true') {
      document.getElementById('guest-nav').style.display = 'none';
      document.getElementById('user-nav').style.display = 'none';
      document.getElementById('admin-nav').style.display = 'none';
      document.getElementById('super-nav').style.display = 'block';
          
  } else if (isLoggedIn === 'true') {
      document.getElementById('guest-nav').style.display = 'none';
      document.getElementById('user-nav').style.display = 'block';
      document.getElementById('super-nav').style.display = 'none';
       
  } else if (isLoggedInAD === 'true') {
    document.getElementById('guest-nav').style.display = 'none';
      document.getElementById('user-nav').style.display = 'none';
      document.getElementById('admin-nav').style.display = 'block';
      document.getElementById('super-nav').style.display = 'none';
     
  } 
  else {
      document.getElementById('guest-nav').style.display = 'block';
      document.getElementById('user-nav').style.display = 'none';
      document.getElementById('super-nav').style.display = 'none';
      document.getElementById('admin-nav').style.display = 'none';
  }
});
