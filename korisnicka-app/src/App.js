import './App.css';
import React from 'react';
import KanbanBoard from './components/KanbanBoard.js';
import { TeamsMenu } from './components/TeamsMenu';
import { Login } from './components/Login.js';
import { Logs } from './components/Logs.js';
import Settings from './components/Settings';
import { useState } from 'react';
import { Routes, Route, useNavigate } from "react-router-dom";
import './index.css';
import { Stats } from './components/Stats.js';




function App() {

  const [loggedUser, setLoggedUser] = useState(null);
  const [isAuthenticated, setUserIsAuthenticated] = useState(false);

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

  localStorage.setItem("teams", teams);

  return (
    <div className='background'>
      <Routes>
        {/* <Route path="/" element={<Comments></Comments>} /> */}
        <Route path="/" element={<Login setLoggedUser={setLoggedUser} setUserIsAuthenticated={setUserIsAuthenticated}></Login>} />
        <Route path="/teams" element={<TeamsMenu loggedUser={loggedUser} setLoggedUser={setLoggedUser} teams={teams} />}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/Design" element={<KanbanBoard loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={4} teams={teams}></KanbanBoard>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/IT" element={<KanbanBoard loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={8} teams={teams}></KanbanBoard>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/HR" element={<KanbanBoard loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={5} teams={teams}></KanbanBoard>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/PR" element={<KanbanBoard loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={6} teams={teams}></KanbanBoard>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/FR" element={<KanbanBoard loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={7} teams={teams}></KanbanBoard>}
          appProps={{ isAuthenticated }} />

        <Route path="/teams/Design/logs" element={<Logs loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={0} teams={teams}></Logs>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/IT/logs" element={<Logs loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={1} teams={teams}></Logs>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/HR/logs" element={<Logs loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={2} teams={teams}></Logs>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/PR/logs" element={<Logs loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={3} teams={teams}></Logs>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/FR/logs" element={<Logs loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={4} teams={teams}></Logs>}
          appProps={{ isAuthenticated }} />

        <Route path="/teams/Design/stats" element={<Stats loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={0} teams={teams}></Stats>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/IT/stats" element={<Stats loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={1} teams={teams}></Stats>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/HR/stats" element={<Stats loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={2} teams={teams}></Stats>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/PR/stats" element={<Stats loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={3} teams={teams}></Stats>}
          appProps={{ isAuthenticated }} />
        <Route path="/teams/FR/stats" element={<Stats loggedUser={loggedUser} setLoggedUser={setLoggedUser} team={4} teams={teams}></Stats>}
          appProps={{ isAuthenticated }} />

        <Route path="/settings" element={<Settings loggedUser={loggedUser}></Settings>} />
      </Routes>
    </div>
  )
}

export default App;
