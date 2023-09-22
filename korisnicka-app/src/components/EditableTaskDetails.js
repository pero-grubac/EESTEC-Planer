import { useState } from "react"
import DeleteTaskConfirmation from "./DeleteTaskConfirmation";
import axios from "axios";


export default function EditableTaskDetails({ setShowEditableTaskDetails, setShowTaskDetails, selectedTask,
    setItems, setCategories, setResult, getCategoriesAndItems, team, navigate }) {
    const [taskTitle, setTaskTitle] = useState(selectedTask.naslov);
    const [taskText, setTaskText] = useState(selectedTask.tekst);
    const [taskDeadline, setTaskDeadline] = useState(selectedTask.rok);

    const [deleteTaskConfirmation, setDeleteTaskConfirmation] = useState(false);

    const handleUpdateTaskClick = async () => {
        try {
            console.log(taskText, taskDeadline, selectedTask.idAutora, taskTitle, selectedTask.kategorija.idKategorija
            )
            const response = await axios.post("http://localhost:8080/zadatak/update",
                {
                    tekst: taskText,
                    rok: taskDeadline,
                    idZadatak: selectedTask.idZadatak,
                    naslov: taskTitle,
                    kategorija: {
                        idKategorija: selectedTask.kategorija.idKategorija
                    }
                },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                },
            )

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            setShowTaskDetails(false);
            setShowEditableTaskDetails(false);

            try {
                const result = await getCategoriesAndItems(team, navigate);

                setItems(result.tasks);
                setCategories(result.categories);
                setResult(result);
            } catch (error) {
                console.error(error);
            }

        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowEditableTaskDetails(false)}>
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
            <textarea className="text-area input-area" placeholder="Opis zadatka"
                value={taskText}
                onChange={(e) => setTaskText(e.target.value)}
            ></textarea>
            <div className="button-line">
                <button className="long-button" onClick={handleUpdateTaskClick}>Ažurirajte zadatak</button>
                <button className="long-button" onClick={() => setDeleteTaskConfirmation(true)}>Obrišite zadatak</button>
            </div>

            {
                deleteTaskConfirmation ? <DeleteTaskConfirmation selectedTask={selectedTask}
                    setDeleteTaskConfirmation={setDeleteTaskConfirmation}
                    setShowEditableTaskDetails={setShowEditableTaskDetails}
                    setShowTaskDetails={setShowTaskDetails}
                    setCategories={setCategories}
                    setItems={setItems}
                    setResult={setResult}
                    getCategoriesAndItems={getCategoriesAndItems}
                    team={team}
                    navigate={navigate}></DeleteTaskConfirmation> : <></>
            }
        </div>
    )
}