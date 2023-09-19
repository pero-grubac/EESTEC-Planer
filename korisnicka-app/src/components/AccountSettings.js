import { useState } from "react";

export default function AccountSettings({ password, setPassword }) {

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
            <button>Ažurirajte nalog</button>
        </div>
    )
}