import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { formatDateTime } from '../utils/formatDateTime';

export const Logs = ({ loggedUser, setLoggedUser, team, teams }) => {
  const [logs, setLogs] = useState([]);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();


  const handleLogoutClick = () => {
    localStorage.clear();
    navigate("../", { replace: true });
  };

  const handleTeamViewClick = () => {
    navigate("../teams", { replace: true });
  };

  const handleSettingsClick = () => {
    navigate("../settings/", { replace: true });
  };

  const handleBackClick = () => {
    navigate("../teams/" + teams[team].naziv, { replace: true });
  }

  useEffect(() => {
    fetchLogs(team, teams);
  }, [team, teams]);

  const fetchLogs = async (team, teams) => {
    try {
      var response;

      if (loggedUser.uloga === "Clan odbora") {
        response = await axios.get(`http://localhost:8080/clanodbora/logs`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        });
      }

      else {
        response = await axios.get(`http://localhost:8080/koordinator/logs/${teams[team].idTim}`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        });
      }

      if (response.status === 403) {
        localStorage.clear();
        navigate("/", { replace: true });
      }

      let counter = 1;
      setLogs((response.data).map(log => ({ ...log, idTable: counter++ })));
      
    } catch (error) {
      console.error("Error fetching requests:", error);
    }
  };

  return (
    <div className="log-list">
      <h2>Logovi</h2>
      <div className="search">
        <input
          onChange={(e) => setSearch(e.target.value)}
          placeholder="Pretraga"
        ></input>
      </div>
      <div className=" table-wrapper table-wrapper-users">
        <table className="my-table">
          <thead>
            <tr>
              <th className="table-header">#</th>
              <th className="table-header">Vrijeme</th>
              <th className="table-header">Korisnik</th>
              <th className="table-header">Poruka</th>
            </tr>
          </thead>
          <tbody>
            {logs
              .filter((log) => {
                return search.toLowerCase() === ""
                  ? log
                  : formatDateTime(log.datum).toString().toLowerCase().includes(search) ||
                  log.subjekat.toLowerCase().includes(search) ||
                  log.tekstPoruke.toLowerCase().includes(search);
              })
              .map((log) => (
                <tr
                  key={log.idLog}
                  className="table-row"
                >
                  <th>{log.idTable}</th>
                  <td>{formatDateTime(log.datum)}</td>
                  <td>{log.subjekat}</td>
                  <td>{log.tekstPoruke}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>

      <div className="menu-buttons">
        <button
          className="logout-button logs-button"
          onClick={handleBackClick}
        >
          <div className="logs-button-icon"></div>
        </button>
        <button
          className="logout-button back-button"
          onClick={handleTeamViewClick}
        >
          <div className="back-button-icon"></div>
        </button>
        <button
          className="logout-button settings-button"
          onClick={handleSettingsClick}
        >
          <div className="settings-button-icon"></div>
        </button>
        <button 
          className="logout-button"
          onClick={handleLogoutClick}
        >
          <div className="logout-button-icon"></div>
        </button>
      </div>
    </div>
  );
};
