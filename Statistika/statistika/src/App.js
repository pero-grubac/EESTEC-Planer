import React from "react";
import MonthlyTasksByTeamByYearChart from "./MonthlyTasksByTeamByYearChart";
import MonthlyTasksByUserByYearChart from "./MonthlyTasksByUserByYearChart";
import CustomBarChart from "./TasksPerUserChart";
import MonthlyTasksByYear from "./MonthlyTasksByYear";
import TasksPerUserInTeamChart from "./TasksPerUserInTeamChart";
import TotalNumberOfUsers from "./TotalNumberOfUsers";
import StatsForTwoYears from "./StatsForTwoYears";
const godina = 2024;
const god = 2023;
const token =
"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWhhaWxvIiwiaWF0IjoxNzA5ODQ3OTY5LCJleHAiOjE3MDk4NDk3Njl9.dXq0UfWwidQqeZ98rMXWIhWNI1dgg686mim1QIdd_Xo";
const idKorisnika = 43;
const idTim = 7;
/*
<div style={{ display: "flex" }}>
        <MonthlyTasksByUserByYearChart godina={godina} token={token} />
      </div>
      <div>
        <CustomBarChart token={token} />
      </div>
      <div>
        <MonthlyTasksByTeamByYearChart godina={godina} token={token} />
      </div>
      <div>
        <MonthlyTasksByYear godina={godina} id={idKorisnika} token={token} />
      </div>
      <div>
        <TasksPerUserInTeamChart godina={godina} id={idTim} token={token} />
      </div>
      <div>
        <TotalNumberOfUsers token={token} />
      </div>
      <div>
        <StatsForTwoYears prva={god} druga={godina} id={idTim} token={token} />
      </div>
 */
function App() {
  return (
    <div>
       <div style={{ display: "flex" }}>
        <MonthlyTasksByUserByYearChart godina={godina} token={token} />
      </div>
      <div>
        <CustomBarChart token={token} />
      </div>
      <div>
        <MonthlyTasksByTeamByYearChart godina={godina} token={token} />
      </div>
      <div>
        <MonthlyTasksByYear godina={godina} id={idKorisnika} token={token} />
      </div>
      <div>
        <TasksPerUserInTeamChart godina={godina} id={idTim} token={token} />
      </div>
      <div>
        <TotalNumberOfUsers token={token} />
      </div>
      <div>
        <StatsForTwoYears prva={god} druga={godina} id={idTim} token={token} />
      </div>
      
    </div>
  );
}

export default App;
