
function RequestDetails ({switchTab, selectedRequest}) {

    const handleRejectRequest = () => {
        switchTab("request_del");
    };

     return (
        <div className="user-details-container">
            <h3>Zahtjev</h3>
            <div className="user-details-edit">
                <div className="user-details-column">
                    <p htmlFor="ime">Ime</p>
                    <p htmlFor="prezime">Prezime</p>
                    <p htmlFor="korisnicko-ime">Korisničko ime</p>
                    <p htmlFor="email">email</p>
                    <p htmlFor="vrijeme">Vrijeme slanja</p>
                </div>
                <div className="user-details-column ">
                    <p className="info-text" id="ime">{selectedRequest.ime}</p>
                    <p className="info-text" id="prezime">{selectedRequest.prezime}</p>
                    <p className="info-text" id="korisnicko-ime">{selectedRequest.korisnickoIme}</p>
                    <p className="info-text" id="email">{selectedRequest.email}</p>
                    <p className="info-text" id="vrijeme">{selectedRequest.vrijemeKreiranja}</p>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button">Prihvati</button>
                <button className="login-button" onClick={() => switchTab("request_del")}>Odbij</button>
                <button className="login-button">Nazad</button>
            </div>
        </div>
    )
}

export default RequestDetails;