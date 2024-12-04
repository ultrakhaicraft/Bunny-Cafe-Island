import './App.css';
import { BrowserRouter as Router, Routes, Route, BrowserRouter } from 'react-router-dom';
import LandingPage from './Pages/LandingPage';
import BunnyShowcase from './Pages/BunnyShowcase';
import AboutUs from './Pages/AboutUs';
import Login from './Pages/Login';
import StaffHome from './Pages/StaffHome';
import ManageBunny from './Pages/ManageBunny'
import ManageMenu from './Pages/ManageMenu';
import { useState } from 'react';

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [email,setEmail]= useState('')

  return (
    <>
      <BrowserRouter>  
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="bunnyShowcase" element={<BunnyShowcase />} />
          <Route path="aboutUs" element={<AboutUs/>}/>
          
          <Route path="login" element={<Login setLoggedIn={setLoggedIn} setEmail={setEmail} />} />
          <Route path="staffHome" element={<StaffHome />} /> 
          <Route path="manageBunny" element={<ManageBunny />} /> 
          <Route path="manageMenu" element={<ManageMenu />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
