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
const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWhhaWxvIiwiaWF0IjoxNzA5NzU1NDkxLCJleHAiOjE3MDk3NTcyOTF9.SpJG_w3Haa-Sk1sJxAeeN0ZLWWd_qBhK6O9YbAI13ZU";
const idKorisnika = 43;
const idTim = 7;
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
