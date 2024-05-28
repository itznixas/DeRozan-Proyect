import React from 'react';
import { Link } from 'react-router-dom';
import "../../style/navbar.css"

function Header({ onLoginClick }) {
  return (
    <header className="header">
      <nav className="nav">
        <a href="#" className="nav_logo">DEROZAN</a>
        <ul className="nav_items">
          <li className="nav_item">
            <Link className='nav_link' to={"/gdg"}>HOME</Link>
            <Link className='nav_link' to={"/login"}>SNEAKERS</Link>
            <Link className='nav_link' to={"/ffg"}>RELASES</Link>
            <Link className='nav_link' to={"/dgdg"}>CONTACT</Link>
          </li>
        </ul>
        <button className="button-login" id="form-open" onClick={onLoginClick}>Login</button>

      </nav>
    </header>
  );
}

export default Header;
