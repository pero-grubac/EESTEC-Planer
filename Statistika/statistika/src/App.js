import React from "react";
import MonthyTasksByUserByYearChart from "./MonthyTasksByUserByYearChart";
const godina = 2024;

function App() {
  return (
    <div>
      <div>
        <MonthyTasksByUserByYearChart godina={godina} />
      </div>
    </div>
  );
}

export default App;
