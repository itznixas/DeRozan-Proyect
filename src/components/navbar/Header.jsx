import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import "../../style/navbar.css";

function Header({ onLoginClick }) {
  const location = useLocation();
  
  // Determinar el color del texto basado en la ruta
  const getColor = () => {
    switch (location.pathname) {
      case '/':
        return '#FFFFFF'; // Color blanco para la página de inicio
      case '/login':
        return '#000000'; // Color negro para la página de sneakers
      case '/ffg':
        return '#000000'; // Color negro para la página de releases
      case '/dgdg':
        return '#000000'; // Color negro para la página de contacto
      default:
        return '#FFFFFF'; // Color por defecto
    }
  };

  return (
    <header className="header">
      <nav className="nav" style={{ color: getColor() }}>
        <a href="#" className="nav_logo" style={{ color: getColor() }}>DEROZAN</a>
        <ul className="nav_items">
          <li className="nav_item">
            <Link className="nav_link" to={"/"} style={{ color: getColor() }}>HOME</Link>
            <Link className="nav_link" to={"/login"} style={{ color: getColor() }}>SNEAKERS</Link>
            <Link className="nav_link" to={"/ffg"} style={{ color: getColor() }}>RELEASES</Link>
            <Link className="nav_link" to={"/dgdg"} style={{ color: getColor() }}>CONTACT</Link>
          </li>
        </ul>
        <div className="login">
          <button className="button-login" id="form-open" onClick={onLoginClick}  style={{ color: getColor() }}>Login</button>
          <button className="button-login" id="form-open" onClick={onLoginClick}  style={{ color: getColor() }}>Login</button>
          <button className="button-login" id="form-open" onClick={onLoginClick}  style={{ color: getColor() }}>Login</button>
        </div>
      </nav>
    </header>
  );
}

export default Header;
