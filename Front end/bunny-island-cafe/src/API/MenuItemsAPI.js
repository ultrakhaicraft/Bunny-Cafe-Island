

export const getAllMenuItems = async (token) => {
    try {
        
        const response = await fetch('http://localhost:8080/api/management/menuItems', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error(`API call error: ${response.status} ${response.statusText}`);
        }

        return await response.json();
    } catch (error) {
        throw new Error(error.message); 
    }
};



export const AddMenuItem = async (token,newItem) => {
    try {
        
        const response = await fetch('http://localhost:8080/api/management/menuItems', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newItem),
        });

        if (!response.ok) {
            throw new Error(`Failed to add new menu item: ${response.status} ${response.statusText}`);
        }

        return await response.json();
    } catch (error) {
        throw new Error(error.message); 
    }
};

export const UpdateMenuItem = async (token,updatedItem) => {
    try {
        console.log(token);
        const response = await fetch('http://localhost:8080/api/management/menuItems', {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedItem)
        });

        if (!response.ok) {
            throw new Error(`Failed to update menu item info: ${response.status} ${response.statusText}`);
        }

        return await response.json();
    } catch (error) {
        throw new Error(error.message); 
    }
};

export const DeleteMenuItem = async (token,id) => {
    try {
        
        const response = await fetch(`http://localhost:8080/api/management/menuItems/${id}`,{
            method: 'DELETE',
            headers:{
                'Content-Type':'application/json',
                'Authorization': `Bearer ${token}`
            }
        })

        if (!response.ok) {
            throw new Error(`Failed to delete menu item: ${response.status} ${response.statusText}`);
        }

        return await response.json();
    } catch (error) {
        throw new Error(error.message); 
    }
};

export const uploadImage = async (token,formData) => {

    try {
        const response = await fetch("http://localhost:8080/api/management/menuItems/images", {
            method: "PUT",
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: formData,
          });
    
     
          if (!response.ok) {
            throw new Error(`Failed to upload image: ${response.status} ${response.statusText}`);
          }
          return await response.json();
    } catch (error) {
        throw new Error(error.message);
    }
};