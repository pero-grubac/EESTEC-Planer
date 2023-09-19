

export default function TaskDetails({selectedTask, setShowTaskDetails}) {
    return(
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>
            <div className="x-button-x"></div>
            </button>
            <h2>Detalji:</h2>
            <p>{selectedTask.naziv}</p>
            <p>Do: {selectedTask.rok}</p>
            <p>{selectedTask.tekst}</p>
            <p>Radi: </p>
            <button className="long-button">Prijavite se</button>
        </div>
    )
}