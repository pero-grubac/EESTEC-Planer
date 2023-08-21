//import 'bootstrap/dist/css/bootstrap.css'
import { DeleteConfirmation } from "./components/DeleteConfirmation.js";
import RequestsList from "./components/RequestsList.js";
import UserDetails from "./components/UserDetails.js";
import UserList from "./components/UserList";
import { useState } from "react";

export const Main = () => {
  const sidebarItems = [
    {
      id: 1,
      name: "requests",
      text: "Zahtjevi za naloge",
    },
    {
      id: 2,
      name: "users",
      text: "Korisnici",
    },
  ];

  const [currentTab, setCurrentTab] = useState("requests");

  const toggleTab = (tabName) => {
    setCurrentTab(tabName);
  };

  const handleSidebarClick = (sidebarItem) => {
    setSelectedSidebarItem(sidebarItem.id);
    toggleTab(sidebarItem.name);
  };

  const [selectedSidebarItem, setSelectedSidebarItem] = useState(1);

  const SelectedTab = () => {
    switch(currentTab){
        case 'requests':
            return <RequestsList onFormSwitch={toggleTab} />
        case 'users':
            return <UserList userClicked={toggleTab} onFormSwitch={toggleTab} />
        case 'user':
            return <UserDetails onFonFormSwitch={toggleTab} />
        case 'user_del':
            return <DeleteConfirmation objectName={"korisnika iz baze"}></DeleteConfirmation>
        case 'request':
        case 'request_del':
        default:
          return <div />
    }
  }

  return (
    <div className="main">
      <div className="sidebar">
        <div>
          <h2 id="pregledi-label">Pregledi</h2>
          <ul className="my-list-group">
            {sidebarItems.map((option) => (
              <li
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
        {/* {currentTab === "requests" ? (
          <RequestsList onFormSwitch={toggleTab} />
        ) : (
          <UserDetails toggleTab={toggleTab} onFormSwitch={toggleTab} />
        )} */}
      </div>
    </div>
  );
};
