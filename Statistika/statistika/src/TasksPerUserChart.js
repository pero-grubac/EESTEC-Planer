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

const CustomBarChart = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/stats/taksperuser`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization:
                "Bearer " +
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGVrc2FuZHJhIiwiaWF0IjoxNzA5MjE0MDg0LCJleHAiOjE3MDkyMTU4ODR9.kQGlCyjxyZMR1zhMedzMYaVYOR8qMXepLkHqvgEA_m0",
            },
          }
        );
        // Transforming data
        const transformedData = response.data.map((item, index) => ({
          username: item.first.korisnickoIme,
          name: item.first.ime,
          lastName: item.first.prezime,
          Zadaci: item.second,
          id: index,
        }));
        setData(transformedData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h2>BROJ ZADATAKA PO KORISNIKU</h2>
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
          }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="username" />
          <YAxis />
          <Tooltip
            content={({ payload, label }) => {
              if (payload && payload.length) {
                return (
                  <div className="custom-tooltip">
                    <p className="label">{` ${payload[0].payload.name} ${payload[0].payload.lastName}`}</p>
                    <p className="desc">{`Broj zadataka: ${payload[0].payload.Zadaci}`}</p>
                  </div>
                );
              }
              return null;
            }}
          />
          <Legend />
          <Bar dataKey="Zadaci" barSize={20} fill="#8884d8" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default CustomBarChart;
