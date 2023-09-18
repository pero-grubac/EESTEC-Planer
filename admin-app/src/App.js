import './App.css';
import { Login } from './Login';
import { Main } from './Main';
import { useState } from 'react';

function App() {

  const [currentForm, setCurrentForm] = useState('login');
  const [admin, setAdmin] = useState(null);

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
    <div className="App">
      {
        currentForm === "login" ? <Login setAdmin={setAdmin} onFormSwitch={toggleForm}/> : <Main admin={admin} onFormSwitch={toggleForm}/>
      }
    </div>
  );
}

export default App;
