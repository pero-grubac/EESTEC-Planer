function CoordinatorTeams({ selectedUser }) {

    return (
        <div id="coordinator-teams">
            <label htmlFor="timovi">Koordinator u timovima:</label>
            <div id="timovi">
                <input
                    className="checkbox"
                    type="checkbox"
                    id="IT-team-cb"
                />
                <label htmlFor="IT-team-cb">IT</label>

                <input
                    className="checkbox"
                    type="checkbox"
                    id="design-team-cb"
                />
                <label htmlFor="design-team-c">Design</label>

                <input
                    className="checkbox"
                    type="checkbox"
                    id="HR-team-cb"
                />
                <label htmlFor="HR-team-cb">HR</label>

                <input
                    className="checkbox"
                    type="checkbox"
                    id="PR-team-cb"
                />
                <label htmlFor="PR-team-c">PR</label>

                <input
                    className="checkbox"
                    type="checkbox"
                    id="FR-team-cb"
                />
                <label htmlFor="IT-team-cb">FR</label>
            </div>
        </div>
    )
}

export default CoordinatorTeams;
