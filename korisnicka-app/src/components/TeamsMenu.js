import classNames from "classnames";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Teams } from "./Teams";
import { TeamImages } from "./TeamImages";
import axios from "axios";

export const TeamsMenu = ({ loggedUser, teams, setLoggedUser }) => {
    //console.log(loggedUser);

    const navigate = useNavigate();

    useEffect(() => {
        if (loggedUser === null) {
            localStorage.clear();
            navigate('/', { replace: true });
        }
    }, []);

    function delay(time) {
        return new Promise(resolve => setTimeout(resolve, time));
    }
    
    const [seed, setSeed] = useState(1);
    const reset = () => {
        setSeed(Math.random());
    }

    try {
        if (loggedUser.timovi.length !== 0) {
            teams.forEach((team, id) => {
                //console.log(loggedUser.timovi)
                const matchingTeam = loggedUser.timovi.find((tim) => team.naziv === tim.naziv);
                if (matchingTeam) {
                    teams[id].aktivan = true;
                }
                //console.log(teams[id]);
            })
        }
    } catch (error) {
    }

    let teamClasses = [];

    teams.forEach((team, id) => {
        teamClasses[id] = classNames({
            'team-rectangle': true,
            'team-active': team.aktivan,
            'team-inactive': !team.aktivan
        })
    })


    const handleTeamClick = async (team) => {
        if (team.aktivan)
            navigate('/teams/' + team.naziv, { replace: true });
    }

    const handleJoinClick = async (team) => {
        // dodaj u tim

        try {
            const response = await axios.put("http://localhost:8080/user/joinTeam",
                { idKorisnika: loggedUser.idKorisnika, idTim: teams[team.id].idTim },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                },
            )

        } catch (error) {
            console.error(error);
        }

        await delay(10);
        if (!team.aktivan) {
            teams[team.id].aktivan = true;
            reset();
        }

        try {
            const response = await axios.get(
                `http://localhost:8080/user/getById/${loggedUser.idKorisnika}`,
              {
                headers: {
                  "Content-Type": "application/json",
                  Authorization: "Bearer " + localStorage.getItem("token"),
                },
              }
            );
        
            console.log("fecovanje ", response.data);
        
            if (response.status === 403) {
              localStorage.clear();
              navigate("/", { replace: true });
            }

            setLoggedUser(response.data);

        } catch(error) {
            console.error(error);
        }
    }

    const handleLogoutClick = () => {
        localStorage.clear();
        navigate('/', { replace: true });
    }

    const handleSettingsClick = () => {
        navigate('/settings', { replace: true });
    }

    return (
        <div>
            <TeamImages></TeamImages>
            <Teams key={seed} teams={teams} teamClasses={teamClasses} handleJoinClick={handleJoinClick} handleTeamClick={handleTeamClick} />
            <div className="menu-buttons">
                <button className="logout-button settings-button" onClick={handleSettingsClick}>
                    <div className="settings-button-icon"></div>
                </button>
                <button className="logout-button" onClick={handleLogoutClick}>
                    <div className="logout-button-icon"></div>
                </button>
            </div>
        </div>
    )
}