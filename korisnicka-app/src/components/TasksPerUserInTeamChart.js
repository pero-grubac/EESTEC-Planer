import React, { useEffect, useState } from "react";
import {
  BarChart,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  Bar,
  ResponsiveContainer,
} from "recharts";
import axios from "axios";

const TasksPerUserInTeamChart = ({ godina, id, token }) => {
  const [mappedData, setMappedData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/stats/taskbymonthinteam/${id}/${godina}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        const responsedata = response.data.map((item) => ({
          month: item.second,
          username: item.first.korisnickoIme,
          name: item.first.ime,
          lastName: item.first.prezime,
          "Broj zadataka": item.third,
        }));

        setMappedData(responsedata);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [id, godina, token]);

  const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const dataByMonth = mappedData.reduce((acc, item) => {
    const monthName = monthNames[item.month - 1];
    if (!acc[monthName]) {
      acc[monthName] = [];
    }
    acc[monthName].push(item);
    return acc;
  }, {});

  const charts = Object.entries(dataByMonth).map(([monthName, data]) => (
    <div style={{ display: "flex" }} key={monthName}>
      <h3>Mjesec: {monthName}</h3>
      <ResponsiveContainer width="100%" height={300}>
        <BarChart
          data={data}
          margin={{
            top: 5,
            right: 30,
            left: 20,
            bottom: 5,
          }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="username" />
          <YAxis />
          <Tooltip
            content={({ payload, label }) => {
              if (payload && payload.length) {
                return (
                  <div className="custom-tooltip">
                    <p className="label">{` ${payload[0].payload.name} ${payload[0].payload.lastName}`}</p>
                    <p className="desc">{`Broj zadataka: ${payload[0].payload["Broj zadataka"]}`}</p>
                  </div>
                );
              }
              return null;
            }}
          />
          <Legend />
          <Bar dataKey="Broj zadataka" barSize={20} fill="#8884d8" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  ));

  return (
    <div>
      <h2>MJESECNI BROJ ZADATAKA PO KORISNIKU U TIMU U GODINI: {godina}</h2>
      <div>{charts}</div>
    </div>
  );
};

export default TasksPerUserInTeamChart;
