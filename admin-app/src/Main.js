//import 'bootstrap/dist/css/bootstrap.css'
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

  const [currentList, setCurrentList] = useState("requests");

  const toggleList = (listName) => {
    setCurrentList(listName);
  };

  const handleSidebarClick = (sidebarItem) => {
    setSelectedSidebarItem(sidebarItem.id);
    toggleList(sidebarItem.name);
  };

  const [selectedSidebarItem, setSelectedSidebarItem] = useState(1);

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
        {currentList === "requests" ? (
          <RequestsList onFormSwitch={toggleList} />
        ) : (
          <UserDetails onFormSwitch={toggleList} />
        )}
      </div>
    </div>
  );
};
