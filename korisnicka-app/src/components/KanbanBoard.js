import React, { useState } from "react";
import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import uuid from "uuid/v4";
import NewTaskForm from "./NewTaskForm";
import TaskDetails from "./TaskDetails";
import NewCategoryForm from "./NewCategoryForm";
import LeaveTeamConfirmation from "./LeaveTeamConfirmation";
import EditableTaskDetails from "./EditableTaskDetails";
import { useNavigate } from "react-router-dom";
import DeleteCategoryConfirmation from "./DeleteCategoryConfirmation";
import axios from "axios";

const getTasks = async (team, navigate) => {
  try {
    const response = await axios.get(`http://localhost:8080/zadatak/byTim/${team}`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })

    console.log(response.data);

    if( response.status === 403){
      localStorage.clear();
      navigate("/", {replace: true});
    }

    return response.data;

  } catch (error) {
  }
}

const itemsFromBackend = [
  { id: uuid(), naziv: "Uraditi fetch broja clanova", tekst: "aaaaaa", rok: null, taskIsAssigned: true }, // uuid() automatski dodjeljuje neki random id kako bi i trebalo na FE, ali moze i sa id iz baze, nije bitno
  { id: uuid(), naziv: "Ne zaboraviti time remaining polje", tekst: "bbbbbb", rok: null, taskIsAssigned: false },
  { id: uuid(), naziv: "Treba prikazati ime i prezime onoga ko radi zadatak", tekst: "cccccc", rok: null, taskIsAssigned: false },
  { id: uuid(), naziv: "(Mozda) Promijeniti boju zadatka ako je zaduzen", teskt: "dddddd", rok: null, taskIsAssigned: true },
  { id: uuid(), naziv: "Uljepsati prikaz zadatka", tekst: "1. uraditi nesto\n2. uraditi nesto drugo\n3. uraditi jos nesto\nneke napomene...", rok: null, taskIsAssigned: false }
];

const columnsFromBackend = {
  [uuid()]: {
    name: "Requested",
    items: itemsFromBackend
  },
  [uuid()]: {
    name: "To do",
    items: []
  },
  [uuid()]: {
    name: "In Progress",
    items: []
  },
  [uuid()]: {
    name: "Done",
    items: []
  }
};

function delay(time) {
  return new Promise(resolve => setTimeout(resolve, time));
}

const onDragEnd = (result, columns, setColumns) => {
  console.log("result: ", result);

  if (!result.destination) return;
  const { source, destination } = result;

  if (source.droppableId !== destination.droppableId) {
    const sourceColumn = columns[source.droppableId];
    const destColumn = columns[destination.droppableId];
    const sourceItems = [...sourceColumn.items];
    const destItems = [...destColumn.items];
    const [removed] = sourceItems.splice(source.index, 1);
    destItems.splice(destination.index, 0, removed);
    setColumns({
      ...columns,
      [source.droppableId]: {
        ...sourceColumn,
        items: sourceItems
      },
      [destination.droppableId]: {
        ...destColumn,
        items: destItems
      }
    });
  } else {
    const column = columns[source.droppableId];
    const copiedItems = [...column.items];
    const [removed] = copiedItems.splice(source.index, 1);
    copiedItems.splice(destination.index, 0, removed);
    setColumns({
      ...columns,
      [source.droppableId]: {
        ...column,
        items: copiedItems
      }
    });
  }
};

export default function KanbanBoard({ loggedUser, team, teams }) {

  const navigate = useNavigate(); 

  let tasksByCategory = getTasks(team, navigate);

  const currentTeam = loggedUser.timovi.filter(tim => {
    return tim.idTim === team 
  })
  
  const isKoordinator = loggedUser.idKorisnika === currentTeam[0].idKoordinator;
  const isClanOdbora = loggedUser.role === "Clan odbora";

  // console.log("Kooridnator: ", isKoordinator, "Clan odbora: ", isClanOdbora);

  const [columns, setColumns] = useState(columnsFromBackend);
  const [showNewTaskForm, setShowNewTaskForm] = useState(false);
  const [showTaskDetails, setShowTaskDetails] = useState(false);
  const [showNewCategory, setShowNewCategory] = useState(false);
  const [leaveTeamConfirmation, setLeaveTeamConfirmation] = useState(false);
  const [editableTaskDetails, setEditableTaskDetails] = useState(false);
  const [deleteCategoryConfirmation, setDeleteCategoryConfirmation] = useState(false);

  const [selectedTask, setSelectedTask] = useState(null);

  const handleNewTaskClick = async (columnId, column) => {
    setShowNewTaskForm(true);
  }

  const handleTaskClick = async (item) => {
    await delay(10);
    setSelectedTask(item);
    setShowTaskDetails(true);
  }

  const handleLogoutClick = () => {
    localStorage.clear();
    navigate('/', { replace: true });
  }

  const handleTeamViewClick = () => {
    navigate('/teams', { replace: true });
  }

  const handleAddCategoryClick = async () => {
    await delay(10);
    setShowNewCategory(true);
  }

  const handleLeaveTeamClick = async () => {
    await delay(10);
    setLeaveTeamConfirmation(true);
  }

  const handleSettingsClick = () => {
    navigate('/settings', { replace: true });
  }

  const handleDeleteCategory = () => {
    setDeleteCategoryConfirmation(true);
  }

  return (
    <div style={{ display: "flex", justifyContent: "center", height: "100%" }}>
      <div className="team-title-container">
        <h1>IT</h1>
      </div>
      <div className="team-num-members-container">
        <h3>Broj članova: 23</h3>
      </div>

      <DragDropContext
        onDragEnd={isClanOdbora && !isKoordinator ? () => { } : (result => onDragEnd(result, columns, setColumns))}
      >
        {Object.entries(columns).map(([columnId, column], index) => {
          return (
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center"
              }}
              key={columnId}
            >
              <h2>{column.name}</h2>
              <div style={{ margin: 8 }}>
                {
                  isKoordinator ? <button className="remove-category-button" onClick={handleDeleteCategory}>
                    <div className="remove-category-icon"></div>
                  </button> : <></>
                }
                <Droppable droppableId={columnId} key={columnId}>
                  {(provided, snapshot) => {
                    return (
                      <div
                        {...provided.droppableProps}
                        ref={provided.innerRef}
                        style={{
                          background: snapshot.isDraggingOver
                            ? "rgba(227, 158, 158, 0.5)"
                            : "rgba(255, 214, 214, 0.5)",
                          width: 250,
                          minHeight: 500,
                          justifyContent: "left",
                          padding: "0.5rem",
                          borderRadius: "15px"
                        }}
                      >
                        {column.items.map((item, index) => {
                          return (
                            <Draggable
                              key={item.id}
                              draggableId={item.id}
                              index={index}
                            >
                              {(provided, snapshot) => {
                                return (
                                  <div
                                    className="task"
                                    ref={provided.innerRef}
                                    {...provided.draggableProps}
                                    {...provided.dragHandleProps}
                                    style={{
                                      userSelect: "none",
                                      padding: 10,
                                      paddingBottom: "0.25rem",
                                      margin: "0 0 8px 0",
                                      minHeight: "50px",
                                      borderRadius: "15px",
                                      backgroundColor: snapshot.isDragging
                                        ? "#f2c9c9"
                                        : "white",
                                      color: "black",
                                      ...provided.draggableProps.style
                                    }}
                                  >
                                    {item.naziv}
                                    <div className="task-info-line">
                                      <button className="task-button"
                                        onClick={() => handleTaskClick(item)}>
                                        <div className="task-button-icon"></div>
                                      </button>
                                      <p className="task-countdown">
                                        <div className="countdown-icon"></div>
                                        6d 5h</p>
                                    </div>

                                  </div>
                                );
                              }}

                            </Draggable>
                          );
                        })}
                        {provided.placeholder}
                        {
                          isKoordinator ? <button className="plus-button" onClick={() => handleNewTaskClick(columnId, column)}>+</button>
                            :
                            (
                              isClanOdbora ? (
                                column.name === "Requested" ? <button className="plus-button" onClick={() => handleNewTaskClick(columnId, column)}>+</button>
                                  :
                                  <></>
                              )
                                :
                                <></>
                            )
                        }

                      </div>
                    );
                  }}
                </Droppable>
              </div>
            </div>
          );
        })}
      </DragDropContext>

      <div className="menu-buttons">
        <button className="logout-button back-button" onClick={handleTeamViewClick}>
          <div className="back-button-icon"></div>
        </button>
        <button className="logout-button leave-team-button" onClick={handleLeaveTeamClick}>
          <div className="leave-team-button-icon"></div>
        </button>
        <button className="logout-button settings-button" onClick={handleSettingsClick}>
          <div className="settings-button-icon"></div>
        </button>
        <button className="logout-button" onClick={handleLogoutClick}>
          <div className="logout-button-icon"></div>
        </button>
      </div>


      {
        isKoordinator ? <button className="plus-button add-category-button" onClick={handleAddCategoryClick}>+</button> : <></>
      }

      {
        showNewTaskForm ? <NewTaskForm setShowNewTaskForm={setShowNewTaskForm}></NewTaskForm> : <></>
      }
      {
        showTaskDetails ?
          <TaskDetails setShowTaskDetails={setShowTaskDetails} setEditableTaskDetails={setEditableTaskDetails}
            selectedTask={selectedTask} isKoordinator={isKoordinator}>
          </TaskDetails>
          : <></>
      }
      {
        editableTaskDetails ? <EditableTaskDetails selectedTask={selectedTask} setShowTaskDetails={setEditableTaskDetails}></EditableTaskDetails>
          : <></>
      }
      {
        showNewCategory ? <NewCategoryForm setShowNewCategory={setShowNewCategory}></NewCategoryForm> : <></>
      }
      {
        leaveTeamConfirmation ? <LeaveTeamConfirmation setLeaveTeamConfirmation={setLeaveTeamConfirmation}></LeaveTeamConfirmation> : <></>
      }
      {
        deleteCategoryConfirmation ? <DeleteCategoryConfirmation setDeleteCategoryConfirmation={setDeleteCategoryConfirmation}
          categoryTitle
        ></DeleteCategoryConfirmation> : <></>
      }

    </div>
  );

}