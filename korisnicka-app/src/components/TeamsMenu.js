

export const TeamsMenu = (props) => {

    return (
        <div className="teams-container">
            <div className="teams-row">
                <div className="team-rectangle team-active">Dizajn</div>
                <div className="team-rectangle team-inactive">HR</div>
                <div className="team-rectangle team-inactive">PR</div>
            </div>
            <div className="teams-row">
                <div className="team-rectangle team-inactive">IT</div>
                <div className="team-rectangle team-inactive">FR</div>
            </div>
        </div>

    )
}