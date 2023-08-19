import React, { useState, useEffect } from 'react';

function App() {
  const [admins, setAdmins] = useState([]);

  useEffect(() => {
    fetch('/admins') // Assuming your Spring Boot app is running on the same host
      .then(response => response.json())
      .then(data => setAdmins(data))
      .catch(error => console.error('Error fetching admins:', error));
  }, []);

  return (
    <div>
      <h1>Admins</h1>
      <ul>
        {admins.map(admin => (
          <li key={admin.idAdmin}>
            {admin.korisnickoIme} - {admin.lozinka}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;