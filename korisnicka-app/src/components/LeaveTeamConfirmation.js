

export default function LeaveTeamConfirmation({ setLeaveTeamConfirmation, team }) {
    const handleLeaveTeam = async () => {
    }

    return (
        <div className="new-task-form-container">
            <p>Da li ste sigurni da želite napustiti tim?</p>
            <div className="button-line"> 
                <button onClick={handleLeaveTeam}>Da</button>
                <button onClick={() => setLeaveTeamConfirmation(false)}>Ne</button>
            </div>
        </div>
    )
}