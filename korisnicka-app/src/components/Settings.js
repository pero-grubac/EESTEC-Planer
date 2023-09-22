import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AccountSettings from "./AccountSettings";

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
           <AccountSettings></AccountSettings>
        </div>
    )
}