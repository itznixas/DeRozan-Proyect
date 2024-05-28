import React from 'react';
import facebook from "../../assets/redesSocial/facebook.svg"
import instagram from "../../assets/redesSocial/instagram.svg"
import X from "../../assets/redesSocial/twitter.svg"
import img_footer from "../../assets/redesSocial/footer.svg"

import "../../style/footer.css"

function Footer() {
  return (
    <footer>
      <div className="top-footer">
        <img src={img_footer} alt="Footer Background" />
      </div>
      <div className="bootoomfooter">
        <h2 className="titlefooter">DEROZAN</h2>
        <div className="rowsocial">
          <h2 id="socialmedia">SOCIAL MEDIA</h2>
          <img src={facebook} alt="facebook" />
          <img src={instagram} alt="instagram" />
          <img src={X} alt="X" />
          <p>COPYRIGHT © 2024 SNEAKERBARON.NL - ALLE RECHTEN VOORBEHOUDEN</p>
        </div>
      </div>
      <div className="avisos-politicas">
        <div className="container-txt-politicas">
          <a href="aviso-de-privacidad.html" className="textos-de-avisos">Aviso de Privacidad</a>
          <a href="politica-y-condiciones.html" className="textos-de-avisos">Politicas y Condiciones</a>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
