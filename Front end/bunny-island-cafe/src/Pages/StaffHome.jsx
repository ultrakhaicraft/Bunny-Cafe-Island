import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import NavSideBar from './Component/Sidebar/navSideBar';
import './StaffCommon.css'


const Home = () => {
  const location = useLocation();
  /*const { email, loggedIn } = location.state || {};*/
  const [user,setUser] = useState({}); 
  
  
    
    useEffect(() => {
      const storedUserInfo = JSON.parse(localStorage.getItem("userInfo"));     
      if (storedUserInfo) {
        setUser(storedUserInfo)
      }
    }, [])
    

  return (
    <div>
      
      <NavSideBar/>  

      <div className='main-content col-lg-10'>
        <div className="Title BiggerText">
          Welcome, {user.name}
        </div>

        <div className="Label BigText">
          Email: {user.email}
        </div>

        <div className="Label BigText">
          Role: {user.role}
        </div>

        {user.role=="Manager"&&<AdminOnly/>}
        
      </div>
        
    </div>
  

  );
};


const AdminOnly = ()=>{
  return(
    <>
    <div >
      <h3>Hello there Manager !!!!</h3>
    </div>
    </>
  );
};

export default Home