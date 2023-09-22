import { useState } from "react";
import AssignTaskStatusChangeMessage from "./AssignTaskStatusChangeMessage";


function delay(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

export default function TaskDetails({ selectedTask, setShowTaskDetails, setEditableTaskDetails, isKoordinator, formatDate }) {

    const [assignTaskConfirmation, setAssignTaskConfirmation] = useState(false);
    const [dropTaskConfirmation, setDropTaskConfirmation] = useState(false);
    const [isAssignedByUser, setIsAssignedByUser] = useState(false);

    const handleEditTaskClick = () => {
        setEditableTaskDetails(true);
    }

    const handleAssignTaskClick = async () => {
        try {
            // update u bazi

            setAssignTaskConfirmation(true);
            delay(2000);
            setAssignTaskConfirmation(false);
            setShowTaskDetails(false);
        } catch (error) {
            console.error(error);
        }
    }

    const handleDropTaskClick = async () => {
        try {
            // update u bazi

            setDropTaskConfirmation(true);
            delay(2000);
            setDropTaskConfirmation(false);
            setShowTaskDetails(false);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="new-task-form-container">
            <button className="x-button" onClick={() => setShowTaskDetails(false)}>
                <div className="x-button-x"></div>
            </button>
            <h2>Detalji:</h2>
            <h3 className="text-background">{selectedTask.naslov}</h3>
            <h4>Do: {formatDate(selectedTask.rok)}</h4>
            <h4 className="text-area text-output">{selectedTask.tekst}</h4>
            <h4>Vrijeme kreiranja: {formatDate(selectedTask.rok)}</h4>
            <h4>Rade: </h4>
            <div className="button-line">
                {
                    isAssignedByUser ? <button className="long-button" onClick={handleDropTaskClick}>Odjavite se</button> :
                        <button className="long-button" onClick={handleAssignTaskClick}>Prijavite se</button>
                }
                {
                    isKoordinator ? <button className="long-button" onClick={handleEditTaskClick}>Izmijenite zadatak</button> : <></>
                }
            </div>
            {
                assignTaskConfirmation ? <AssignTaskStatusChangeMessage message={"Prijavljeni ste na zadatak!"}>
                </AssignTaskStatusChangeMessage> : <></>
            }
            {
                dropTaskConfirmation ? <AssignTaskStatusChangeMessage message={"Odjavljeni ste sa zadatka!"}>
                </AssignTaskStatusChangeMessage> : <></>
            }
        </div>
    )
}