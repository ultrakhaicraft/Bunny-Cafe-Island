import React, { useEffect, useState } from 'react';
import { createPortal } from 'react-dom';
import Backdrop from './Component/Backdrop/backdrop';
import NavSideBar from './Component/Sidebar/navSideBar';
import './StaffCommon.css';
import { AddMenuItem, DeleteMenuItem, getAllMenuItems, UpdateMenuItem, uploadImage } from '../API/MenuItemsAPI';
	

const Main = () => {
	const [menuItems,setMenuItems] = useState([]);
	const [error,setError]= useState(null);
	const [isModalOpen,setIsModalOpen]=useState(false);
	

	 //Call API to get all menu items
	useEffect(()=>{
		const token = localStorage.getItem('jwtToken');
		
		(async()=>{
			try {
				const data=await getAllMenuItems(token);
				setMenuItems(data);
			} catch (error) {
				setError(error.message)
			}
		})();
	},[]); 

	

	return (
		<div>
				<NavSideBar/>
				
				<div className='main-content'>

				<div className="Title biggerText">
					Menu Item Management
				</div>
				<div className='modal-area'>
					<button className='btn primary-button' onClick={()=>setIsModalOpen(true)}> 
						Add
					</button>
				{isModalOpen && createPortal(<>
					<Backdrop/>
					<ModalForCreate  setIsModalOpen={setIsModalOpen} />
					</>,
				document.body)}

				</div>

				<div className='search-area'>
					<div className="search-bar">
					<span className="search-icon"><i className="fa-solid fa-magnifying-glass"></i></span>
					<input type="text" placeholder="Search..." className="search-input" />
				</div>
				</div>

				<div className='table-area'>
					<Table menuItems={menuItems}/>
				</div>

				<div className='pagination-area'>
					<Pagination />
				</div>


				</div>
				
		</div>
	

	);
};

const Table = ({menuItems}) => {
	const[isModalOpenDel,setIsModalOpenDel] = useState(false);
	const[isModalOpenView,setIsModalOpenView] = useState(false);
	const[isModalOpenUpd,setIsModalOpenUpd] = useState(false);
	const[selectedItem,setSelectedItem]=useState(null);

	const openViewModal = (menuItem) =>{
		setIsModalOpenView(true);
		setSelectedItem(menuItem)
	}

	const openEditModal = (menuItem) =>{
		setIsModalOpenUpd(true);
		setSelectedItem(menuItem)
	}

	const openDeleteModal = (menuItem) =>{
		setIsModalOpenDel(true);
		setSelectedItem(menuItem)
	}

	

	return(
		<>
		<table className='table'>
						<thead>         
							<tr>
								<th>Image</th>
								<th>Name</th>
								<th>Price</th>
								<th>Status</th>
								<th>Menu Type</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							{menuItems && menuItems.length >0 ? (
							menuItems.map((menuItem)=>(
								<tr key={menuItem.id}>
									<td><img src={menuItem.image_path} alt={menuItem.name} /> </td>
									<td className='Name'>{menuItem.name}</td>
									<td>{menuItem.price}</td>
									<td>{menuItem.status}</td>
									<td>{menuItem.type}</td>
									<td className='button-group'>
										<button className='btn secondary-button' onClick={()=>openViewModal(menuItem)}> <i className="fa-solid fa-eye"></i> </button>
										<button className='btn primary-button' onClick={()=>openEditModal(menuItem)}> <i className="fa-solid fa-pen-to-square"></i></button>
										<button className='btn danger-button' onClick={()=>openDeleteModal(menuItem)}>  <i className="fa-solid fa-trash-can"></i></button>
									</td>
								</tr>
							))
						):(
							<tr>
								<td colSpan="6">No menu items available</td>
							</tr>
						)}
						</tbody>         
		</table>

		<div className='modal-area'>
        
		{isModalOpenView && createPortal(<>
        <Backdrop/>
        <ModalForView 	
						setIsModalOpenView={setIsModalOpenView}
						MenuItem={selectedItem}/>
        </>
        ,document.body)}
		
        {isModalOpenUpd && createPortal(<>
        <Backdrop/>
        <ModalForUpdate 
					    setIsModalOpenUpd={setIsModalOpenUpd}
						MenuItem={selectedItem}/>
        </>
        ,document.body)}
		
        {isModalOpenDel && createPortal(<>
        <Backdrop/>
        <ModalForDelete 
						setIsModalOpenDel={setIsModalOpenDel}
						MenuItem={selectedItem}/>
        </>
        ,document.body)}
        </div>		
		
		</>
	);
};

const Pagination = () =>{
	

	return (
		<nav aria-label="table-page navigation">
		<ul className="pagination">
			<li className="page-item">
				<a className="page-link" href="#" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
					<span className="sr-only">Previous</span>
				</a>
			</li>
			<li className="page-item"><a className="page-link" href="#">1</a></li>
			<li className="page-item"><a className="page-link" href="#">2</a></li>
			<li className="page-item"><a className="page-link" href="#">3</a></li>
			<li className="page-item">
				<a className="page-link" href="#" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
					<span className="sr-only">Next</span>
				</a>
			</li>
		</ul>
	</nav>
	);
};

const ModalForCreate = ({setIsModalOpen}) => {
	const [newItem, setNewItem] = useState({
    name: "",
    price: "",
    description: "",
    status: "Available",
    type: "Permanent",
  	});
	const [file, setFile] = useState(undefined);

	const addItem = async (newItem) => {
		try {
		  
			const token = localStorage.getItem('jwtToken');
	  
		  	const itemData = await AddMenuItem(token,newItem);
		  	const itemId = itemData.id; 

		  
		  	const formData = new FormData();
		  	formData.append('file', file, file.name); 
		  	formData.append('id', itemId); 
	  
		  	const data = await uploadImage(token,formData);

		  	console.log("Item added successfully:", data);
	  
		  setNewItem({
			name: "",
			price: "",
			description: "",
			status: "Available",
			type: "Permanent",
		  });

		  setFile(undefined);
		  window.location.reload(false);

		} catch (error) {
		  console.error("Error adding item:", error);
		} finally {
		  setIsModalOpen(false);
		}
	  };

	const handleChange = (e) => {
    const { name, value } = e.target;
    setNewItem({ ...newItem, [name]: value });
  };

	
	return(
		<div className="modal fade"  role="dialog" >
			<div className="modal-dialog modal-dialog-centered" role="document">
				<div className="modal-content modal-content-long">
					<div className="modal-header">
					<h5 className="modal-title">Add an item</h5>
					<button onClick={()=>setIsModalOpen(false)}  className="btn-close" aria-label="Close"></button>
					</div>
					<form  onSubmit={(e) => {e.preventDefault(); addItem(newItem);}}>
					<div className="modal-body">
					<div className='form-group'>
					<label htmlFor='name'>Name</label>
					<input name='name' placeholder='Name' value={newItem.name} onChange={handleChange} required />
					</div>
					<div className='form-group'>
					<label htmlFor='price'>Price</label>
					<input name='price' placeholder='Price' type='number' min={0} max={9999} value={newItem.price} onChange={handleChange} required />
					</div>
					<div className='form-group'>
					<label htmlFor='description'>Description</label>
					<textarea name='description' placeholder='Description' value={newItem.description} onChange={handleChange} required ></textarea>
					</div>
					<div className='form-group selection'>
					<label htmlFor='status'>Status</label>
					<select name='status' onChange={handleChange} value={newItem.status} >
					<option value='Available'>Available</option>
					<option value='Unavailable'>Unavailable</option>
					</select>
					</div>
					<div className='form-group selection'>
					<label htmlFor='type'>Type</label>
					<select name='type' onChange={handleChange} value={newItem.type}>
					<option value='Permanent'>Permanent</option>
					<option value='Seasonal'>Seasonal</option>
					</select>
					</div>
					<div className='form-group'>
					<label htmlFor='image'>Image</label>
					<input name="image" type='file' onChange={(e)=>setFile(e.target.files[0])} required/>
					</div>
					</div>
					<div className="modal-footer">
					<button type="submit" className="btn primary-button">Add</button>
					<button onClick={()=>setIsModalOpen(false)} className="btn danger-button">Close</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	
	);
};

const ModalForDelete = ({setIsModalOpenDel,MenuItem}) =>{

	const deleteItem = async() => {
		try {
			
			const token = localStorage.getItem('jwtToken');
	
			const data = await DeleteMenuItem(token,MenuItem.id);
			
			console.log("Item deleted successfully:", data);

			window.location.reload(false);
		} catch (error) {
			console.error("Error deleting item:", error);
		}finally{
			setIsModalOpenDel(false);
		}
	}
	
	return(
		<div className='modal fade' role='dialog'>
			<div className='modal-dialog modal-dialog-centered' role='document'>
				<div className='modal-content'>
					<div className='modal-header'>
						<h5 className='modal-title'>Confirmation</h5>
						<button onClick={()=>setIsModalOpenDel(false)}  className="btn-close" aria-label="Close"></button>
					</div>
					<div className='modal-body'>Do you want to delete this item</div>
					<div className='modal-footer'>
						<button className="btn danger-button" onClick={deleteItem}>Yes</button>
						<button onClick={()=>setIsModalOpenDel(false)} className='btn primary-button'>No</button>
					</div>
				</div>
			</div>
		</div>
	);
};

const ModalForView = ({setIsModalOpenView,MenuItem}) =>{

	
	
	return(
		<div className='modal fade' role='dialog'>
			<div className='modal-dialog modal-dialog-centered' role='document'>
				<div className='modal-content modal-content-long'>
					<div className='modal-header'>
						<h5 className='modal-title'>View Item Detail</h5>
						<button onClick={()=>setIsModalOpenView(false)}  className="btn-close" aria-label="Close"></button>
					</div>
					<div className='modal-body'>
						<p>{MenuItem.name}</p>
					</div>
					<div className='modal-footer'>	
						<button onClick={()=>setIsModalOpenView(false)} className='btn primary-button'>Close</button>
					</div>
				</div>
			</div>
		</div>
	);
};

const ModalForUpdate = ({setIsModalOpenUpd,MenuItem}) => {
		const [updatedItem, setUpdatedItem] = useState(MenuItem || {});
		const [file,setFile]=useState(null);

		useEffect(() => {
			setUpdatedItem(MenuItem || {});
			setFile(MenuItem ||{});
		  }, [MenuItem]);

		const updateItem = async (updatedItem) => {
			try {

				const token = localStorage.getItem('jwtToken');
			  	
				const itemData = await UpdateMenuItem(token,updateItem)
          	  	const itemId = itemData.id; 

				const formData = new FormData();
				formData.append('file', file, file.name); 
				formData.append('id', itemId); 

			  	const data = await uploadImage(token,formData);
			  	console.log("Item updated successfully:", data);

				window.location.reload(false);
			} catch (error) {
			  console.error("Error updating item:", error);
			} finally {
			  setIsModalOpenUpd(false);
			}
		  };


		const handleChange = (e) => {
			const { name, value } = e.target;
			setUpdatedItem({ ...updatedItem, [name]: value });
		};

		
		return(
			
			<div className="modal fade"  role="dialog" >
			<div className="modal-dialog modal-dialog-centered" role="document">
				<div className="modal-content modal-content-long">
					<div className="modal-header">
					<h5 className="modal-title">Update an item</h5>
					<button onClick={()=>setIsModalOpenUpd(false)}  className="btn-close" aria-label="Close"></button>
					</div>
					<form  onSubmit={(e) => {e.preventDefault(); updateItem(updatedItem)}}>
					<div className="modal-body">
					<div className='form-group'>
					<label htmlFor='name'>Name</label>
					<input name='name' placeholder='Name' value={updatedItem.name || ""} onChange={handleChange} required />
					</div>
					<div className='form-group'>
					<label htmlFor='price'>Price</label>
					<input name='price' placeholder='Price' type='number' min={0} max={9999} value={updatedItem.price || ""} onChange={handleChange} required />
					</div>
					<div className='form-group'>
					<label htmlFor='description'>Description</label>
					<textarea name='description' placeholder='Description' value={updatedItem.description || ""} onChange={handleChange} required ></textarea>
					</div>
					<div className='form-group selection'>
					<label htmlFor='status'>Status</label>
					<select name='status' onChange={handleChange} value={updatedItem.status || "Available"} >
					<option value='Available'>Available</option>
					<option value='Unavailable'>Unavailable</option>
					</select>
					</div>
					<div className='form-group selection'>
					<label htmlFor='type'>Type</label>
					<select name='type' onChange={handleChange} value={updatedItem.type || "Permanent"}>
					<option value='Permanent'>Permanent</option>
					<option value='Seasonal'>Seasonal</option>
					</select>
					</div>
					<div className='form-group'>
					<label htmlFor='image'>Image</label>
					<p>Current: {updatedItem.image_path}</p>
					<input name="image" type='file' onChange={(e)=>setFile(e.target.files[0])} />
					</div>
					</div>
					<div className="modal-footer">
					<button type="submit" className="btn primary-button">Add</button>
					<button onClick={()=>setIsModalOpenUpd(false)} className="btn danger-button">Close</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		);
};



export default Main