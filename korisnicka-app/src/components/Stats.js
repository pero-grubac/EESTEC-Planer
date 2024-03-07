import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import MonthlyTasksByTeamByYearChart from "./MonthlyTasksByTeamByYearChart";
import MonthlyTasksByUserByYearChart from "./MonthlyTasksByUserByYearChart";
import CustomBarChart from "./TasksPerUserChart";
import MonthlyTasksByYear from "./MonthlyTasksByYear";
import TasksPerUserInTeamChart from "./TasksPerUserInTeamChart";
import TotalNumberOfUsers from "./TotalNumberOfUsers";
import StatsForTwoYears from "./StatsForTwoYears";

export const Stats = ({ loggedUser, setLoggedUser, team, teams }) => {
  const navigate = useNavigate();

  const handleBoardViewClick = () => {
    navigate("../", { replace: true });
  };

  let currentDate = new Date();
  let currentYear = currentDate.getFullYear();
  let startingYear = currentYear - 3;

  return (
    <div className="log-list stats-container">
      <MonthlyTasksByUserByYearChart
        godina={currentYear}
        token={localStorage.getItem("token")}
      />
      <CustomBarChart token={localStorage.getItem("token")} />
      <MonthlyTasksByTeamByYearChart
        godina={currentYear}
        token={localStorage.getItem("token")}
      />
      <MonthlyTasksByYear
        godina={currentYear}
        id={loggedUser.idKorisnika}
        token={localStorage.getItem("token")}
      />
      <TasksPerUserInTeamChart
        godina={currentYear}
        id={teams[team].idTim}
        token={localStorage.getItem("token")}
      />
      <TotalNumberOfUsers token={localStorage.getItem("token")} />
      <StatsForTwoYears
        prva={currentYear - 1}
        druga={currentYear}
        id={teams[team].idTim}
        token={localStorage.getItem("token")}
      />

      <div className="menu-buttons">
        <button
          className="logout-button logs-button"
          onClick={() =>
            navigate("../teams/" + teams[team].naziv, { replace: true })
          }
        >
          <div className="logs-button-icon"></div>
        </button>
        <button className="logout-button back-button">
          <div className="back-button-icon"></div>
        </button>
        <button className="logout-button leave-team-button">
          <div className="leave-team-button-icon"></div>
        </button>
        <button className="logout-button settings-button">
          <div className="settings-button-icon"></div>
        </button>
        <button className="logout-button">
          <div className="logout-button-icon"></div>
        </button>
      </div>
    </div>
  );
};
