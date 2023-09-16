import './App.css';
import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { TeamsMenu } from './components/TeamsMenu';
import { Login } from './components/Login.js';
import { useState } from 'react';
import { Routes, Route } from "react-router-dom";
import './index.css';




function App() {

  return (
    <div className='background'>
      <Routes>
        <Route path="/" element={<Login></Login>} />
        <Route path="/teams" element={<TeamsMenu/>} />
        <Route path="design" element={<KanbanBoard></KanbanBoard>} />
      </Routes>
    </div>
  )

  // return(


  //   <div>
  //     <KanbanBoard/>
  //   </div>
  // );
}

export default App;
