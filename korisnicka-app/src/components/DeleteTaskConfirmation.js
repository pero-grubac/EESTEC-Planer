

export default function DeleteTaskConfirmation({ setDeleteTaskConfirmation, taskTitle }) {
    const handleDeleteTask = async () => {
    }

    return (
        <div className="new-task-form-container">
            <p>Da li ste sigurni da želite obrisati zadatak "{taskTitle}"?</p>
            <div className="button-line"> 
                <button onClick={handleDeleteTask}>Da</button>
                <button onClick={() => setDeleteTaskConfirmation(false)}>Ne</button>
            </div>
        </div>
    )
}