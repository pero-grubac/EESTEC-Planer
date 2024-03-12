import React, { useState, useEffect } from "react";
import axios from "axios";
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

const CustomBarChart = ({ token }) => {
  // Accept token as a prop
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/stats/taksperuser",
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`, // Use the token prop
            },
          }
        );
        // Transforming data
        const transformedData = response.data.map((item, index) => ({
          username: item.first.korisnickoIme,
          name: item.first.ime,
          lastName: item.first.prezime,
          fullname: item.first.ime + " " + item.first.prezime,
          "Broj zadataka": item.second,
          id: index,
        }));
        setData(transformedData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [token]);
  return (
    <div className="chart-div stat-div">
      <h2>Aktivnost članova</h2>
      <h4>Brojevi označavaju završene zadatke</h4>
      <br></br>
      <ResponsiveContainer width="100%" height={300}>
        <BarChart
          width={500}
          height={300}
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
  );
};

export default CustomBarChart;
