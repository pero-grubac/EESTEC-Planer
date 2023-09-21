import React, { useEffect } from 'react';
import axios from 'axios';

function formatDateTime(isoDate) {
    const date = new Date(isoDate);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is zero-based
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${day}.${month}.${year} ${hours}:${minutes}:${seconds}`;
}

function RequestDetails({ switchTab, selectedRequest }) {

    const handleAccept = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/question/approve/${selectedRequest.idZahtjev}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                });

            if (response.status === 403) {
                localStorage.clear();
                switchTab("login");
            }

            switchTab("requests")
        } catch (error) {
            console.error('Error accepting request:', error);
        }
    }

    return (
        <div className="request-details-container">
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
                    <p className="info-text-request" id="ime">{selectedRequest.ime}</p>
                    <p className="info-text-request" id="prezime">{selectedRequest.prezime}</p>
                    <p className="info-text-request" id="korisnicko-ime">{selectedRequest.korisnickoIme}</p>
                    <p className="info-text-request" id="email">{selectedRequest.email}</p>
                    <p className="info-text-request" id="vrijeme">{formatDateTime(selectedRequest.datumKreiranja)}</p>
                </div>
            </div>
            <div className="user-details-buttons">
                <button className="login-button" onClick={() => handleAccept()}>Prihvati</button>
                <button className="login-button" onClick={() => switchTab("request_del")}>Odbij</button>
                <button className="login-button" onClick={() => switchTab("requests")}>Nazad</button>
            </div>
        </div>
    )
}

export default RequestDetails;