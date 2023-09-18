import { useState } from "react"


export default function EditableTaskDetails({setShowTaskDetails, selectedTask}) {
    const [taskTitle, setTaskTitle] = useState(selectedTask.naziv);
    const [taskText, setTaskText] = useState(selectedTask.tekst);
    const [taskDeadline, setTaskDeadline] = useState(selectedTask.rok);

    return(
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>X</button>
            <h2>Izmijeni zadatak:</h2>
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
            <button>Ažuriraj zadatak</button>
        </div>
    )
}