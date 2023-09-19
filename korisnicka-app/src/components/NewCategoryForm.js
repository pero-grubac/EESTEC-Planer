import { useState } from "react";

export default function NewCategoryForm({setShowNewCategory}) {
    const [categoryTitle, setCategoryTitle] = useState("");

    return(
        <div className="new-task-form-container">
             <button className="x-button" onClick={() => setShowNewCategory(false)}>
             <div className="x-button-x"></div>
             </button>
            <h2>Nova kategorija:</h2>
            <input className="new-task-input" placeholder="Naziv kategorije"
                value={categoryTitle}
                onChange={(e) => setCategoryTitle(e.target.value)}
            ></input>
            <button>Dodajte kategoriju</button>
        </div>
    )
}