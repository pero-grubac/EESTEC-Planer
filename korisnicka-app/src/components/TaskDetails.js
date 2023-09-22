import { useState } from "react";


export default function TaskDetails({ selectedTask, setShowTaskDetails, setEditableTaskDetails, isKoordinator, formatDate }) {

    const [assignTaskConfirmation, setAssignTaskConfirmation] = useState(false);

    const handleEditTaskClick = () => {
        setEditableTaskDetails(true);
    }

    const handleAssignTaskClick = () => {

    }

    return (
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>
                <div className="x-button-x"></div>
            </button>
            <h2>Detalji:</h2>
            <h3>{selectedTask.naslov}</h3>
            <h4>Do: {formatDate(selectedTask.rok)}</h4>
            <h4 className="text-area text-output">{selectedTask.tekst}</h4>
            <h4>Vrijeme kreiranja: {formatDate(selectedTask.rok)}</h4>
            <h4>Rade: </h4>
            <div className="button-line">
                <button className="long-button" onClick={handleAssignTaskClick}>Prijavite se</button>
                <button className="long-button">Odjavite se</button>
                {
                    isKoordinator ? <button className="long-button" onClick={handleEditTaskClick}>Izmijenite zadatak</button> : <></>
                }
            </div>

        </div>
    )
}