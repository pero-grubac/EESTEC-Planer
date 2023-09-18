

export default function TaskDetails({selectedTask, setShowTaskDetails}) {
    return(
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>X</button>
            <h2>Detalji:</h2>
            <p>{selectedTask.naziv}</p>
            <p>Do: {selectedTask.rok}</p>
            <p>{selectedTask.tekst}</p>
            <p>Radi: </p>
        </div>
    )
}