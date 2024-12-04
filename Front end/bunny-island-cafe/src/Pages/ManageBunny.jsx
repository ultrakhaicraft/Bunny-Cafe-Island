import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom'
import { Outlet,Link } from "react-router-dom";
import './StaffCommon.css'





const Main = () => {
  const [bunnies,setBunnies] = useState([]);
  const [error,setError]= useState(null);
  //Call API to get bunny
  
  
  
  

  
  return (
    <div >
        <NavSideBar/>

        <div className='main-content'>
        <div className="Title BiggerText">
          Bunny Management
        </div>

        <button>Placeholder Add</button>
        <div className='table'>
          <BunnyTable bunnies={bunnies}/>
        </div>
        </div>
        
    </div>
  

  );
};


const BunnyTable = ({bunnies}) => {

  return(
    <table>
            <thead>         
              <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Breed</th>
                <th>age</th>
                <th>availabilityStatus</th>
                <th>healthStatus</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
            {bunnies?.map((bunny)=>(
              <tr key={bunny.id}>
                <td><img src={bunny.image} alt={bunny.name}/></td>
                <td>{bunny.name}</td>
                <td>{bunny.breed}</td>
                <td>{bunny.age}</td>
                <td>{bunny.availabilityStatus}</td>
                <td>{bunny.healthStatus}</td>
                <td>
                  <button>Placeholder View</button>
                  <button>Placeholder Edit</button>
                  <button>Placeholder Delete</button>
                </td>
              </tr>
            ))}
            </tbody>         
    </table>
  );
};


const NavSideBar = () => {
  const[isOpen,setIsOpen] = useState(false);

  const toggleSidebar = () =>{
    setIsOpen(!isOpen);
  };


  return(
      <aside className={`sidebar ${isOpen? 'sidebar-collapsed' : '' } `}>
        <button className={`toggleSidebar-Btn ${isOpen? 'toggleSidebar-Btn-collapsed':''}`} onClick={toggleSidebar}>
        <i className={`fas ${isOpen ? 'fa-chevron-left' : 'fa-chevron-right'} toggle-icon`}></i>
        </button>
          <div className='sidebar-header' >
            <h2 className='brand '><i className="fas fa-anchor"></i>
            <span className='' >Bunny Island Cafe</span></h2>
          </div>

          <nav className='sidebar-content'>
            <ul className='nav-links'>
                <li>
                  <Link className='nav-item'  to="/staffHome"><span className="nav-icon"><i className="fas fa-home"></i></span>
                  <span>Home</span></Link>
                </li>
                <li>
                  <Link className='nav-item' to="/handleReservation"><span className="nav-icon"><i className="fa-regular fa-calendar-check"></i></span>
                  <span>Handle Reservation</span></Link>
                </li>
                <li>
                  <Link className='nav-item'  to="/handleAdoption"><span className="nav-icon"><i className="fa-solid fa-check-to-slot"></i></span>
                  <span>Handle Adoption</span></Link>
                </li>
                <li>
                  <Link className='nav-item' to="/manageBunny"><span className="nav-icon"><i className="fa-solid fa-feather"></i></span>
                  <span>Manage Bunny</span></Link>
                </li>
                <li>
                  <Link className='nav-item' to="/manageMenu"><span className="nav-icon"><i className="fa-solid fa-utensils"></i></span>
                  <span>Manage Menu</span></Link>
                </li>
            </ul>
          </nav>
      </aside>
    );
};




export default Main