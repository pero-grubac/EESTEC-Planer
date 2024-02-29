import axios from "axios";
import React, { useEffect, useState } from "react";

const TotalNumberOfUsers =({token})=>{
    const [mappedData, setMappedData] = useState([]);

    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await axios.get(
              "http://localhost:8080/stats/numberofusers",
              {
                headers: {
                  "Content-Type": "application/json",
                  Authorization: `Bearer ${token}`, 
                },
              }
            );
          setMappedData(response.data);
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };
  
      fetchData();
    }, [token]);
    return (
        <div>
          <h2>UKUPAN BROJ KORISNIKA: {mappedData}</h2>
        </div>
      );  
};
export default TotalNumberOfUsers;