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

const TasksPerUserInTeamChart = ({ godina, id, token, tim }) => {
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
          fullname: item.first.ime + " " + item.first.prezime,
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
    "Januar",
    "Februar",
    "Mart",
    "April",
    "Maj",
    "Jun",
    "Jul",
    "Avgust",
    "Septembar",
    "Oktobar",
    "Novembar",
    "Decembar",
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
    <div className="chart-div" key={monthName}>
      <h3>{monthName}</h3>
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
          <XAxis dataKey="fullname" />
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
    <div className="stat-div">
      <h2>Aktivnost članova {tim.naziv} tima kroz godinu {godina}.</h2>
      <h4>Brojevi označavaju završene zadatke</h4>
      <br></br>
      <div>{charts}</div>
    </div>
  );
};

export default TasksPerUserInTeamChart;
