import { useState } from "react";

function CoordinatorTeams({ team, setTeam }) {
  return (
    <div id="coordinator-teams">
      <label htmlFor="timovi">Koordinator u timovima:</label>
      <div id="timovi">
        <input
          className="checkbox"
          type="radio"
          id="IT-team-cb"
          value={team}
          checked={team === "IT"}
          onChange={() => setTeam("IT")}
        />
        <label htmlFor="IT-team-cb">IT</label>

        <input
          className="checkbox"
          type="radio"
          id="design-team-cb"
          value={team}
          checked={team === "Design"}
          onChange={() => setTeam("Design")}
        />
        <label htmlFor="design-team-cb">Design</label>

        <input
          className="checkbox"
          type="radio"
          id="HR-team-cb"
          value={team}
          checked={team === "HR"}
          onChange={() => setTeam("HR")}
        />
        <label htmlFor="HR-team-cb">HR</label>

        <input
          className="checkbox"
          type="radio"
          id="PR-team-cb"
          value={team}
          checked={team === "PR"}
          onChange={() => setTeam("PR")}
        />
        <label htmlFor="PR-team-cb">PR</label>

        <input
          className="checkbox"
          type="radio"
          id="FR-team-cb"
          value={team}
          checked={team === "FR"}
          onChange={() => setTeam("FR")}
        />
        <label htmlFor="FR-team-cb">FR</label>
      </div>
    </div>
  );
}

export default CoordinatorTeams;
