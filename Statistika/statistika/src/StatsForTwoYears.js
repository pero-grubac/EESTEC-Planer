import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const StatsForTwoYears = ({ prva, druga, id, token }) => {
  const [yearOne, setYearOne] = useState([]);
  const [yearTwo, setYearTwo] = useState([]);

  useEffect(() => {
    const fetchData = async (year, setData) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/stats/taskbymonthinteam/${id}/${year}`,
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

        // Count tasks by month and update state
        const countedData = countTasksByMonth(responsedata);
        setData(countedData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData(prva, setYearOne);
    fetchData(druga, setYearTwo);
  }, [id, prva, druga, token]);

  // Function to count tasks by month
  const countTasksByMonth = (data) => {
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

    const monthlyCounts = {};

    // Iterate through each data item
    data.forEach((item) => {
      const month = item.mjesec; // Extract the month from the data
      item.list.forEach((task) => {
        if (!monthlyCounts[month]) {
          // If the month doesn't exist in the counts, initialize it to zero
          monthlyCounts[month] = 0;
        }
        // Increment the task count for the corresponding month
        monthlyCounts[month] += task.brojZadataka;
      });
    });

    // Convert the monthlyCounts object into an array of objects
    const result = Object.keys(monthlyCounts).map((month) => ({
      month,
      tasks: monthlyCounts[month],
    }));

    // Map the month number to its corresponding name
    return result.map((monthData) => ({
      ...monthData,
      month: monthNames[parseInt(monthData.month) - 1],
    }));
  };

  // Merge the data from yearOne and yearTwo into a single array
  const mergedYear = yearOne.map(({ month, tasks }) => {
    const tasksYearTwo =
      yearTwo.find((item) => item.month === month)?.tasks || 0;
    return {
      month,
      tasksYearOne: tasks,
      tasksYearTwo,
    };
  });

  return (
    <div>
      <h2>
        GODINE {prva} I {druga}
      </h2>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart
          width={500}
          height={300}
          data={mergedYear}
          margin={{
            top: 5,
            right: 30,
            left: 20,
            bottom: 5,
          }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="month" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey="tasksYearOne"
            stroke="#8884d8"
            name={`Zadaci - ${prva}`}
          />
          <Line
            type="monotone"
            dataKey="tasksYearTwo"
            stroke="#82ca9d"
            name={`Zadaci - ${druga}`}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default StatsForTwoYears;
