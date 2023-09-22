import axios from "axios";

export default function DeleteCategoryConfirmation({ setDeleteCategoryConfirmation, selectedCategoryTitle, selectedCategoryId,
    navigate, refreshBoard }) {
    const handleDeleteCategory = async () => {

        try {
            const response = await axios.delete(`http://localhost:8080/category/delete/${selectedCategoryId}`,
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

            refreshBoard();
            setDeleteCategoryConfirmation(false);

        } catch (error) {
            console.error(error);
        }

    }

    return (
        <div className="new-task-form-container">
            <p>Da li ste sigurni da želite obrisati kategoriju "{selectedCategoryTitle}"?</p>
            <div className="button-line">
                <button onClick={handleDeleteCategory}>Da</button>
                <button onClick={() => setDeleteCategoryConfirmation(false)}>Ne</button>
            </div>
        </div>
    )
}