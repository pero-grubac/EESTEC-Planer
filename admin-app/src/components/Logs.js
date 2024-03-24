import { useState, useEffect } from "react";
import { Table } from "react-bootstrap";
import axios from "axios";

function formatDateTime(isoDate) {
  const date = new Date(isoDate);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return `${day}.${month}.${year} ${hours}:${minutes}:${seconds}`;
}

export const Logs = ({switchTab}) => {
  const [logs, setLogs] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    fetchLogs();
  }, []);

  const fetchLogs = async () => {
    try {
      var response;

      response = await axios.get(`http://localhost:8080/admins/logs`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });

      if (response.status === 403) {
        localStorage.clear();
        switchTab("login");
      }

      let counter = 1;
      setLogs((response.data).map(log => ({ ...log, idTable: counter++ })));

    } catch (error) {
      console.error("Error fetching requests:", error);
    }
  };

  return (
    <div className="user-list">
      <div className="search">
        <input
          onChange={(e) => setSearch(e.target.value)}
          placeholder="Pretraga"
        ></input>
      </div>

      <h2>Logovi</h2>
      <div className=" table-wrapper table-wrapper-users">
        <Table className="my-table">
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
        </Table>
      </div>
    </div>
  );
};
