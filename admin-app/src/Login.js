import React, { useState } from "react";

//const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,20}$/;
//const PWD_REGEX = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;

export const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(username);
        console.log(password);
        props.onFormSwitch('main')
    }

    return(
        <div className="login-form-container">
             <h2 className="heading">Prijava</h2>
        <form className="login-form" onSubmit={handleSubmit}>
            <label htmlFor="username">Korisničko ime</label>
            <input value={username} onChange={(e) => setUsername(e.target.value)} type = "username" placeholder="marko.markovic" id="username" name="username"></input>

            <label htmlFor="password">Šifra</label>
            <input value={password} onChange={(e) => setPassword(e.target.value)} type = "password" placeholder="********" id="password" name="password"></input>

            <button type="submit">Prijavi se</button> 
        </form>
        </div>    
    )

    
}
