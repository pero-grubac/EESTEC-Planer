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
    const [logs, setLogs] = useState([]);
    const navigate = useNavigate();


    const handleBoardViewClick = () => {
        navigate("../", { replace: true });
    }

    useEffect(() => {
        fetchLogs(team, teams);
    }, [team, teams]);

    const fetchLogs = async (team, teams) => {
        try {

            console.log("TEAM: " + team);
            var response;

            if (loggedUser.uloga === "Clan odbora") {
                response = await axios.get(`http://localhost:8080/clanodbora/logs`, {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                });
            }

            else {
                response = await axios.get(`http://localhost:8080/koordinator/logs/${teams[team].idTim}`, {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                });
            }

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            let counter = 1;
            setLogs((response.data).map(log => ({ ...log, idTable: counter++ })));

        } catch (error) {
            console.error("Error fetching requests:", error);
        }
    };

    let currentDate = new Date();
    let currentYear = currentDate.getFullYear();
    let startingYear = currentYear - 3;

    return (
        <div className="log-list stats-container">
            <div style={{ display: "flex" }}>
                <MonthlyTasksByUserByYearChart godina={currentYear} token={localStorage.getItem("token")} />
            </div>
            <div>
                <CustomBarChart token={localStorage.getItem("token")} />
            </div>
            <div>
                <MonthlyTasksByTeamByYearChart godina={currentYear} token={localStorage.getItem("token")} />
            </div>
            <div>
                <MonthlyTasksByYear godina={currentYear} id={loggedUser.idKorisnika} token={localStorage.getItem("token")} />
            </div>
            <div>
                <TasksPerUserInTeamChart godina={currentYear} id={teams[team].idTim} token={localStorage.getItem("token")} />
            </div>
            <div>
                <TotalNumberOfUsers token={localStorage.getItem("token")} />
            </div>
            <div>
                <StatsForTwoYears prva={currentYear - 1} druga={currentYear} id={teams[team].idTim} token={localStorage.getItem("token")} />
            </div>

            <div className="menu-buttons">
                <button
                    className="logout-button logs-button"
                    onClick={() => navigate('../teams/' + teams[team].naziv, { replace: true })}
                >
                    <div className="logs-button-icon"></div>
                </button>
                <button
                    className="logout-button back-button"
                >
                    <div className="back-button-icon"></div>
                </button>
                <button
                    className="logout-button leave-team-button"

                >
                    <div className="leave-team-button-icon"></div>
                </button>
                <button
                    className="logout-button settings-button"
                >
                    <div className="settings-button-icon"></div>
                </button>
                <button className="logout-button" >
                    <div className="logout-button-icon"></div>
                </button>
            </div>
        </div>
    );
};
