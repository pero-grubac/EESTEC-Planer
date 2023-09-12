import React, { useState, useEffect } from 'react';

function App({setAdmin}) {
  //const [admins, setAdmins] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/admins') // Assuming your Spring Boot app is running on the same host
      .then(response => response.json())
      .then(data => setAdmin(data))
      .catch(error => console.error('Error fetching admins:', error));
  }, []);

  return (
    <div>
      {/* <h1>Admins</h1>
      <ul>
        {admins.map(admin => (
          <li key={admin.idAdmin}>
            {admin.korisnickoIme} - {admin.lozinka}
          </li>
        ))}
      </ul> */}
    </div>
  );
}

export default App;