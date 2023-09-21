import React, { useState } from "react";
import logo from "./images/LC_Banja_Luka_red.png";
import axios from "axios";

//const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,20}$/;
//const PWD_REGEX = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;

export const Login = (props) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginFailMessage, setLoginFailMessage] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    const loginForm = {
      username: username,
      lozinka: password,
    };

    console.log(loginForm);

    axios
      .put("http://localhost:8080/admins/login", loginForm)
      .then((response) => {
        if (response.status === 200) {
          localStorage.setItem('token', response.data)


          props.onFormSwitch("main");
          props.setAdmin(loginForm);
        }

      }).catch((error) => {
        setLoginFailMessage(true);
      });

  };

  return (
    <div className="login-form-container">
      <img src={logo} alt="" className="logo" />
      <h2 className="heading">Admin</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="username">Korisničko ime:</label>
        <input
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          type="username"
          placeholder="admin"
          id="username"
          name="username"
          required
        ></input>

        <label htmlFor="password">Lozinka:</label>
        <input
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          type="password"
          placeholder="********"
          id="password"
          name="password"
          required
        ></input>

        {
          loginFailMessage ? <p className="info-text" >Pogrešni pristupni podaci.</p> : <></>
        }

        <button type="submit" className="login-button">
          Prijavi se
        </button>
      </form>
    </div>
  );
};
