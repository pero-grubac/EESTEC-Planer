import { useState } from "react";

function UserDetails() {

    const testUser = {
        korisnickoIme: "korisnik",
        ime: "ime",
        prezime: "prezime"
    }

    const [name, setName] = useState(testUser.ime);
    const [surname, setSurname] = useState(testUser.prezime);
    const [username, setUsername] = useState(testUser.korisnickoIme);

    return (
        <form className="user-details-container">
            <div className="user-details-edit">
                <div className="user-details-column">
                    <label htmlFor="ime">Ime</label>
                    <label htmlFor="prezime">Prezime</label>
                    <label htmlFor="korisnicko-ime">Korisničko ime</label>
                    <label htmlFor="lozinka">Lozinka</label>
                </div>
                <div className="user-details-column user-input-column">
                    <input value={name} onChange={(e) => setName(e.target.value)} id="ime" type="text"></input>
                    <input value={surname} onChange={(e) => setSurname(e.target.value)} id="prezime" type="text"></input>
                    <input value={username} onChange={(e) => setUsername(e.target.value)} id="korisnicko-ime" type="text"></input>
                    <input id="lozinka" type="text"></input>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button" type="submit">Sačuvaj</button>
                <button className="login-button">Nazad</button>
            </div>
        </form>

    )
}

export default UserDetails;