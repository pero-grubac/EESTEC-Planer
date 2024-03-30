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
import { useRef } from "react";
import { formatDateTime } from "../utils/formatDateTime";




export const Stats = ({ loggedUser, setLoggedUser, team, teams }) => {

  const contentRef = useRef(null);

  const navigate = useNavigate();

  const handleLogoutClick = () => {
    localStorage.clear();
    navigate("../", { replace: true });
  };

  const handleTeamViewClick = () => {
    navigate("../teams", { replace: true });
  };

  const handleSettingsClick = () => {
    navigate("../settings/", { replace: true });
  };

  const handleBackClick = () => {
    navigate("../teams/" + teams[team].naziv, { replace: true });
  }


  const ExportDivToPDF = () => {

    const printCSS = "@page {margin: 10%; size: A4;}  body {margin: 4rem; margin-left: 10%; margin-right: 10%}";

    var doc = new jsPDF();

    const date = new Date();
    const formattedDate = formatDateTime(date);

    var divContents = document.getElementById("stats-container").innerHTML;
    var printWindow = window.open('', '', 'height=400, width=800');
    printWindow.document.write('<html><head><style>' + printCSS + '</style></head><body>');
    printWindow.document.write('<h1>Izvještaj</h1>');
    printWindow.document.write(divContents);
    printWindow.document.write('<p>Izvještaj generisan '+ formattedDate +'.</p>');
    printWindow.document.write('</body></html>');
    printWindow.print();
    printWindow.close();

  };



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
    <div>
      <div className="log-list stats-container">
        <div id="stats-container">
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

        </div>

        <button
          onClick={ExportDivToPDF}
        >Preuzmite PDF</button>

      </div>
      <div className="menu-buttons">
        <button
          className="logout-button logs-button"
          onClick={handleBackClick}
        >
          <div className="stats-button-icon"></div>
        </button>
        <button className="logout-button back-button"
          onClick={handleTeamViewClick}
        >
          <div className="back-button-icon"></div>
        </button>
        <button className="logout-button settings-button"
          onClick={handleSettingsClick}
        >
          <div className="settings-button-icon"></div>
        </button>
        <button className="logout-button"
          onClick={handleLogoutClick}
        >
          <div className="logout-button-icon"></div>
        </button>
      </div>
    </div>
  );
};
