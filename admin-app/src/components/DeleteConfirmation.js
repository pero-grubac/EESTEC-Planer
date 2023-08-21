
export const DeleteConfirmation = ({objectName}) => {

    return (
        <div className="user-details-container">
            <text>Da li ste sigurni da želite obrisati {objectName}?</text>
            <div className="user-details-buttons">
                <button className="login-button">Da</button>
                <button className="login-button">Ne</button>
            </div>
        </div>
    )
}