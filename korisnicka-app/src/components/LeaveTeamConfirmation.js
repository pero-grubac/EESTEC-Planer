
import axios from "axios";
import { useNavigate } from "react-router";

export default function LeaveTeamConfirmation({ loggedUser, setLoggedUser, setLeaveTeamConfirmation, team }) {
    const navigate = useNavigate();

    const handleLeaveTeam = async () => {
        try {
            const response = await axios.put("http://localhost:8080/user/leaveTeam",
                {
                    idKorisnika: loggedUser.idKorisnika,
                    idTim: team
                },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                },
            )

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            setLeaveTeamConfirmation(false);
            navigate("/teams", { replace: true });

        } catch (error) {
            console.error(error);
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

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            setLoggedUser(response.data);

        } catch (error) {
            console.error(error);
        }
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