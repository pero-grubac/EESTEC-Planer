import { useState } from "react";

function AdminConfig() {
  const [password, setPassword] = useState(null);

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
