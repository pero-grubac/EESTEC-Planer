import classNames from "classnames";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Teams } from "./Teams";

export const TeamsMenu = ({loggedUser, teams}) => {
    //console.log(loggedUser);
    const navigate = useNavigate();

    const [seed, setSeed] = useState(1);
    const reset = () => {
        setSeed(Math.random());
    }

    console.log(loggedUser);
    useEffect(() => {
        if (loggedUser === null) {
            setTimeout(() => {
                navigate('/', { replace: true });
            }, 3000);
        }
    }, []);

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

    let teamClasses = [];

    teams.forEach((team, id) => {
        teamClasses[id] = classNames({
            'team-rectangle': true,
            'team-active': team.aktivan,
            'team-inactive': !team.aktivan
        })
    })

    const handleJoinClick = async (tim) => {
        // dodaj u tim

        teams[tim.id].aktivan = true;
        console.log("tim: " + teams[tim.id].naziv + teams[tim.id].aktivan);
        //window.location.reload(false);
        console.log(teams);
        reset();
    }

    return (
        <Teams key={seed} teams={teams} teamClasses={teamClasses} handleJoinClick={handleJoinClick} />

    )
}