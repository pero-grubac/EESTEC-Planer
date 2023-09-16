import './App.css';
import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { TeamsMenu } from './components/TeamsMenu';
import { Login } from './components/Login.js';
import { useState } from 'react';
import { Routes, Route } from "react-router-dom";
import './index.css';




function App() {

  const[loggedUser, setLoggedUser] = useState(null);

  return (
    <div className='background'>
      <Routes>
        <Route path="/" element={<Login setLoggedUser={setLoggedUser}></Login>} />
        <Route path="/teams" element={<TeamsMenu loggedUser={loggedUser}/>} />
        <Route path="design" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
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
