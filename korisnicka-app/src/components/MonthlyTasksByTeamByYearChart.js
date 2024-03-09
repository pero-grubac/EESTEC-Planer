import React, { useState, useEffect } from "react";
import axios from "axios";
import { ResponsivePie } from "@nivo/pie";

const MonthlyTasksByTeamByYearChart = ({ godina, token }) => {
  const [chartData, setChartData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/stats/taskbymonthbyteam/${godina}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        // Process data to group by month
        const groupedData = groupDataByMonth(response.data);
        setChartData(groupedData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [godina, token]);

  // Function to group data by month
  const groupDataByMonth = (data) => {
    const groupedData = {};
    data.forEach((item) => {
      const month = item.second; // Assuming "second" represents the month
      if (!groupedData[month]) {
        groupedData[month] = [];
      }
      groupedData[month].push({ id: item.first.naziv, value: item.third });
    });
    return Object.keys(groupedData).map((month) => ({
      month,
      data: groupedData[month],
    }));
  };
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
  return (
    <div className="stat-div">
      <h2>Aktivnost članova po timovima u godini {godina}.</h2>
      <h4>Brojevi označavaju završene zadatke</h4>
      <br></br>
      <div className="pie-charts-container">
        {chartData.map((monthData, index) => (
          <div key={index}>
            <h3>{monthNames[monthData.month - 1]}</h3>
            <div style={{ height: "400px" }}>
              <ResponsivePie
                width={500}
                data={monthData.data}
                margin={{ top: 40, right: 80, bottom: 80, left: 80 }}
                cornerRadius={3}
                activeOuterRadiusOffset={8}
                borderWidth={4}
                borderColor="white"
                enableArcLinkLabels={true}
                arcLinkLabelsSkipAngle={10}
                arcLinkLabelsTextColor="#333333"
                arcLinkLabelsThickness={4}
                arcLinkLabelsColor={{ from: "color" }}
                arcLabelsSkipAngle={10}
                arcLabelsTextColor="black"
                isInteractive={false}
                arcLabel={(arc) =>
                  ` ${(
                    (arc.value /
                      monthData.data.reduce((acc, d) => acc + d.value, 0)) *
                    100
                  ).toFixed(2)}% (${arc.value})`
                }
              />
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MonthlyTasksByTeamByYearChart;
