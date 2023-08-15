import './App.css';
import { Login } from './Login';
import { Main } from './Main';
import { useState } from 'react';

function App() {

  const [currentForm, setCurrentForm] = useState('login');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
    <div className="App">
      {
        currentForm === "login" ? <Login onFormSwitch={toggleForm}/> : <Main onFormSwitch={toggleForm}/>
      }
    </div>
  );
}

export default App;
