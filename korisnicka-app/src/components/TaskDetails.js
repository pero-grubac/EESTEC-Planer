import { useState } from "react";
import AssignTaskStatusChangeMessage from "./AssignTaskStatusChangeMessage";
import axios from "axios";
import { Comments } from "./Comments";


function delay(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

export default function TaskDetails({ loggedUser, selectedTask, users, setShowTaskDetails, setEditableTaskDetails, isKoordinator, formatDate,
    refreshBoard, navigate }) {

    const [assignTaskConfirmation, setAssignTaskConfirmation] = useState(false);
    const [dropTaskConfirmation, setDropTaskConfirmation] = useState(false);
    let isAssignedByUser = false;

    let usersOnTask = [];

    users.forEach(user => user.zadaci.forEach(zadatak => zadatak.idZadatak === selectedTask.idZadatak ? usersOnTask.push(user) : {}));

    usersOnTask.forEach(user => user.idKorisnika === loggedUser.idKorisnika ? (isAssignedByUser = true) : {});

    let workingOnTask = "";
    let usersOnTaskNum = usersOnTask.length;

    usersOnTask.forEach(user => workingOnTask += user.ime + " " + user.prezime  + (--usersOnTaskNum > 0 ? ", " : ""));

    let author = "";
    users.forEach(user => (user.idKorisnika === selectedTask.idAutora) ? (author = user.ime + " " + user.prezime) : {});

    const handleEditTaskClick = () => {
        setEditableTaskDetails(true);
    }

    const handleArchiveTaskClick = async () => {
        try{
            console.log("task id: " + selectedTask.idZadatak);
            const response = await axios.put(
                `http://localhost:8080/zadatak/archive/${selectedTask.idZadatak}`,
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                }
            );

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            setAssignTaskConfirmation(true);
            delay(2000);
            setAssignTaskConfirmation(false);
            setShowTaskDetails(false);
            refreshBoard();
        } catch (error) {
            console.error(error);
        }
    }

    const handleAssignTaskClick = async () => {
        try {
            const response = await axios.put("http://localhost:8080/user/assign",
                {
                    idKorisnika: loggedUser.idKorisnika,
                    idZadatak: selectedTask.idZadatak
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

            setAssignTaskConfirmation(true);
            delay(2000);
            setAssignTaskConfirmation(false);
            setShowTaskDetails(false);
            refreshBoard();
        } catch (error) {
            console.error(error);
        }
    }

    const handleDropTaskClick = async () => {
        try {
            const response = await axios.put("http://localhost:8080/user/drop",
                {
                    idKorisnika: loggedUser.idKorisnika,
                    idZadatak: selectedTask.idZadatak
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

            setDropTaskConfirmation(true);
            delay(2000);
            setDropTaskConfirmation(false);
            setShowTaskDetails(false);
            refreshBoard();
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="new-task-form-container task-info-container">
            <div className="task-with-comments-container">
                <button className="x-button" onClick={() => setShowTaskDetails(false)}>
                    <div className="x-button-x"></div>
                </button>
                <h2>Detalji:</h2>
                <h3 className="text-background">{selectedTask.naslov}</h3>
                <h4>Autor: {author}</h4>
                <h4>Do: {selectedTask.rok ? formatDate(selectedTask.rok) : "--d --h"}</h4>
                <h4 className="text-area text-output">{selectedTask.tekst}</h4>
                <h4>Vrijeme kreiranja: {formatDate(selectedTask.datumKreiranja)}</h4>
                <h4>Rade: {workingOnTask}</h4>
                <div className="button-line">
                    {
                        isAssignedByUser ? <button className="long-button" onClick={handleDropTaskClick}>Odjavite se</button> :
                            <button className="long-button" onClick={handleAssignTaskClick}>Prijavite se</button>
                    }
                    {
                        isKoordinator ? <button className="long-button" onClick={handleEditTaskClick}>Izmijenite zadatak</button> : <></>
                    }
                    {
                        isKoordinator ? <button className="long-button" onClick={handleArchiveTaskClick}>Arhivirajte zadatak</button> : <></>
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
            <Comments loggedUser={loggedUser} task={selectedTask}></Comments>
        </div>



    )
}