import React, { useState } from "react";
import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import uuid from "uuid/v4";
import NewTaskForm from "./NewTaskForm";
import TaskDetails from "./TaskDetails";
import NewCategoryForm from "./NewCategoryForm";
import LeaveTeamConfirmation from "./LeaveTeamConfirmation";
import EditableTaskDetails from "./EditableTaskDetails";
import { useNavigate } from "react-router-dom";

const isKoordinator = true;
const isClanOdbora = false;

const itemsFromBackend = [
  { id: uuid(), naziv: "First task", tekst: "aaaaaa", rok: null }, // uuid() automatski dodjeljuje neki random id kako bi i trebalo na FE, ali moze i sa id iz baze, nije bitno
  { id: uuid(), naziv: "Second task", tekst: "bbbbbb", rok: null },
  { id: uuid(), naziv: "Third task", tekst: "cccccc", rok: null },
  { id: uuid(), naziv: "Fourth task", teskt: "dddddd", rok: null },
  { id: uuid(), naziv: "Fifth task", tekst: "eeeeee", rok: null }
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

export default function KanbanBoard() {
  const [columns, setColumns] = useState(columnsFromBackend);
  const [showNewTaskForm, setShowNewTaskForm] = useState(false);
  const [showTaskDetails, setShowTaskDetails] = useState(false);
  const [showNewCategory, setShowNewCategory] = useState(false);
  const [leaveTeamConfirmation, setLeaveTeamConfirmation] = useState(false);

  const [selectedTask, setSelectedTask] = useState(null);

  const navigate = useNavigate();

  const handleNewTaskClick = async (columnId, column) => {
    setShowNewTaskForm(true);
  }

  const handleTaskClick = async (item) => {
    await delay(10);
    setSelectedTask(item);
    setShowTaskDetails(true);
  }

  const handleLogoutClick = () => {
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

  return (
    <div style={{ display: "flex", justifyContent: "center", height: "100%" }}>
      <div className="team-title-container">
        <h1>Naziv tima</h1>
      </div>

      <DragDropContext
        onDragEnd={isClanOdbora ? () => { } : (result => onDragEnd(result, columns, setColumns))}
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
                                        onClick={() => handleTaskClick(item)}
                                      >Detalji</button>
                                      <p className="task-countdown">6d 5h</p>
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
          (
            isKoordinator ? <EditableTaskDetails setShowTaskDetails={setShowTaskDetails}
              selectedTask={selectedTask}
            ></EditableTaskDetails> :
              <TaskDetails setShowTaskDetails={setShowTaskDetails}
                selectedTask={selectedTask}
              ></TaskDetails>
          )
          : <></>
      }
      {
        showNewCategory ? <NewCategoryForm setShowNewCategory={setShowNewCategory}></NewCategoryForm> : <></>
      }
      {
        leaveTeamConfirmation ? <LeaveTeamConfirmation setLeaveTeamConfirmation={setLeaveTeamConfirmation}></LeaveTeamConfirmation> : <></>
      }

    </div>
  );

}