import { useState, useEffect } from "react";
import { Table } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import axios from "axios";

const UserList = ({ switchTab, selectRequest }) => {
  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState("");

  function formatDateTime(isoDate) {
    const date = new Date(isoDate);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is zero-based
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${month}/${day}/${year} ${hours}:${minutes}:${seconds}`;
  }
  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      
      const response = await axios.get("http://localhost:8080/question/all", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching requests:", error);
    }
  };

  const handleRequestSelect = (request) => {
    switchTab("request");
    selectRequest(request);
  };

  return (
    <div className="user-list">
      <h2 className="table-heading">Zahtjevi za naloge</h2>
      <div className="table-wrapper">
        <Table hover className="table table-bordered my-table">
          <thead>
            <tr>
              <th className="table-header"></th>
              <th className="table-header">Korisničko ime</th>
              <th className="table-header">Ime</th>
              <th className="table-header">Prezime</th>
              <th className="table-header">Vrijeme slanja zahtjeva</th>
            </tr>
          </thead>
          <tbody>
            {users.map((korisnik) => (
              <tr
                key={korisnik.id}
                className="table-row"
                onClick={() => handleRequestSelect(korisnik)}
              >
                <th scope="row">{korisnik.idZahtjev}</th>
                <td>{korisnik.korisnickoIme}</td>
                <td>{korisnik.ime}</td>
                <td>{korisnik.prezime}</td>
                <td>{formatDateTime(korisnik.datumKreiranja)}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default UserList;
