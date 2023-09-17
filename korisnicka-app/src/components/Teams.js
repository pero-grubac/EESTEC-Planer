
export const Teams = ({teams, teamClasses, handleJoinClick, handleTeamClick}) => {
    return (
        <div className="teams-container">
            <div className="teams-row">
                <div className={teamClasses[0]} onClick={() => handleTeamClick(teams[0])}>Dizajn
                    {!teams[0].aktivan ? <button onClick={() => handleJoinClick(teams[0])}>Pridruži se</button> : <></>}
                </div>
                <div className={teamClasses[1]} onClick={() => handleTeamClick(teams[1])}>IT
                    {!teams[1].aktivan ? <button onClick={() => handleJoinClick(teams[1])}>Pridruži se</button> : <></>}
                </div>
                <div className={teamClasses[2]} onClick={() => handleTeamClick(teams[2])}>HR
                    {!teams[2].aktivan ? <button onClick={() => handleJoinClick(teams[2])}>Pridruži se</button> : <></>}
                </div>
            </div>
            <div className="teams-row">
                <div className={teamClasses[3]} onClick={() => handleTeamClick(teams[3])}>PR
                    {!teams[3].aktivan ? <button onClick={() => handleJoinClick(teams[3])}>Pridruži se</button> : <></>}
                </div>
                <div className={teamClasses[4]} onClick={() => handleTeamClick(teams[4])}>FR
                    {!teams[4].aktivan ? <button onClick={() => handleJoinClick(teams[4])}>Pridruži se</button> : <></>}
                </div>
            </div>
        </div>

    )
}