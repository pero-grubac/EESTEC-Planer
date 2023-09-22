import axios from "axios";

export default function DeleteTaskConfirmation({ setDeleteTaskConfirmation, selectedTask,
setShowEditableTaskDetails, setShowTaskDetails,
setItems, setCategories, setResult, getCategoriesAndItems, team, navigate }) {
    const handleDeleteTask = async () => {
        try{
            const deleteTask = await axios.delete(
                `http://localhost:8080/zadatak/delete/${selectedTask.idZadatak}`,
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                }
            );

            setShowTaskDetails(false);
            setShowEditableTaskDetails(false);
            setDeleteTaskConfirmation(false);

            try {
                const result = await getCategoriesAndItems(team, navigate);

                setItems(result.tasks);
                setCategories(result.categories);
                setResult(result);
            } catch (error) {
                console.error(error);
            }

        }catch(error){
            console.error(error);
        }
    }

    return (
        <div className="new-task-form-container">
            <p>Da li ste sigurni da želite obrisati zadatak "{selectedTask.naslov}"?</p>
            <div className="button-line"> 
                <button onClick={handleDeleteTask}>Da</button>
                <button onClick={() => setDeleteTaskConfirmation(false)}>Ne</button>
            </div>
        </div>
    )
}