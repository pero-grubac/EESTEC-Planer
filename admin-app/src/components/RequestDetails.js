
function RequestDetails () {

    const testRequest = {
        korisnickoIme: "korisnik",
        ime: "ime",
        prezime: "prezime",
        email: "imeprezime@email.com",
        vrijeme: "14.08.2023 14:32:56"
    }

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
                    <p className="info-text" id="ime">{testRequest.ime}</p>
                    <p className="info-text" id="prezime">{testRequest.prezime}</p>
                    <p className="info-text" id="korisnicko-ime">{testRequest.korisnickoIme}</p>
                    <p className="info-text" id="email">{testRequest.email}</p>
                    <p className="info-text" id="vrijeme">{testRequest.vrijeme}</p>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button" type="submit">Prihvati</button>
                <button className="login-button" type="submit">Odbij</button>
                <button className="login-button">Nazad</button>
            </div>
        </div>
    )
}

export default RequestDetails;