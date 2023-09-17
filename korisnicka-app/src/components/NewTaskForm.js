
export default function NewTaskForm({setShowNewTaskForm}) {
    return(
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowNewTaskForm(false)}>X</button>
            <h2>Novi zadatak:</h2>
            <input className="new-task-input" placeholder="Naslov"></input>
            <input type="datetime-local" className="new-task-input"></input>
            <textarea className="input-area" placeholder="Opis zadatka"></textarea>
            <button>Dodaj zadatak</button>
        </div>
    )
}