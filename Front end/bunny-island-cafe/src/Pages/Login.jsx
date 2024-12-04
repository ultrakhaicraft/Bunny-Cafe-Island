import React, { useState } from 'react'
import "./Login.css"
import { useNavigate } from 'react-router-dom';

function Login(props){
    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[emailError, setEmailError] = useState('');
    const[passwordError, setPasswordError] = useState('');
    const[responseError, setResponseError] = useState(''); //Check API exception

    const navigate = useNavigate();

    async function handleForm(event){
        // Prevent the default form submission behavior
        event.preventDefault();

        // Empty out the error
        setEmailError('');
        setPasswordError('');
        setResponseError('');

        // Create json data
        const loginData = {
            email: email,
            password: password
        };

        // Attempt to call API
        try {
            const response = await fetch('http://localhost:8080/api/login',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData)
            });

            // Check the response
            if (response.ok){
                const data = await response.json();
                const loggedIn = true;
                console.log("Login successful: ", data);
                localStorage.setItem('token', data.token);
                navigate("/staffHome", { state: { email: email, loggedIn: loggedIn } });
                
            } else {
                const errorData = await response.json();
                setResponseError(errorData.message);
                console.log("Login fail: ", errorData);
            }
        } catch (error) {
            console.error('Error during login:', error);
            setResponseError('An exception error occurred. Please try again later.');
        }
    }

    return (
        <div className='Body'>
            <div className='loginContainer'>
                <h1>Bunny Island Cafe Management System</h1>
                {/* Attach onSubmit to form, not button */}
                <form onSubmit={handleForm}>
                    <div className='form-group'>
                        <label htmlFor="email">Email address</label>
                        <input 
                            value={email} 
                            type="email" 
                            id="email" 
                            onChange={(ev) => setEmail(ev.target.value)} 
                            className='form-input'  
                            placeholder="Enter your email here" 
                            required 
                        /> 
                        <label className='errorLabel'>{emailError}</label>               
                    </div>
                    <div className='form-group'>
                        <label htmlFor="password">Password</label>
                        <input 
                            value={password} 
                            type="password" 
                            id="password"  
                            onChange={(ev) => setPassword(ev.target.value)} 
                            className='form-input' 
                            placeholder="Enter your password here" 
                            required 
                        />
                        <label className='errorLabel'>{passwordError}</label>
                    </div>
                    <button type="submit" className='btn btn-primary'>Submit</button>
                </form>
                {responseError && <p className="errorLabel">{responseError}</p>}
            </div>
        </div>
    );
}

export default Login;
