import React from 'react';
import "../../style/navbar.css"

function LoginForm({ onSignupClick }) {
  return (
    <div className="form login_form">
      <form>
        <h2>Login</h2>
        <div className="input_box">
          <input type="email" placeholder="Enter your email" required />
          <i className="uil uil-envelope-alt email"></i>
        </div>
        <div className="input_box">
          <input type="password" placeholder="Enter your password" required />
          <i className="uil uil-lock password"></i>
          <i className="uil uil-eye-slash pw_hide" onClick={togglePasswordVisibility}></i>
        </div>
        <div className="option_field">
          <span className="checkbox">
            <input type="checkbox" id="check" />
            <label htmlFor="check">Remembmr me</label>
          </span>
          <a href="#" className="forgot_pw">Forgot password?</a>
        </div>
        <button className="button">Login Now</button>
        <div className="login_signup">Don´t have an account? <a href="#" onClick={onSignupClick}>Signup</a></div>
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

export default LoginForm;
