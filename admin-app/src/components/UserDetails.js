import { useState } from "react";
import CoordinatorTeams from "./CoordinatorTeams";
import axios from "axios";

function UserDetails({ switchTab, selectedUser, selectedTeam, teams }) {
  const [name, setName] = useState(selectedUser.ime);
  const [surname, setSurname] = useState(selectedUser.prezime);
  const [username, setUsername] = useState(selectedUser.korisnickoIme);
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState(selectedUser.email);
  const [role, setRole] = useState(selectedUser.uloga);
  const [team, setTeam] = useState(selectedTeam ? selectedTeam.naziv : null);
  const [oldTeam, setoldTeam] = useState(
    selectedTeam ? selectedTeam.naziv : null
  );
  const [oldIdTeam, setOldIdTeam] = useState(
    selectedTeam ? selectedTeam.idTim : null
  );
  const [confirmation, setConfirmation] = useState(false);
  const [error, setError] = useState(false);

  var newKoordinator = true;
  var newClanOdbora = true;
  var newUser = true;

  function findIdTimByNaziv(teams, naziv) {
    for (let i = 0; i < teams.length; i++) {
      if (teams[i].naziv === naziv) return teams[i].idTim;
    }
  }
  //napravi tim i napravi korisnika
  const handleSubmit = async (e) => {
    e.preventDefault();
    // ako je sve isto nista ne radi
    if (
      !(
        name === selectedUser.ime &&
        surname === selectedUser.prezime &&
        username === selectedUser.korisnickoIme &&
        password === "" &&
        email === selectedUser.email &&
        role === selectedUser.uloga &&
        team === oldTeam
      )
    ) {
      try {
        if (role === "Koordinator" && team !== null) {
          // ako prije nisi bio koordinator
          if (role !== selectedUser.uloga) {
            const idNewTeam = findIdTimByNaziv(teams, team);
            const koordinator = await axios.post(
              "http://localhost:8080/koordinator/new",
              {
                idKorisnika: selectedUser.idKorisnika,
                idTim: idNewTeam,
              }
            );
            if (koordinator.status !== 200) newKoordinator = false;
          }
          // ako jesi koordinator a mijenjas tim
          if (role === selectedUser.uloga && team !== oldTeam) {
            const idNewTeam = findIdTimByNaziv(teams, team);

            const koordinator = await axios.post(
              "http://localhost:8080/koordinator/addToTeam",
              {
                idKorisnika: selectedUser.idKorisnika,
                idTim: idNewTeam,
              }
            );
            if (koordinator.status !== 200) newKoordinator = false;
          }
        } else if (role === "Clan odbora" && role !== selectedUser.uloga) {
          const clanOdbora = await axios.post(
            "http://localhost:8080/clanodbora/new",
            null,
            {
              params: {
                id: selectedUser.idKorisnika,
              },
            }
          );
          if (clanOdbora.status !== 200) newClanOdbora = false;
        } // obrisi da vise nije koordinator ili clan odbora
        else if (role === null) {
          if (selectedUser.uloga === "Clan odbora") {
            const clanOdbora = await axios.delete(
              `http://localhost:8080/clanodbora/delete/${selectedUser.idKorisnika}`
            );

            if (clanOdbora.status !== 204) newClanOdbora = false;
          } else if (selectedUser.uloga === "Koordinator") {
            const KorisnikTim = {
              idKorisnika: selectedUser.idKorisnika,
              idTim: oldIdTeam,
            };
            const koordinator = await axios.delete(
              `http://localhost:8080/koordinator/delete`,
              {
                data: KorisnikTim,
              }
            );
            if (koordinator.status !== 204) newKoordinator = false;
          }
        }


        const korisnik = await axios.post("http://localhost:8080/user/update", {
          ime: name,
          prezime: surname,
          korisnickoime: username,
          lozinka: password,
          email: email,
          idKorisnika: selectedUser.idKorisnika,
        });


        if (korisnik.status !== 200) newUser = false;
        if (newKoordinator && newClanOdbora && newUser) {
          setConfirmation(true);
          setError(false);
        } else {
          setConfirmation(false);
          setError(true);
        }
      } catch (error) {
        console.error(error);
      }
    }
  };

  return (
    <form className="user-details-container" onSubmit={handleSubmit}>
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
            <input
              value={name}
              onChange={(e) => setName(e.target.value)}
              id="ime"
              type="text"
            ></input>
            <input
              value={surname}
              onChange={(e) => setSurname(e.target.value)}
              id="prezime"
              type="text"
            ></input>
            <input
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              id="korisnicko-ime"
              type="text"
            ></input>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              id="email"
              type="text"
            ></input>
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              id="lozinka"
              type="text"
            ></input>
          </div>
        </div>
        <div className="uloge">
          <input
            className="checkbox"
            type="checkbox"
            id="koordinator"
            name="uloga"
            value="koordinator"
            checked={role === "Koordinator"}
            onChange={() =>
              role === "Koordinator" ? setRole(null) : setRole("Koordinator")
            }
          />
          <label htmlFor="koordinator">Koordinator</label>

          <input
            className="checkbox"
            type="checkbox"
            id="clan-upravnog-odbora"
            name="uloga"
            value="Clan odbora"
            checked={role === "Clan odbora"}
            onChange={() =>
              role === "Clan odbora" ? setRole(null) : setRole("Clan odbora")
            }
          />
          <label htmlFor="clan-upravnog-odbora">Član upravnog odbora</label>
        </div>

        <div className="user-details-buttons">
          <button className="login-button" type="submit">
            Sačuvaj
          </button>
          <button
            className="login-button"
            onClick={() => switchTab("user_del")}
          >
            Obriši nalog
          </button>
          <button className="login-button" onClick={() => switchTab("users")}>
            Nazad
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
      <div className="user-details-coordinator">
        {role === "Koordinator" ? (
          <CoordinatorTeams team={team} setTeam={setTeam} />
        ) : (
          <div />
        )}
      </div>
    </form>
  );
}

export default UserDetails;
