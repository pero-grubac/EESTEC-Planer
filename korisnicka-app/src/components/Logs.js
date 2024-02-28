import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const Logs = ({ loggedUser, isKoordinator, isClanOdbora, setLoggedUser, team, teams }) => {
  //const [logs, setLogs] = useState([]);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();


  var logs = [
    {
      idLog: 1,
      idTable: 1,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 2,
      idTable: 2,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 3,
      idTable: 3,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 4,
      idTable: 4,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 5,
      idTable: 5,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 6,
      idTable: 6,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 7,
      idTable: 7,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 8,
      idTable: 8,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 9,
      idTable: 9,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 10,
      idTable: 10,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 21,
      idTable: 21,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 31,
      idTable: 31,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 11,
      idTable: 11,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 26,
      idTable: 26,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 36,
      idTable: 36,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 16,
      idTable: 16,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 28,
      idTable: 28,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 38,
      idTable: 38,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },
    {
      idLog: 18,
      idTable: 18,
      datum: 1203127,
      subjekat: "aaa",
      poruka: { tekstPoruke: "aaaaaaa" }
    },
    {
      idLog: 29,
      idTable: 29,
      datum: 1203127,
      subjekat: "bbb",
      poruka: { tekstPoruke: "bbbbbbb" }
    },
    {
      idLog: 39,
      idTable: 39,
      datum: 1203127,
      subjekat: "ccc",
      poruka: { tekstPoruke: "ccccccc" }
    },

  ]

  const handleBoardViewClick = () => {
    navigate("../", { replace: true });
  }

  function formatDateTime(isoDate) {
    const date = new Date(isoDate);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is zero-based
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${day}.${month}.${year} ${hours}:${minutes}:${seconds}`;
  }
  useEffect(() => {
    //fetchLogs();
  }, []);

  //   const fetchLogs = async () => {
  //     try {

  //       const response = await axios.get("http://localhost:8080/question/all", {
  //         headers: {
  //           "Content-Type": "application/json",
  //           Authorization: "Bearer " + localStorage.getItem("token"),
  //         },
  //       });

  //       if(response.status === 403){
  //         localStorage.clear();
  //         switchTab("login");
  //       }

  //       let counter = 1;
  //       setLogs((response.data).map(request => ({...request, idTable: counter++})));
  //     } catch (error) {
  //       console.error("Error fetching requests:", error);
  //     }
  //   };

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
                  : log.datum.toString().toLowerCase().includes(search) ||
                  log.subjekat.toLowerCase().includes(search) ||
                  log.poruka.tekstPoruke.toLowerCase().includes(search);
              })
              .map((log) => (
                <tr
                  key={log.idLog}
                  className="table-row"
                >
                  <th>{log.idTable}</th>
                  <td>{formatDateTime(log.datum)}</td>
                  <td>{log.subjekat}</td>
                  <td>{log.poruka.tekstPoruke}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>

      <div className="menu-buttons">
        <button
          className="logout-button logs-button"
          onClick={() => navigate('../teams/' + teams[team].naziv , { replace: true })}
        >
          <div className="logs-button-icon"></div>
        </button>
        <button
          className="logout-button back-button"
        >
          <div className="back-button-icon"></div>
        </button>
        <button
          className="logout-button leave-team-button"

        >
          <div className="leave-team-button-icon"></div>
        </button>
        <button
          className="logout-button settings-button"
        >
          <div className="settings-button-icon"></div>
        </button>
        <button className="logout-button" >
          <div className="logout-button-icon"></div>
        </button>
      </div>
    </div>
  );
};
