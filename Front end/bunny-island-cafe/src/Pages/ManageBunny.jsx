import React, { useEffect, useState } from 'react';
import NavSideBar from './Component/Sidebar/navSideBar';
import './StaffCommon.css';



const Main = () => {
  const [bunnies,setBunnies] = useState([]);
  const [error,setError]= useState(null);
  
  //TODO: Call API to get bunny
  
  
  return (
    <div >
        <NavSideBar/>
        <div className='main-content'>
        <div className="Title biggerText">
          Bunny Management
        </div>

        <button className='btn primary-button' > 
						Add
					</button>

        <div className='search-area'>
					<div className="search-bar">
					<span className="search-icon"><i className="fa-solid fa-magnifying-glass"></i></span>
					<input type="text" placeholder="Search..." className="search-input" />
				</div>
				</div>

        <div className='table-area'>
          <Table bunnies={bunnies}/>
        </div>
        </div>
    </div>

  );
};


const Table = ({bunnies}) => {

  return(
    <table className='table'>
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
                <td className='Name'>{bunny.name}</td>
                <td>{bunny.breed}</td>
                <td>{bunny.age}</td>
                <td>{bunny.availabilityStatus}</td>
                <td>{bunny.healthStatus}</td>
                <td className='button-group'>
										<button className='btn secondary-button' > <i className="fa-solid fa-eye"></i> </button>
										<button className='btn primary-button' > <i className="fa-solid fa-pen-to-square"></i></button>
										<button className='btn danger-button' >  <i className="fa-solid fa-trash-can"></i></button>
									</td>
              </tr>
            ))}
            </tbody>         
    </table>
  );
};


export default Main