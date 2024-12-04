import React from "react"
import './AboutUs.css'
import { Outlet,Link } from "react-router-dom";



function AboutUs(){
    return(
        <div className="AboutUs">
             <nav className="navigation navbar navbar-expand-lg navbar-light ">
                <img className="navbar-brand" src="src/assets/index/images/bunny.png" alt="Bunny icon by Dooder from Flaticon"/>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                    <li className="nav-item">                         
                            <Link className="nav-link " to="/">Home</Link>
                        </li>
                        <li className="nav-item "> 
                            
                            <Link className="nav-link active" to="/aboutUs">About us<span className="sr-only">(current)</span></Link>
                        </li>
                        <li className="nav-item "> 
                            
                            <Link className="nav-link " to="/">Menu</Link>
                        </li>
                        <li className="nav-item "> 
                            
                            <Link className="nav-link " to="/bunnyShowcase">Bunny</Link>
                        </li>
                    </ul>
                </div>
            </nav>
            <main>

            </main>

            <div className='footer'>
                <div className="FooterBox row">
                    <div className="left col-6">
                        <h2>Contact us</h2>
                        <ul className="contact-list">
                        <li className="item">Phone number: 093-827-232</li>
                        <li className="item">
                            Facebook: <a href="#">Facebook link</a>
                        </li>
                        <li className="item">
                            Twitter: <a href="#">Twitter link</a>
                        </li>
                        </ul>
                    </div>
                    <div className="right col-6">
                        <h2>Address</h2>
                        <p>63726-1B, Apple Street, Delta District</p>
                    </div>
                </div>
            </div>
        </div>
    )
}


export default AboutUs;