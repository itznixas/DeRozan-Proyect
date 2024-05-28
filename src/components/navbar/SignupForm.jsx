// src/components/SignupForm.jsx
import React from 'react';
import "../../style/navbar.css"  

function SignupForm({ onLoginClick }) {
  return (
    <div className="form signup_form">
      <form>
        <h2>Signup</h2>
        <div className="input_box">
          <input type="email" placeholder="Enter your email" required />
          <i className="uil uil-envelope-alt email"></i>
        </div>
        <div className="input_box">
          <input type="password" placeholder="Create password" required />
          <i className="uil uil-lock password"></i>
          <i className="uil uil-eye-slash pw_hide" onClick={togglePasswordVisibility}></i>
        </div>
        <div className="input_box">
          <input type="password" placeholder="Confirm password" required />
          <i className="uil uil-lock password"></i>
          <i className="uil uil-eye-slash pw_hide" onClick={togglePasswordVisibility}></i>
        </div>
        <button className="button">Signup Now</button>
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

export default SignupForm;
