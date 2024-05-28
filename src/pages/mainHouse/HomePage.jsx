import Header from "../../components/navbar/app"
import "../../style/HomePage.css";
import Footer from "../../components/footer/Footer";


export default function HomePage(){
    return(
    <>
    <Header/>
    <section className="maing-home">
        <div className="home-text">
            <h5>Derozan Collections</h5>
            <h1>New Year  </h1>
            <h1>Collections 2024</h1>
            <p>Choose your new style</p>
            <a href="#" className="btn-shop">Shop Now</a>
        </div> 
    </section>
        <section className="container-welcome">
            <div className="container-row-welcome"> 
                <div className="container-izquierdo-welcome">
                    <div className="big-title">
                        <p className="big-top">WELCOME TO</p>

                        <p className="big-middle">THE DEROZAN</p>
                
                        <p className="big-subtitle">YOUR PERSONAL SNEAKER ADVISOR</p>
                    </div>
                
                    <div className="text">
                        <p className="text-style">
                            The range of sneakers grows every day and new models appear every day. Not surprising that you drown in choices as soon as you are looking for a few new sneakers. Fortunately, that is a thing of the past! The Sneakerbaron shows you the way, shows you the latest models and advises where necessary. He checks everything from: Nike sneakers, adidas, Jordan, Puma, New Balance, Asics, Converse, Vans, until Reebok, Balenciaga and Diadora.
                        </p>
                        <p className="text-style">
                            On our blog you will find announcements of new releases, tips & tricks for all your kicks and the recommendations of the Sneakerbaron itself. So whether you are looking for exclusive sneakers that literally nobody has or that you simply want a new pair of red shoes. The Sneaker Baron makes his knowledge available so that YOU can shine. Let´s go!
                        </p>
                    </div>
                </div>
                
                <div className="container-derecho-welcome">
                    <p className="vertical">IT´S TIME TO</p>
                    <p className="vertical2">BUY NEW KICKS</p>   
                </div>
            </div>
    </section>
  
    <Footer/>
    </>
    )
}