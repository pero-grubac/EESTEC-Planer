import './App.css';
import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { TeamsMenu } from './components/TeamsMenu';
import { Login } from './components/Login.js';
import { useState } from 'react';
import { Routes, Route, useNavigate } from "react-router-dom";
import './index.css';




function App() {

  const[loggedUser, setLoggedUser] = useState(null);

  let teams = [
    {
        id: 0,
        naziv: "Design",
        aktivan: false
    },
    {
        id: 1,
        naziv: "IT",
        aktivan: false
    },
    {
        id: 2,
        naziv: "HR",
        aktivan: false
    },
    {
        id: 3,
        naziv: "PR",
        aktivan: false
    },
    {
        id: 4,
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
