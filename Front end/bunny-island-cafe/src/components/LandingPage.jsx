import React from "react"
import './LandingPage.css'


function LandingPage(){

    return(
    <div className="landing-page">
      
      <nav className="navigation navbar navbar-expand-lg navbar-light ">
                <img className="navbar-brand" src="src/assets/index/images/bunny.png" alt="Bunny icon by Dooder from Flaticon"/>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">  
                            <a className="nav-link active" href="index.html">Home <span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item "> 
                            <a className="nav-link" href="about_us.html">About us</a>
                        </li>
                        <li className="nav-item "> 
                            <a className="nav-link" href="menu.html">Menu</a>
                        </li>
                        <li className="nav-item "> 
                            <a className="nav-link" href="bunny.html">Bunny </a>
                        </li>
                    </ul>
                </div>
        </nav>

        <main>
            <div className="Hero container-fluid row">
                <div className="Title">
                <h1>Do you like bunny ?<br/> You want to chill with the bunnies ?<br/> Want to take a bunny home ?<br/></h1>
                <h2>Bunny Island Cafe is for you</h2>
                </div>
                <div className="Button">
                    <button type="button" className="btn btn-primary">Reserve a table </button>
                    <button type="button" className="btn btn-secondary">Adopt a bunny </button>
                </div>
            </div>

            <div className="About_Us container-fluid row ">             
                    <div className="Image container col-sm-12 col-lg-6 mb-2 mb-lg-0">
                        <img className="" src="src/assets/index/images/BunnyCafe2ByShutterstock.png" alt="placeholder"/>
                    </div>                   
                    <div className="Text col-sm-12 col-lg-6 mb-2 mb-lg-0">
                        <h1>Our cafe</h1>
                        <p>Bunny Island Cafe is a place where you can relax and have fun with the our shop raised bunnies AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA place where you can relax and have fun with the our shop raised bunnies AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA place where you can relax and have fun with the our shop raised bunnies AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</p>
                    </div>              
            </div>
            <div className="Event_News container-fluid row">
                <div className="Event col-sm-12 col-lg-6 mb-4 mb-lg-0">
                    <section>
                        <h2>Event</h2>
                        <div className="container">
                        <img src="src/assets/index/images/beautiful-halloween-free-photo-by-Viktor-Hanacek.jpg" alt="Event"/>
                        
                            <h3>Halloween is upon us, Bunny lovers</h3>
                            <p>Join us for the spooky experience about the legendary Rabbit Killer, We will rediscover the origin of this infamous bunny</p>
                        
                        </div>
                    </section>  
                </div>
                <div className="News col-sm-12 col-lg-6 mb-4 mb-lg-0">
                    <section>
                        <h2>News</h2>
                        <ul>
                            <li className="item">
                                <h3>New Collab</h3>
                                <p>A new collabroration with our sponsor Dai Lieu</p>
                            </li>
                            <li className="item">
                                <h3>Huge Menu Discount</h3>
                                <p>Extremely big 50% discount for all Menu items from 2nd Sep to 9th Sep</p>
                            </li>
                            <li className="item">
                                <h3>Cafe Maintenance</h3>
                                <p>Our cafe will temporarily closed on 31st Aug</p>
                            </li>                          
                        </ul>
                    </section>  
                </div> 
            </div> 
        </main>
      
      <div className="footer">
        <div className="FooterBox row">
            <div className="left col-6">
                <h2>Contact us</h2>
                <ul className="contact-list">
                    <li className="item">Phone number: 093-827-232</li>
                    <li className="item">Facebook: <a href="#">Facebook link</a> </li>
                    <li className="item">Twitter: <a href="#">Twitter link</a> </li>
                </ul>
            </div>
            <div className="right col-6">
                <h2>Address</h2>
                <p>63726-1B, Apple Street, Delta District</p>  
            </div>
        </div>        
    </div>
    
        </div>
    ); 
}


export default LandingPage;