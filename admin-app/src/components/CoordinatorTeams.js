import { useState } from "react";

function CoordinatorTeams({ team, setTeam }) {
  const [nazivTima, setNazivTima] = useState(team.naziv);
  const handleChange = (nazviTima) => {
    // promjeni id korisnika u timu prema nazivu
  };
  return (
    <div id="coordinator-teams">
      <label htmlFor="timovi">Koordinator u timovima:</label>
      <div id="timovi">
        <input
          className="checkbox"
          type="radio"
          id="IT-team-cb"
          value={nazivTima}
          checked={nazivTima === "IT"}
          onChange={() => setTeam("IT")}
        />
        <label htmlFor="IT-team-cb">IT</label>

        <input
          className="checkbox"
          type="radio"
          id="design-team-cb"
          value={nazivTima}
          checked={nazivTima === "Design"}
          onChange={() => setNazivTima("Design")}
        />
        <label htmlFor="design-team-cb">Design</label>

        <input
          className="checkbox"
          type="radio"
          id="HR-team-cb"
          value={nazivTima}
          checked={nazivTima === "HR"}
          onChange={() => setNazivTima("HR")}
        />
        <label htmlFor="HR-team-cb">HR</label>

        <input
          className="checkbox"
          type="radio"
          id="PR-team-cb"
          value={nazivTima}
          checked={nazivTima === "PR"}
          onChange={() => setNazivTima("PR")}
        />
        <label htmlFor="PR-team-cb">PR</label>

        <input
          className="checkbox"
          type="radio"
          id="FR-team-cb"
          value={nazivTima}
          checked={nazivTima === "FR"}
          onChange={() => setNazivTima("FR")}
        />
        <label htmlFor="FR-team-cb">FR</label>
      </div>
    </div>
  );
}

export default CoordinatorTeams;
