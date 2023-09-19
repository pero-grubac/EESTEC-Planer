import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Settings() {
    const navigate = useNavigate();
    const [password, setPassword] = useState("");

    const handleBackClick = () => {
        navigate('/teams', { replace: true });
    }

    return (
        <div className="new-task-form-container">
            <button className="x-button" onClick={handleBackClick}>
                <div className="x-button-x"></div>
            </button>
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
            <button>Ažurirajte nalog</button>
        </div>
    )
}