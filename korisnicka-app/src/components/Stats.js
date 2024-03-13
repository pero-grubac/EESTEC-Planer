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
import { html2pdf } from "html2pdf.js";
import html2canvas from "html2canvas";




export const Stats = ({ loggedUser, setLoggedUser, team, teams }) => {

  const contentRef = useRef(null);

  const navigate = useNavigate();


  const ExportDivToPDF = async () => {

    // OVO RADI ZA TEKST

    //var doc = new jsPDF();

    // var divContents = document.getElementById("stats-container").innerHTML;
    // var printWindow = window.open('', '', 'height=400, width=800');
    // printWindow.document.write('<html><head></head><body>');
    // printWindow.document.write(divContents);
    // printWindow.document.write('</body></html>');
    // printWindow.document.close();
    // printWindow.print();


    // var pdf = new jsPDF();
    // var htmlContent = printWindow.document.documentElement;

    // pdf.html(
    //   htmlContent,
    //   {
    //     callback: function (pdf) {
    //       pdf.save('test.pdf');
    //       printWindow.close();
    //     },
    //     x: 15,
    //     y: 15,
    //     width: 170
    //   }
    // );



    // const report = new jsPDF('portrait','pt','a4');
    //   report.html(document.querySelector('#stats-container')).then(() => {
    //       report.save('report.pdf');
    //   }



    // OVO SAMO NE RADI IZ NEKOG RAZLOGA

    // const content = contentRef.current;

    // const options = {
    //   filename: 'my-document.pdf',
    //   margin: 1,
    //   image: { type: 'jpeg', quality: 0.98 },
    //   html2canvas: { scale: 2 },
    //   jsPDF: {
    //     unit: 'in',
    //     format: 'letter',
    //     orientation: 'portrait',
    //   },
    // };

    // html2pdf().set(options).from(content).save();


    const downloadFileName = "a";

    const input = document.getElementById("stats-container");

    const canvas = await html2canvas(input, {
      scrollX: 0,
      scrollY: 0,
      allowTaint: true,
      useCORS: true,
      logging: false,
      height: input.scrollHeight,
      windowHeight: input.scrollHeight,
    })
      .then(function (canvas) {
        var imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF();
        const imgWidth = 210;
        const pageHeight = 295;
        const imgHeight = (imgWidth * canvas.height) / canvas.width;
        //pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);

        let position = 0;
        let heightLeft = imgHeight;
        while (heightLeft >= 0) {
          position = heightLeft - imgHeight;
          pdf.addPage();
          pdf.addImage(canvas, 'PNG', 0, position, imgWidth, imgHeight, '', 'FAST');
          heightLeft -= pageHeight;
        }
        pdf.save(`${downloadFileName}.pdf`);
      });




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
          onClick={ExportDivToPDF}
        >Preuzmite PDF</button>

      </div>
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
