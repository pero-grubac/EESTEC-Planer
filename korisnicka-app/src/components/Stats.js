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
import jsPDF from "jspdf";


const exportDivToPDF = () => {
  var doc = new jsPDF();
  var source = window.document.getElementsByTagName("stats-container").innerHTML;
  doc.html(
    source,
    {
      'x': 15,
      'y': 15,
      'width': 200,
    });
  doc.save("test.pdf");
}

export const Stats = ({ loggedUser, setLoggedUser, team, teams }) => {

  const navigate = useNavigate();


  const handleBoardViewClick = () => {
    navigate("../", { replace: true });
  };

  let currentDate = new Date();
  let currentYear = currentDate.getFullYear();

  const currentTeam = loggedUser.timovi.filter((tim) => {
    return tim.idTim === teams[team].idTim;
  });

  const isKoordinator = loggedUser.idKorisnika === currentTeam[0].idKoordinator;
  const isClanOdbora = loggedUser.uloga === "Clan odbora";

  return (
    <div className="log-list stats-container" id="stats-container">
      {
        isClanOdbora ?
          <div>
            <MonthlyTasksByUserByYearChart
              godina={currentYear}
              token={localStorage.getItem("token")}
            />
            <MonthlyTasksByTeamByYearChart
              godina={currentYear}
              token={localStorage.getItem("token")}
            />
            <CustomBarChart
              width={500}
              className="tasks-per-user-bar-chart"
              token={localStorage.getItem("token")} />
          </div> : <div></div>
      }

      {
        isKoordinator ?
          <div>
            <TasksPerUserInTeamChart
              tim={teams[team]}
              godina={currentYear}
              id={teams[team].idTim}
              token={localStorage.getItem("token")}
            />
          </div> : <div></div>
      }

      <StatsForTwoYears
        tim={teams[team]}
        prva={currentYear - 1}
        druga={currentYear}
        id={teams[team].idTim}
        token={localStorage.getItem("token")}
      />

      <MonthlyTasksByYear
        godina={currentYear}
        id={loggedUser.idKorisnika}
        token={localStorage.getItem("token")}
      />

      {
        isClanOdbora ?
          <div>
            <TotalNumberOfUsers token={localStorage.getItem("token")} />
          </div> : <div></div>
      }

      <button
        onClick={exportDivToPDF}
      >Preuzmi</button>


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
