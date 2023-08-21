import { useState } from "react";

function UserDetails({toggleList}) {

    const testUser = {
        korisnickoIme: "korisnik",
        ime: "ime",
        prezime: "prezime",
        email: "imeprezime@email.com",
        role: "koordinator"
    }

    const [name, setName] = useState(testUser.ime);
    const [surname, setSurname] = useState(testUser.prezime);
    const [username, setUsername] = useState(testUser.korisnickoIme);
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState(testUser.email);
    const [role, setRole] = useState(testUser.role);

    return (
        <form className="user-details-container">
            <div className="user-details-edit">
                <div className="user-details-column">
                    <label htmlFor="ime">Ime</label>
                    <label htmlFor="prezime">Prezime</label>
                    <label htmlFor="korisnicko-ime">Korisničko ime</label>
                    <label htmlFor="email">email</label>
                    <label htmlFor="lozinka">Lozinka</label>
                    <label htmlFor="uloga">Uloga</label>
                </div>
                <div className="user-details-column user-input-column">
                    <input value={name} onChange={(e) => setName(e.target.value)} id="ime" type="text"></input>
                    <input value={surname} onChange={(e) => setSurname(e.target.value)} id="prezime" type="text"></input>
                    <input value={username} onChange={(e) => setUsername(e.target.value)} id="korisnicko-ime" type="text"></input>
                    <input value={email} onChange={(e) => setEmail(e.target.value)} id="email" type="text"></input>
                    <input value={password} onChange={(e) => setPassword(e.target.value)} id="lozinka" type="text"></input>
                    <input value={role} onChange={(e) => setRole(e.target.value)} id="uloga" type="text"></input>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button" type="submit">Sačuvaj</button>
                <button className="login-button" type="submit">Obriši nalog</button>
                <button className="login-button">Nazad</button>
            </div>
        </form>

    )
}

export default UserDetails;