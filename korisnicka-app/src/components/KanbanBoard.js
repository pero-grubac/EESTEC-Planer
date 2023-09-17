import React, { useState } from "react";
import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import uuid from "uuid/v4";
import NewTaskForm from "./NewTaskForm";
import TaskDetails from "./TaskDetails";

const itemsFromBackend = [
  { id: uuid(), content: "First task" },
  { id: uuid(), content: "Second task" },
  { id: uuid(), content: "Third task" },
  { id: uuid(), content: "Fourth task" },
  { id: uuid(), content: "Fifth task" }
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

  const handleNewTaskClick = async (columnId, column) => {
    setShowNewTaskForm(true);
  }

  return (
    <div style={{ display: "flex", justifyContent: "center", height: "100%" }}>
      <DragDropContext
        onDragEnd={result => onDragEnd(result, columns, setColumns)}
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
                          borderRadius: "3px"
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
                                    ref={provided.innerRef}
                                    {...provided.draggableProps}
                                    {...provided.dragHandleProps}
                                    style={{
                                      userSelect: "none",
                                      padding: 16,
                                      margin: "0 0 8px 0",
                                      minHeight: "50px",
                                      borderRadius: "5px",
                                      backgroundColor: snapshot.isDragging
                                        ? "#f2c9c9"
                                        : "white",
                                      color: "black",
                                      ...provided.draggableProps.style
                                    }}
                                  >
                                    {item.content}
                                  </div>
                                );
                              }}
                            </Draggable>
                          );
                        })}
                        {provided.placeholder}
                        <button onClick={() => handleNewTaskClick(columnId, column)}>+ Dodaj novi zadatak</button>
                      </div>
                    );
                  }}
                </Droppable>
              </div>
            </div>
          );
        })}
      </DragDropContext>

      <button className="logout-button back-button">&lt;&lt; Timovi</button> 
      <button className="logout-button">Odjavi se</button>
      
      {
        showNewTaskForm ? <NewTaskForm setShowNewTaskForm={setShowNewTaskForm}></NewTaskForm> : <></>
      }
      {
        showTaskDetails ? <TaskDetails></TaskDetails> : <></>
      }

    </div>
  );

}