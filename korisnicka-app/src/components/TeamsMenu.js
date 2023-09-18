import classNames from "classnames";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Teams } from "./Teams";
import { TeamImages } from "./TeamImages";

export const TeamsMenu = ({ loggedUser, teams }) => {
    //console.log(loggedUser);

    const navigate = useNavigate();
    function delay(time) {
        return new Promise(resolve => setTimeout(resolve, time));
    }

    const [seed, setSeed] = useState(1);
    const reset = () => {
        setSeed(Math.random());
    }

    console.log(loggedUser);
    if (loggedUser === null) {
        console.log("aaaaaaaaaaaaa");
        navigate('/', { replace: true }); // ovo i dalje ne radi, ne znam zasto
    }

    // if (loggedUser.timovi.length !== 0) {
    //     teams.forEach((team, id) => {
    //         //console.log(loggedUser.timovi)
    //         const matchingTeam = loggedUser.timovi.find((tim) => team.naziv === tim.naziv);
    //         if (matchingTeam) {
    //             teams[id].aktivan = true;
    //         }
    //         //console.log(teams[id]);
    //     })
    // }

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
        await delay(1000);
        if (!team.aktivan) {
            teams[team.id].aktivan = true;
            reset();
        }
    }

    const handleLogoutClick = () => {
        navigate('/', { replace: true }); 
      }

    return (
        <div>
            <TeamImages></TeamImages>
            <Teams key={seed} teams={teams} teamClasses={teamClasses} handleJoinClick={handleJoinClick} handleTeamClick={handleTeamClick} />
            <button className="logout-button" onClick={handleLogoutClick}>Odjavi se</button>
        </div>
    )
}