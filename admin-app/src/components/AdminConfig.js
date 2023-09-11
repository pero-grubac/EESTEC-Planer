import { useState } from "react";
import axios from "axios";

function AdminConfig() {
  const [password, setPassword] = useState(null);
  const handleChage = async () => {
    const admin = await axios.post("http://localhost:8080/admins/update", {
      ime: "",
      prezime: "",
      korisnickoime: "",
      lozinka: password,
      email: "",
     // ovako trebas da adminov id ovdje stavis nekako ili da gore mi das njegoo korisnickoime jedno od to dvoje  ce posluziti idKorisnika: admin.idAdmin,
    });
    if (admin.status !== 200) console.error(admin);
  };
  return (
    <form className="user-details-container">
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
              type="text"
            ></input>
          </div>
        </div>

        <div className="user-details-buttons">
          <button className="login-button" type="submit">
            Sačuvaj
          </button>
        </div>
      </div>
    </form>
  );
}

export default AdminConfig;
