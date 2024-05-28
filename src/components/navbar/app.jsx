import React, { useState } from 'react';
import Header from './Header';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import "../../style/navbar.css"

function App() {
  const [isLoginForm, setIsLoginForm] = useState(true);
  const [isFormVisible, setIsFormVisible] = useState(false);

  const toggleFormVisibility = () => setIsFormVisible(!isFormVisible);
  const showSignupForm = () => {
    setIsLoginForm(false);
    setIsFormVisible(true);
  };
  const showLoginForm = () => {
    setIsLoginForm(true);
    setIsFormVisible(true);
  };

  return (
    <>
      <Header onLoginClick={toggleFormVisibility} />
      <section className={`home ${isFormVisible ? 'show' : ''}`}>
        <div className={`form_container ${isLoginForm ? '' : 'active'}`}>
          <i className="uil uil-times form_close" onClick={toggleFormVisibility}></i>
          {isLoginForm ? (
            <LoginForm onSignupClick={showSignupForm} />
          ) : (
            <SignupForm onLoginClick={showLoginForm} />
          )}
        </div>
      </section>
    </>
  );
}

export default App;