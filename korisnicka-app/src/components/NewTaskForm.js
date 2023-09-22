import { useState } from "react";
import axios from "axios";

function delay(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

export default function NewTaskForm({ setShowNewTaskForm, categoryId, loggedUserId,
    setItems, setCategories, setResult, getCategoriesAndItems, team, navigate }) {
    const [taskTitle, setTaskTitle] = useState("");
    const [taskText, setTaskText] = useState("");
    const [taskDeadline, setTaskDeadline] = useState("");

    const handleSubmitNewTask = async () => {
        console.log("deadline: ", taskDeadline);
        try {
            const createTask = await axios.post(
                "http://localhost:8080/zadatak/create",
                {
                    tekst: taskText,
                    rok: taskDeadline,
                    idAutora: loggedUserId,
                    naslov: taskTitle,
                    kategorija: {
                        idKategorija: categoryId
                    }
                },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                }
            );

            if (createTask.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            setShowNewTaskForm(false);

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
            <button onClick={handleSubmitNewTask}>Dodajte zadatak</button>
        </div>
    )
}