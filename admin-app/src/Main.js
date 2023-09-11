//import 'bootstrap/dist/css/bootstrap.css'
import { DeleteConfirmation } from "./components/DeleteConfirmation.js";
import RequestDetails from "./components/RequestDetails.js";
import RequestsList from "./components/RequestsList.js";
import UserDetails from "./components/UserDetails.js";
import UserList from "./components/UserList";
import AdminConfig from "./components/AdminConfig.js";
import { useState } from "react";

export const Main = (props) => {
  const sidebarItems = [
    {
      id: 1,
      name: "requests",
      text: "Pregled zahtjeva za naloge",
    },
    {
      id: 2,
      name: "users",
      text: "Pregled korisnika",
    },
    {
      id: 3,
      name: "admin-config",
      text: "Podešavanje admin naloga",
    },
    {
      id: 4,
      name: "logout",
      text: "Odjava",
    }
  ];

  const [currentTab, setCurrentTab] = useState("requests");
  const [selectedUser, setSelectedUser] = useState(null);
  const [selectedRequest, setSelectedRequest] = useState(null);
  const [selectedTeam, setSelectedTeam] = useState(null);
  
  const toggleTab = (tabName) => {
    setCurrentTab(tabName);
  };

  const handleSidebarClick = (sidebarItem) => {
    setSelectedSidebarItem(sidebarItem.id);
    toggleTab(sidebarItem.name);
  };

  const [selectedSidebarItem, setSelectedSidebarItem] = useState(1);

  const SelectedTab = () => {
    switch (currentTab) {
      case 'requests':
        return <RequestsList switchTab={toggleTab} selectRequest={setSelectedRequest} onFormSwitch={toggleTab} />
      case 'users':
        return <UserList switchTab={toggleTab} selectUser={setSelectedUser} onFormSwitch={toggleTab} selectTeam={setSelectedTeam}/>
      case 'user':
        return <UserDetails onFormSwitch={toggleTab} switchTab={toggleTab} selectedUser={selectedUser} selectedTeam ={selectedTeam} />
      case 'user_del':
        return <DeleteConfirmation onFormSwitch={toggleTab} switchTab={toggleTab} selectedRequest={null} selectedUser={selectedUser} objectName={"korisnika iz baze"}
        ></DeleteConfirmation>
      case 'request':
        return <RequestDetails switchTab={toggleTab} selectedRequest={selectedRequest} />
      case 'request_del':
        return <DeleteConfirmation onFormSwitch={toggleTab} switchTab={toggleTab} objectName={"zahtjev iz baze"}
          selectedUser={null} selectedRequest={selectedRequest}
        ></DeleteConfirmation>
      case 'admin-config':
        return <AdminConfig></AdminConfig>
      case 'logout':
        props.onFormSwitch('login');
      default:
        return <div />
    }
  }

  return (
    <div className="main">
      <div className="sidebar">
        <div>
          <h2 id="pregledi-label">Opcije</h2>
          <ul className="my-list-group">
            {sidebarItems.map((option) => (
              <li key={option.id}
                className={
                  selectedSidebarItem === option.id
                    ? "my-list-group-item-active"
                    : "my-list-group-item"
                }
                onClick={() => handleSidebarClick(option)}
              >
                {option.text}
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div className="list-view">
        {SelectedTab()}
      </div>
    </div>
  );
};
