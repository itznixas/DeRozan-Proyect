import { useRouteError } from "react-router-dom";
import "../style/error.css"
import error404 from "../assets/error/error404.jpg"
import Header from "../components/navbar/Header";

export default function Error(){
    const error = useRouteError();
    return(

    <div>
      <Header/>
    <section className="container-home">
      <div className="container-text">
       
        <h1>404 <br /> {error.statusText || error.message}</h1>
        <img src={error404} alt="error" />
      </div>
    </section>
    </div>
    
    );
}