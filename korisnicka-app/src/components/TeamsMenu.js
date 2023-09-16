import classNames from "classnames";
import { useNavigate } from "react-router-dom";

export const TeamsMenu = (props) => {
    console.log(props.loggedUser);
    const navigate = useNavigate();

    let teams = [
        {
            id: 1,
            naziv: "Design",
            aktivan: false
        },
        {
            id: 2,
            naziv: "IT",
            aktivan: false
        },
        {
            id: 3,
            naziv: "HR",
            aktivan: false
        },
        {
            id: 4,
            naziv: "PR",
            aktivan: false
        },
        {
            id: 5,
            naziv: "FR",
            aktivan: false
        }
    ]

    // nemoj ovako, nego vracaj u App.js da on setuje putanju
    if(props.loggedUser === null){
        setTimeout(() => {
            navigate('/', { replace: true });
        }, 3000);
    }

    if(props.loggedUser.timovi.length !== 0){
        teams.forEach((team, id) => {
            console.log(props.loggedUser.timovi)
            const matchingTeam = props.loggedUser.timovi.find((tim) => team.naziv === tim.naziv);
            if(matchingTeam){
                teams[id].aktivan = true;
            }
            console.log(teams[id]);
        })

        // for(let tim in props.loggedUser.timovi){
        //     teams.forEach(
        //         team => {
        //             if(team.naziv == tim.naziv){
        //                 team.aktivan = true;
        //             }
        //         }
        //     )
        // }

        console.log(teams);
    }

    var designTeamClass = classNames({
        'team-rectangle': true,
        'team-active': teams[0].aktivan,
        'team-inactive': !teams[0].aktivan
    });

    var itTeamClass = classNames({
        'team-rectangle': true,
        'team-active': teams[1].aktivan,
        'team-inactive': !teams[1].aktivan
    });

    var hrTeamClass = classNames({
        'team-rectangle': true,
        'team-active': teams[2].aktivan,
        'team-inactive': !teams[2].aktivan
    });

    var prTeamClass = classNames({
        'team-rectangle': true,
        'team-active': teams[3].aktivan,
        'team-inactive': !teams[3].aktivan
    });

    var frTeamClass = classNames({
        'team-rectangle': true,
        'team-active': teams[4].aktivan,
        'team-inactive': !teams[4].aktivan
    });

    const handleJoinClick = async (tim) => {
        // dodaj u tim

        teams[tim.id - 1].aktivan = true;
        console.log(teams[tim.id - 1].aktivan);

    }

    return (
        <div className="teams-container">
            <div className="teams-row">
                <div className={designTeamClass}>Dizajn
                    {!teams[0].aktivan ? <button onClick={() => handleJoinClick(teams[0])}>Pridruži se</button> : <></>}
                </div>
                <div className={itTeamClass}>IT
                    {!teams[1].aktivan ? <button onClick={() => handleJoinClick(teams[1])}>Pridruži se</button> : <></>}
                </div>
                <div className={hrTeamClass}>HR
                    {!teams[2].aktivan ? <button onClick={() => handleJoinClick(teams[2])}>Pridruži se</button> : <></>}
                </div>
            </div>
            <div className="teams-row">
                <div className={hrTeamClass}>PR
                    {!teams[3].aktivan ? <button onClick={() => handleJoinClick(teams[3])}>Pridruži se</button> : <></>}
                </div>
                <div className={prTeamClass}>FR
                    {!teams[4].aktivan ? <button onClick={() => handleJoinClick(teams[4])}>Pridruži se</button> : <></>}
                </div>
            </div>
        </div>

    )
}