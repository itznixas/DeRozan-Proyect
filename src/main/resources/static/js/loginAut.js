document.getElementById('login-form').addEventListener('submit', async function (e) {
  e.preventDefault();

  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;

  try {
    const response = await axios.post('/api/auth/login', {
      email: email,
      password: password
    });

    if (response.status === 200) {
      const roleId = response.data.roleId;
      const userId = response.data.userId;

      localStorage.setItem('userId', userId); // Guardar userId localmente
      localStorage.setItem('roleId', roleId); // Guardar roleId localmente

      updateNavbar(roleId, email);

      new Noty({
        type: 'success',
        layout: 'topRight',
        text: 'You have been logged in successfully.',
        timeout: 300
      }).show();

    } else {
      new Noty({
        type: 'error',
        layout: 'topRight',
        text: 'Please check your credentials and try again.',
        timeout: 3000
      }).show();
    }
  } catch (err) {
    new Noty({
      type: 'error',
      layout: 'topRight',
      text: 'Please check your credentials and try again.',
      timeout: 3000
    }).show();
  }
});

function updateNavbar(roleId, email) {

  document.getElementById('guest-nav').style.display = 'none';
  document.getElementById('user-nav').style.display = 'none';
  document.getElementById('admin-nav').style.display = 'none';
  document.getElementById("super-nav").style.display = "none";


  if (roleId === 1) { 
    document.getElementById('user-nav').style.display = 'block';
    document.getElementById('homeLogin').style.display = 'none';
    localStorage.setItem('isLoggedIn', 'true');
    console.log('Mostrar interfaz para clientes (ID 1)');
    return;
  } if (roleId === 2) {
    localStorage.setItem('isLoggedInAD', 'true')
    document.getElementById('admin-nav').style.display = 'block';
    document.getElementById('homeLogin').style.display = 'none';
    console.log('Mostrar interfaz para administradores (ID 2)');
    return;
  } if (roleId === 3) { 
    document.getElementById('super-nav').style.display = 'block';
    document.getElementById('homeLogin').style.display = 'none';
    localStorage.setItem('isLoggedInSA', 'true');
    console.log('Mostrar interfaz para super-administradores (ID 3)');
    return;
  } else {
    console.log('Rol desconocido:', roleId);
    new Noty({
      type: 'error',
      layout: 'topRight',
      text: 'Rol desconocido. Contacta al soporte.',
      timeout: 3000
    }).show();
  }
}

document.addEventListener("DOMContentLoaded", function () {
  const isLoggedIn = localStorage.getItem("isLoggedIn");
  const isLoggedInSA = localStorage.getItem("isLoggedInSA");
  const isLoggedInAD = localStorage.getItem("isLoggedInAD");
  const roleId = parseInt(localStorage.getItem("roleId"), 10);
  const userId = localStorage.getItem("userId");

  console.log("Page loaded. isLoggedIn:", isLoggedIn, "isLoggedInSA:", isLoggedInSA, "isLoggedInAD:", isLoggedInAD, "roleId:", roleId, "userId:", userId);

  if (isLoggedIn === "true" && !isNaN(roleId)) {
    updateNavbar(roleId);
    return;
  }
  if (isLoggedInSA === "true" && !isNaN(roleId)) {
    updateNavbar(roleId);
    return;
  }
  if (isLoggedInAD === "true" && !isNaN(roleId)) {
    updateNavbar(roleId);
    return;
  } else {
    document.getElementById("guest-nav").style.display = "block";
  }
});