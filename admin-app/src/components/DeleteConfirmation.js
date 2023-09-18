import React, { useEffect } from "react";
import axios from "axios";
export const DeleteConfirmation = ({
  switchTab,
  objectName,
  selectedUser,
  selectedRequest,
}) => {
  // implementirati funkciju za potvrdu koja provjerava da li je podesen user ili request, i u zavisnosti od toga brise jedno ili drugo
  const handleConfirm = async () => {
    if (selectedUser != null) {
      try {
        const response = await axios.delete(
          `http://localhost:8080/user/delete/${selectedUser.idKorisnika}`
          ,
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

        switchTab("users");
      } catch (error) {
        console.error("Error accepting request:", error);
      }
    } else if (selectedRequest != null) {
      try {
        const response = await axios.post(
          `http://localhost:8080/question/delete/${selectedRequest.idZahtjev}`
          ,
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

        switchTab("requests");
      } catch (error) {
        console.error("Error accepting request:", error);
      }
    }
  };

  useEffect(() => { }, []);
  // takodje funkcija za odbijanje koja samo vraca na prethodnu formu

  const handleCancel = (selectedUser, selectedRequest) => {
    switchTab("request");
    if (selectedRequest != null) switchTab("request");
    else if (selectedUser != null) switchTab("user");
  };

  return (
    <div className="user-details-container">
      <text>Da li ste sigurni da želite obrisati {objectName}?</text>
      <div className="user-details-buttons">
        <button className="login-button" onClick={() => handleConfirm()}>
          Da
        </button>
        <button
          className="login-button"
          onClick={() => handleCancel(selectedUser, selectedRequest)}
        >
          Ne
        </button>
      </div>
    </div>
  );
};
