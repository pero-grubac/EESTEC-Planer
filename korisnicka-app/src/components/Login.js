import React, { useState } from "react";
import logo from '../images/LC_Banja_Luka_red.png';
import { useNavigate } from "react-router-dom";
import '../index.css'; 
// import axios

//const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,20}$/;
//const PWD_REGEX = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;

export const Login = (props) => {
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [regUsername, setRegUsername] = useState('');
    const [regName, setRegName] = useState('');
    const [regSurname, setRegSurname] = useState('');
    const [regEmail, setRegEmail] = useState('');
    const [regPassword, setRegPassword] = useState('');

    const [isLogin, setIsLogin] = useState(true);

    const handleLoginSubmit = (e) => {
        e.preventDefault();

        const loginForm = {
            username: username,
            lozinka: password
        }


        // axios.put('http://localhost:8080/admins/login', loginForm)
        //     .then(response => {
        //         if (response.status === 200) {
        //             // Authentication successful
        //             console.log('Login successful');
        //             // Store authentication data (e.g., token) and redirect
        //             // You can use a state management library like Redux for this
        //             props.onFormSwitch('main');
        //         } else {
        //             // Handle other successful responses or unexpected data
        //             console.log('Unexpected response:', response.data);
        //         }
        //     })
        //     .catch(error => {
        //         // Handle errors (e.g., authentication failure)
        //         console.error('Login error:', error.response.data);
        //         // Display an error message to the user
        //         // Update the UI to indicate the login failed
        //     });

            //ako je uspjesno:
            setTimeout(() => {
                navigate('/design', { replace: true });
              }, 3000);
    }

    const handleRegistrationSubmit = (e) => {
        e.preventDefault();

        console.log(regUsername, regName, regSurname, regEmail, regPassword);
    }

    return (
        <div className="login-form-container">
            <img src={logo} alt="" className="logo" />
            {
                isLogin ?
                    <div>
                        <h2 className="heading">Prijava</h2>
                        <form className="login-form" onSubmit={handleLoginSubmit}>
                            <label htmlFor="username">Korisničko ime:</label>
                            <input value={username} onChange={(e) => setUsername(e.target.value)} type="username" placeholder="marko.markovic" id="username" name="username" required></input>

                            <label htmlFor="password">Lozinka:</label>
                            <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="********" id="password" name="password" required></input>

                            <button type="submit" className="login-button">Prijavi se</button>
                        </form>
                        <u className="link" onClick={() => setIsLogin(false)}>Nemate nalog? Registrujte se!</u>
                        <p className="registration-info">Ako ste zaboravili pristupne podatke, javite se administratoru.</p>
                    </div> :
                    <div>
                        <h2 className="heading">Registracija</h2>
                        <p className="registration-info">Nakon uspješne registracije, administrator mora da odobri nalog.</p>
                        <form className="registration-form" onSubmit={handleRegistrationSubmit}>
                            <div className="registration-attributes">
                                <div className="registration-labels">
                                    <label className="registration-label" htmlFor="username">Korisničko ime:</label>
                                    <label className="registration-label" htmlFor="name">Ime:</label>                                   
                                    <label className="registration-label" htmlFor="surname">Prezime:</label>
                                    <label className="registration-label" htmlFor="email">email:</label>
                                    <label className="registration-label" htmlFor="password">Lozinka:</label>
                                </div>

                                <div className="registration-inputs">
                                    <input value={regUsername} onChange={(e) => setRegUsername(e.target.value)} type="username" name="username" required></input>
                                    <input value={regName} onChange={(e) => setRegName(e.target.value)} type="text" name="name" required></input>
                                    <input value={regSurname} onChange={(e) => setRegSurname(e.target.value)} type="text" name="surname" required></input>
                                    <input value={regEmail} onChange={(e) => setRegEmail(e.target.value)} type="text" name="email" required></input>
                                    <input value={regPassword} onChange={(e) => setRegPassword(e.target.value)} type="password" name="password" required></input>
                                </div>
                            </div>

                            <button type="submit" className="login-button registration-button">Registruj se</button>
                        </form>
                        <u className="link" onClick={() => setIsLogin(true)}>Već imate nalog? Prijavite se!</u>
                    </div>

            }

        </div>
    )


}
