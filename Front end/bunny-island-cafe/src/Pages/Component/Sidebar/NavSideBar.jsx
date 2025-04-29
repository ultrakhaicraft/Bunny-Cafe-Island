import './NavSideBar.css'
import React, { useState } from 'react'
import { Link } from "react-router-dom";

const NavSideBar = () => {
	const[isOpen,setIsOpen] = useState(false);

	const toggleSidebar = () =>{
		setIsOpen(!isOpen);
	};

	return(
			<aside id='staff-sidebar' className={`sidebar ${isOpen? 'sidebar-collapsed' : '' } `}>
				<button className={`toggleSidebar-Btn ${isOpen? 'toggleSidebar-Btn-collapsed':''}`} onClick={toggleSidebar}>
				<i className={`fas ${isOpen ? 'fa-chevron-left' : 'fa-chevron-right'} toggle-icon`}></i>
				</button>
					<div className='sidebar-header' >
						<h2 className='brand '><i className="fas fa-anchor"></i>
						<span className='' >Bunny Island Cafe</span></h2>
					</div>

					<nav className='sidebar-content'>
						<ul className='staff-links nav-links'>
								<li>
									<Link className='staff-nav-item'  to="/staffHome"><span className="nav-icon"><i className="fas fa-home"></i></span>
									<span>Home</span></Link>
								</li>
								<li>
									<Link className='staff-nav-item' to="/handleReservation"><span className="nav-icon"><i className="fa-regular fa-calendar-check"></i></span>
									<span>Handle Reservation</span></Link>
								</li>
								<li>
									<Link className='staff-nav-item'  to="/handleAdoption"><span className="nav-icon"><i className="fa-solid fa-check-to-slot"></i></span>
									<span>Handle Adoption</span></Link>
								</li>
								<li>
									<Link className='staff-nav-item' to="/manageBunny"><span className="nav-icon"><i className="fa-solid fa-feather"></i></span>
									<span>Manage Bunny</span></Link>
								</li>
								<li>
									<Link className='staff-nav-item' to="/manageMenu"><span className="nav-icon"><i className="fa-solid fa-utensils"></i></span>
									<span>Manage Menu</span></Link>
								</li>
						</ul>
					</nav>
			</aside>
		);
};

export default NavSideBar