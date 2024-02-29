import axios from "axios";

const tasksPerUser = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/stats/taksperuser`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization:
            "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwOTIwNTA0NCwiZXhwIjoxNzA5MjA2ODQ0fQ.1_bN2M7pQjUPQqC_wRgOrQpeGYeX1Ai3diC8AYcK65w",
        },
      }
    );
  } catch (error) {
    console.error(error);
  }
};

