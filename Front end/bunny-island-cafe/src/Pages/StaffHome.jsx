import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { Outlet,Link } from "react-router-dom";
import './StaffCommon.css'


const Home = (props) => {
  const location = useLocation();
  const { email, loggedIn } = location.state || {};
  
  

  
  return (
    <div className="dashboard container-fluid row">
      
        <div className="SideMenu col-lg-2">

          <div className="MenuTitle BigText container-fluid row">
            

            <img
              className="MenuIcon col-lg-7"
              alt="Bunny icon by Dooder from Flaticon"
              src="src/assets/index/images/bunny.png"
            />

            <div className="TextWrapper col-lg-5">BIC</div>
          </div>

          <div className="MenuContent smallMediumText">
            <div className="MenuItems">
              <Link to="/staffHome">Dashboard</Link>
            </div>
            
            <div className="MenuItems">
              <Link to="/handleReservation">Handle Reservation</Link>
            </div>

            <div className="MenuItems">
              <Link to="/handleAdoption">Handle Adoption</Link>
            </div>

            <div className="MenuItems">
              <Link to="/manageBunny">Manage Bunny</Link>
            </div>

            <div className="MenuItems">
              <Link to="/manageMenu">Manage Menu</Link>
            </div>
          </div>

          
        </div>

        <div className='Content col-lg-10'>
        <div className="Title BiggerText">
          Welcome, "username"
        </div>

        <div className="Label BigText">
          Email: "Email"
        </div>

        <div className="Label BigText">
          Role: "Role"
        </div>
        </div>
        
    </div>
  

  );
};

export default Home