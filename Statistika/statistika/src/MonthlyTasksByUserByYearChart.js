import React, { useEffect, useState } from "react";
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid } from "recharts";
import getData from "./MonthlyTasksByUserByYear";

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
const MonthlyTasksByUserByYearChart = ({ godina }) => {
  const [mappedData, setMappedData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getData(godina);
        setMappedData(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [godina]);

  // Group the data by month (mjesec)
  const dataByMonth = mappedData.reduce((acc, item) => {
    if (!acc[item.mjesec]) {
      acc[item.mjesec] = [];
    }
    acc[item.mjesec].push(item);
    return acc;
  }, {});

  // Render a BarChart for each month's data
  const charts = Object.entries(dataByMonth).map(([month, data]) => (
    <div key={month} style={{ marginRight: 20 }}>
      <h3>Mjesec: {month}</h3>
      <BarChart
        width={400}
        height={300}
        data={data}
        margin={{
          top: 20,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="list[0].korisnickoIme" />
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
      <h2>MJESECNI BROJ ZADATAKA PO KORISNIKU U GODINI: {godina}</h2>
      <div style={{ display: "flex" }}>{charts}</div>
    </div>
  );
};

export default MonthlyTasksByUserByYearChart;
