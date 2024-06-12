document.getElementById('signup_form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('signup_username').value;
    const email = document.getElementById('signup_email').value;
    const password = document.getElementById('signup_password').value;

    try {
      const response = await axios.post('/api/users/register-user', {
        name: username,
        email: email,
        password: password
      });

      new Noty({
        type: 'success',
        layout: 'topRight',
        text: 'You have been signed up successfully.',
        timeout: 3000,
        theme: 'mint'
      }).show();
      // Aquí puedes redirigir al usuario o mostrar un mensaje de éxito
    } catch (err) {
      console.error('Error during sign up', err);
      new Noty({
        type: 'error',
        layout: 'topRight',
        text: 'Please check your credentials and try again.',
        timeout: 3000,
        theme: 'mint'
      }).show();
    }
  });