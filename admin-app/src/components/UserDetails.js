import { useState } from "react";
import CoordinatorTeams from "./CoordinatorTeams";

function UserDetails({ switchTab, selectedUser }) {
    const [name, setName] = useState(selectedUser.ime);
    const [surname, setSurname] = useState(selectedUser.prezime);
    const [username, setUsername] = useState(selectedUser.korisnickoIme);
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState(selectedUser.email);
    const [role, setRole] = useState(selectedUser.uloga);

    return (
        <form className="user-details-container">
            <div className="user-details-basic">
                <h3>Detalji o korisniku</h3>
                <div className="user-details-edit">
                    <div className="user-details-column">
                        <label htmlFor="ime">Ime</label>
                        <label htmlFor="prezime">Prezime</label>
                        <label htmlFor="korisnicko-ime">Korisničko ime</label>
                        <label htmlFor="email">email</label>
                        <label htmlFor="lozinka">Lozinka</label>

                    </div>
                    <div className="user-details-column user-input-column">
                        <input value={name} onChange={(e) => setName(e.target.value)} id="ime" type="text"></input>
                        <input value={surname} onChange={(e) => setSurname(e.target.value)} id="prezime" type="text"></input>
                        <input value={username} onChange={(e) => setUsername(e.target.value)} id="korisnicko-ime" type="text"></input>
                        <input value={email} onChange={(e) => setEmail(e.target.value)} id="email" type="text"></input>
                        <input value={password} onChange={(e) => setPassword(e.target.value)} id="lozinka" type="text"></input>
                    </div>
                </div>
                <div className="uloge">
                    <input
                        className="checkbox"
                        type="checkbox"
                        id="koordinator"
                        name="uloga"
                        value="koordinator"
                        checked={role === "koordinator"}
                        onChange={() => (role === "koordinator" ? setRole(null) : setRole("koordinator"))}
                    />
                    <label htmlFor="koordinator">Koordinator</label>

                    <input
                        className="checkbox"
                        type="checkbox"
                        id="clan-upravnog-odbora"
                        name="uloga"
                        value="clan_upravnog_odbora"
                        checked={role === "clan_upravnog_odbora"}
                        onChange={() => (role === "clan_upravnog_odbora" ? setRole(null) : setRole("clan_upravnog_odbora"))}
                    />
                    <label htmlFor="clan-upravnog-odbora">Član upravnog odbora</label>
                </div>

                <div className="user-details-buttons">
                    <button className="login-button" type="submit">Sačuvaj</button>
                    <button className="login-button" onClick={() => switchTab("user_del")}>Obriši nalog</button>
                    <button className="login-button" onClick={() => switchTab("users")}>Nazad</button>
                </div>
            </div>
            <div className="user-details-coordinator">
                {
                    role === "koordinator" ? <CoordinatorTeams /> : <div />
                }
            </div>
        </form>
    )
}

export default UserDetails;
