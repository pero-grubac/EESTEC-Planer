import { useState } from "react";
import axios from "axios";

function AdminConfig({currentAdmin}) {
  const [password, setPassword] = useState(null);
  const [confirmation, setConfirmation] = useState(false);
  const [error, setError] = useState(false);

  const handleChange = async (e) => {
    e.preventDefault();
    //console.log(currentAdmin);
    const admin = await axios.post("http://localhost:8080/admins/update", {
      ime: "",
      prezime: "",
      korisnickoime: "",
      lozinka: password,
      email: "",
      idKorisnika: currentAdmin.id
      });
    if (admin.status !== 200) console.error(admin);
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
