import { useState } from "react";

function UserDetails({ switchTab, selectedUser }) {
    const [name, setName] = useState(selectedUser.ime);
    const [surname, setSurname] = useState(selectedUser.prezime);
    const [username, setUsername] = useState(selectedUser.korisnickoIme);
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState(selectedUser.email);
    const [role, setRole] = useState(selectedUser.uloga);

const handleDeleteUser = () => {
        switchTab("user_del");
    };
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
// TO DO
/*
    poravnaj chechbox
    dodaj tim i koordinatora

*/
                </div>
                <div className="user-details-column user-input-column">
                    <input value={name} onChange={(e) => setName(e.target.value)} id="ime" type="text"></input>
                    <input value={surname} onChange={(e) => setSurname(e.target.value)} id="prezime" type="text"></input>
                    <input value={username} onChange={(e) => setUsername(e.target.value)} id="korisnicko-ime" type="text"></input>
                    <input value={email} onChange={(e) => setEmail(e.target.value)} id="email" type="text"></input>
                    <input value={password} onChange={(e) => setPassword(e.target.value)} id="lozinka" type="text"></input>

                    {/* Radio buttons */}
                    <div>
                        <input
                            type="radio"
                            id="koordinator"
                            name="uloga"
                            value="koordinator"
                            checked={role === "koordinator"}
                            onChange={() => setRole("koordinator")}
                        />
                        <label htmlFor="koordinator">Koordinator</label>

                        <input
                            type="radio"
                            id="clan-upravnog-odbora"
                            name="uloga"
                            value="clan_upravnog_odbora"
                            checked={role === "clan_upravnog_odbora"}
                            onChange={() => setRole("clan_upravnog_odbora")}
                        />
                        <label htmlFor="clan-upravnog-odbora">Član upravnog odbora</label>
                         <input
                                                    type="radio"
                                                    id="korisnik"
                                                    name="uloga"
                                                    value="korisnik"
                                                    checked={role === "korisnik"}
                                                    onChange={() => setRole("korisnik")}
                                                />
                                                <label htmlFor="korisnik">Korisnik</label>
                    </div>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button" type="submit">Sačuvaj</button>
                <button className="login-button" onClick={() => switchTab("user_del")}>Obriši nalog</button>
                <button className="login-button" onClick={() => switchTab("users")}>Nazad</button>
            </div>
        </form>
    )
}

export default UserDetails;
