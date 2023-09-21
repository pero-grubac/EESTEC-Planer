import './App.css';
import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { TeamsMenu } from './components/TeamsMenu';
import { Login } from './components/Login.js';
import Settings from './components/Settings';
import { useState } from 'react';
import { Routes, Route, useNavigate } from "react-router-dom";
import './index.css';




function App() {

  const[loggedUser, setLoggedUser] = useState(null);

  let teams = [
    {
        id: 0,
        idTim: 4,
        naziv: "Design",
        aktivan: false
    },
    {
        id: 1,
        idTim: 8,
        naziv: "IT",
        aktivan: false
    },
    {
        id: 2,
        idTim: 5,
        naziv: "HR",
        aktivan: false
    },
    {
        id: 3,
        idTim: 6,
        naziv: "PR",
        aktivan: false
    },
    {
        id: 4,
        idTim: 7,
        naziv: "FR",
        aktivan: false
    }
]

  return (
    <div className='background'>
      <Routes>
        <Route path="/" element={<Login setLoggedUser={setLoggedUser}></Login>} />
        <Route path="/teams" element={<TeamsMenu loggedUser={loggedUser} teams={teams}/>} />
        <Route path="/teams/Design" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
        <Route path="/teams/IT" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
        <Route path="/teams/HR" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
        <Route path="/teams/PR" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
        <Route path="/teams/FR" element={<KanbanBoard loggedUser={loggedUser}></KanbanBoard>} />
        <Route path="/settings" element={<Settings></Settings>} />
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
