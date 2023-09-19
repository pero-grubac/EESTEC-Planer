import { useState } from "react"
import DeleteTaskConfirmation from "./DeleteTaskConfirmation";


export default function EditableTaskDetails({ setShowTaskDetails, selectedTask }) {
    const [taskTitle, setTaskTitle] = useState(selectedTask.naziv);
    const [taskText, setTaskText] = useState(selectedTask.tekst);
    const [taskDeadline, setTaskDeadline] = useState(selectedTask.rok);

    const [deleteTaskConfirmation, setDeleteTaskConfirmation] = useState(false);

    return (
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>
                <div className="x-button-x"></div>
            </button>
            <h2>Izmijenite zadatak:</h2>
            <input className="new-task-input" placeholder="Naslov"
                value={taskTitle}
                onChange={(e) => setTaskTitle(e.target.value)}
            ></input>
            <input type="datetime-local" className="new-task-input"
                value={taskDeadline}
                onChange={(e) => setTaskDeadline(e.target.value)}
            ></input>
            <textarea className="input-area" placeholder="Opis zadatka"
                value={taskText}
                onChange={(e) => setTaskText(e.target.value)}
            ></textarea>
            <div className="button-line">
                <button className="long-button">Prijavite se</button>
                <button className="long-button">Ažurirajte zadatak</button>
                <button className="long-button" onClick={() => setDeleteTaskConfirmation(true)}>Obrišite zadatak</button>
            </div>

            {
                deleteTaskConfirmation ? <DeleteTaskConfirmation taskTitle={selectedTask.naziv}
                setDeleteTaskConfirmation={setDeleteTaskConfirmation}></DeleteTaskConfirmation> : <></>
            }
        </div>
    )
}