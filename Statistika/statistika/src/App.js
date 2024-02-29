import React from "react";
import MonthlyTasksByTeamByYearChart from "./MonthlyTasksByTeamByYearChart";
/*
import MonthlyTasksByUserByYearChart from "./MonthlyTasksByUserByYearChart";
import CustomBarChart from "./TasksPerUserChart";
const godina = 2024;
 <div>
        <MonthlyTasksByUserByYearChart godina={godina} />
      </div>
      <div>
        <CustomBarChart />
      </div>
      */
     const godina = 2024;
function App() {
  return (
    <div>
     <MonthlyTasksByTeamByYearChart godina={godina}/>
    </div>
  );
}

export default App;
