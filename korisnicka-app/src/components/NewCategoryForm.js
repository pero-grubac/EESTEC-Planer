import { useState } from "react";
import axios from "axios";

export default function NewCategoryForm({setShowNewCategory, navigate, refreshBoard, team}) {
    const [categoryTitle, setCategoryTitle] = useState("");

    const handleNewCategoryClick = async () => {
        try {
            const response = await axios.post("http://localhost:8080/category/create",
              {
                naziv: categoryTitle,
                timDTO: {
                  idTim: team
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

            refreshBoard();
          } catch (error) {
            console.error(error);
          }
    }

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
            <button onClick={handleNewCategoryClick}>Dodajte kategoriju</button>
        </div>
    )
}