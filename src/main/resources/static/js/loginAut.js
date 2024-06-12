document.getElementById('login-form').addEventListener('submit', async function (e) {
  e.preventDefault();

  const email = document.getElementById('login_email').value;
  const password = document.getElementById('login_password').value;

  try {
    const response = await axios.post('/api/auth/login', {
      email: email,
      password: password
    });

    if (response.status === 200) {
      new Noty({
      type: 'success',
      layout: 'topRight',
      text: 'You have been logged in successfully.',
      timeout: 3000
    }).show();
      // Aquí puedes redirigir al usuario o mostrar un mensaje de éxito
    }
  } catch (err) {
    new Noty({
    type: 'error',
    layout: 'topRight',
    text: 'Please check your credentials and try again.',
    timeout: 3000
  }).show();
    // Aquí puedes mostrar un mensaje de error al usuario
  }
});
