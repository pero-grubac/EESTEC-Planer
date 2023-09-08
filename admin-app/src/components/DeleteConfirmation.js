
export const DeleteConfirmation = ({ switchTab, objectName, selectedUser, selectedRequest }) => {

    // implementirati funkciju za potvrdu koja provjerava da li je podesen user ili request, i u zavisnosti od toga brise jedno ili drugo
    const handleConfirm = () => {
        if (selectedUser) {

        }
        else if (selectedRequest) {

        }
    };

    // takodje funkcija za odbijanje koja samo vraca na prethodnu formu

    const handleCancel = (selectedUser, selectedRequest) => {
        switchTab("request");
        if (selectedRequest != null)
            switchTab("request");
        else if (selectedUser != null)
            switchTab("user");
    };

    return (
        <div className="user-details-container">
            <text>Da li ste sigurni da želite obrisati {objectName}?</text>
            <div className="user-details-buttons">
                <button className="login-button" onClick={() => handleConfirm}>Da</button>
                <button className="login-button" onClick={() => handleCancel(selectedUser, selectedRequest)}>Ne</button>
            </div>
        </div>
    )
}