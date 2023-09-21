

export default function DeleteCategoryConfirmation({ setDeleteCategoryConfirmation, categoryTitle }) {
    const handleDeleteCategory = async () => {
    }

    return (
        <div className="new-task-form-container">
            <p>Da li ste sigurni da želite obrisati kategoriju "{categoryTitle}"?</p>
            <div className="button-line"> 
                <button onClick={handleDeleteCategory}>Da</button>
                <button onClick={() => setDeleteCategoryConfirmation(false)}>Ne</button>
            </div>
        </div>
    )
}