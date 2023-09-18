import { useState } from "react";
import axios from "axios";

function AdminConfig({currentAdmin, switchTab}) {
  const [password, setPassword] = useState(null);
  const [confirmation, setConfirmation] = useState(false);
  const [error, setError] = useState(false);

  const handleChange = async (e) => {
    e.preventDefault();

    const admin = await axios.post("http://localhost:8080/admins/update", {
      headers: {
        'Content-Type': 'application/json',
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      ime: "",
      prezime: "",
      korisnickoime: currentAdmin.username,
      lozinka: password,
      email: "",
      idKorisnika: 0
      });
    if (admin.status !== 200) console.error(admin);

    if (admin.status === 403) {
      localStorage.clear();
      switchTab("login");
    }
    
    // podesi opet confirmation i error da bi se mogla prikazati poruka
  };
  return (
    <form className="user-details-container" onSubmit={handleChange}>
      <div className="user-details-basic">
        <h3>Podešavanja</h3>
        <div className="user-details-edit">
          <div className="user-details-column">
            <label htmlFor="sifra">Nova pristupna šifra</label>
          </div>
          <div className="user-details-column user-input-column">
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              id="password"
              type="password"
            ></input>
          </div>
        </div>

        <div className="user-details-buttons">
          <button className="login-button" type="submit">
            Sačuvaj
          </button>
        </div>
        {confirmation ? (
          <text>Podaci su uspješno ažurirani.</text>
        ) : (
          <text></text>
        )}
        {error ? (
          <text>Desila se greška u ažuriranju podataka</text>
        ) : (
          <text></text>
        )}
      </div>
    </form>
  );
}

export default AdminConfig;
