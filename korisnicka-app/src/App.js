import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { Login } from './components/Login.js';
import { useState } from 'react';
import { Routes, Route } from "react-router-dom";
import './index.css'; 




function App() {

  return (
    <Routes>
      <Route path="/" element={<Login></Login>} />
      <Route path="Design" element={<KanbanBoard></KanbanBoard>} />
    </Routes>
  )

  // return(


  //   <div>
  //     <KanbanBoard/>
  //   </div>
  // );
}

export default App;