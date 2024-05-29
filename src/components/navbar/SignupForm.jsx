import React from 'react';
import "../../style/navbar.css"
import {supabase} from "../../supabaseClient.jsx";

function SignupForm({ onLoginClick }) {

  const handleGoogleSignup = async () => {
    const { error } = await supabase.auth.signInWithOAuth({
      provider: 'google',
    });

    if (error) console.error('Error during Google signup', error);
  };

  return (
      <div className="form signup_form">
        <form onSubmit={handleSignup}>
          <h2>Signup</h2>
          <div className="input_box">
            <input type="email" id="email" placeholder="Enter your email" required />
            <i className="uil uil-envelope-alt email"></i>
          </div>
          <div className="input_box">
            <input type="password" id="password" placeholder="Create password" required />
            <i className="uil uil-lock password"></i>
            <i className="uil uil-eye-slash pw_hide" onClick={togglePasswordVisibility}></i>
          </div>
          <div className="input_box">
            <input type="password" id="confirmPassword" placeholder="Confirm password" required />
            <i className="uil uil-lock password"></i>
            <i className="uil uil-eye-slash pw_hide" onClick={togglePasswordVisibility}></i>
          </div>
          <button className="button" type="submit">Signup Now</button>
          <div className="social_signup">
            <button type="button" className="button google" onClick={handleGoogleSignup}>
              <i className="uil uil-google"></i> Google
            </button>
            <button type="button" className="button facebook" onClick={handleFacebookSignup}>
              <i className="uil uil-facebook-f"></i> Facebook
            </button>
          </div>
          <div className="login_signup">Already have an account? <a href="#" onClick={onLoginClick}>Login</a></div>
        </form>
      </div>
  );
}

function togglePasswordVisibility(e) {
  const input = e.target.parentElement.querySelector('input');
  if (input.type === 'password') {
    input.type = 'text';
    e.target.classList.replace('uil-eye-slash', 'uil-eye');
  } else {
    input.type = 'password';
    e.target.classList.replace('uil-eye', 'uil-eye-slash');
  }
}

function handleSignup(e) {
  e.preventDefault();
  // Lógica para manejar el registro normal
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmPassword').value;

  // Validación simple de ejemplo
  if (password !== confirmPassword) {
    alert('Passwords do not match');
    return;
  }

  console.log('Signup with email and password', email, password);
  // Implementa la lógica de registro aquí (por ejemplo, enviar datos al servidor)
}

function handleFacebookSignup() {
  // Lógica para registrar con Facebook
  console.log("Facebook signup clicked");
  // Aquí iría la lógica de autenticación con Facebook, evitando la validación de campos
}

export default SignupForm;
