import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AccountSettings({ loggedUser }) {
  const navigate = useNavigate();

  useEffect(() => {
    const refreshUser = async () => {
      if (!loggedUser) {
        let loggedUserId = window.atob(localStorage.getItem("userId"));
        try {
          const user = await axios.post(
            `http://localhost:8080/user/getById/${loggedUserId}`,
            {
              headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          );

          if (user.status === 403) {
            localStorage.clear();
            navigate('/', { replace: true });
          }

        } catch (error) {
          console.error(error);
        }
      }
    }

    refreshUser();
  }, []);



  const [password, setPassword] = useState("");

  const handleUpdateUser = async () => {
    try {
      const user = await axios.post(
        "http://localhost:8080/user/update",
        {
          ime: "",
          prezime: "",
          korisnickoime: "",
          lozinka: password,
          email: "",
          idKorisnika: loggedUser.idKorisnika,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      if (user.status === 403) {
        localStorage.clear();
        navigate('/', { replace: true });
      }

      navigate('/teams', { replace: true });

    } catch (error) {
      console.error(error);
    }
  }

  return (
    <div>
      <h2>Izmjena naloga:</h2>
      <div className="label-and-edit-field">
        <label htmlFor="new-password">Nova šifra:</label>
        <input className="new-task-input"
          id="new-password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        ></input>
      </div>
      <button onClick={handleUpdateUser}>Ažurirajte nalog</button>
    </div>
  )
}