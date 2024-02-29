import React, { useEffect, useState } from "react";
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid } from "recharts";
import axios from "axios";

const colors = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "red", "pink"];
const getPath = (x, y, width, height) => {
  return `M${x},${y + height}C${x + width / 3},${y + height} ${x + width / 2},${
    y + height / 3
  }
    ${x + width / 2}, ${y}
    C${x + width / 2},${y + height / 3} ${x + (2 * width) / 3},${y + height} ${
    x + width
  }, ${y + height}
    Z`;
};
const TriangleBar = (props) => {
  const { fill, x, y, width, height } = props;

  return <path d={getPath(x, y, width, height)} stroke="none" fill={fill} />;
};
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
          mjesec: item.second,
          list: [
            {
              korisnickoIme: item.first.korisnickoIme,
              brojZadataka: item.third,
            },
          ],
        }));
       
        setMappedData(responsedata);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [id, godina, token]);
 
  const countTasksByMonth = (data) => {
    const monthlyCounts = {};
    
    // Iterate through each data item
    data.forEach((item) => {
      const month = item.second; // Extract the month from the data
      const taskCount = item.third; // Extract the number of tasks
      if (!monthlyCounts[month]) {
        // If the month doesn't exist in the counts, initialize it to zero
        monthlyCounts[month] = 0;
      }
      // Increment the task count for the corresponding month
      monthlyCounts[month] += taskCount;
    });
    
    return monthlyCounts;
  };
  console.log(countTasksByMonth(mappedData));
  
  const dataByMonth = mappedData.reduce((acc, item) => {
    if (!acc[item.mjesec]) {
      acc[item.mjesec] = [];
    }
    acc[item.mjesec].push(item);
    return acc;
  }, {});

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
 

  const charts = Object.entries(dataByMonth).map(([month, data]) => (
    <div key={month} style={{ marginRight: 20 }}>
      <h3>Mjesec: {monthNames[month - 1]}</h3>
      <BarChart
        width={600}
        height={500}
        data={data}
        margin={{
          top: 20,
          right: 30,
          left: 30,
          bottom: 150,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="list[0].korisnickoIme" angle={-45} textAnchor="end" />
        <YAxis />
        <Bar
          dataKey="list[0].brojZadataka"
          fill="#8884d8"
          shape={<TriangleBar />}
          label={{ position: "top" }}
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={colors[index % 20]} />
          ))}
        </Bar>
      </BarChart>
    </div>
  ));

  return (
    <div>
      <h2>MJESECNI BROJ ZADATAKA PO KORISNIKU U TIMU U GODINI: {godina}</h2>
      <div style={{ display: "flex" }}>{charts}</div>
    </div>
  );
};

export default TasksPerUserInTeamChart;
