import { useState } from "react";

export default function NewTaskForm({setShowNewTaskForm}) {
    const [taskTitle, setTaskTitle] = useState("");
    const [taskText, setTaskText] = useState("");
    const [taskDeadline, setTaskDeadline] = useState("");

    return(
        <div className="new-task-form-container">
             <button className="x-button" onClick={() => setShowNewTaskForm(false)}>
             <div className="x-button-x"></div>
             </button>
            <h2>Novi zadatak:</h2>
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
            <button>Dodajte zadatak</button>
        </div>
    )
}