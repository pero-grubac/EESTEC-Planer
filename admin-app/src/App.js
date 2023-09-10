import './App.css';
import { Login } from './Login';
import { Main } from './Main';
import { useState } from 'react';
import AdminList from './AdminList';

function App() {

  const [currentForm, setCurrentForm] = useState('main');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
    <div className="App">
      <AdminList></AdminList>
      
      {
        currentForm === "login" ? <Login onFormSwitch={toggleForm}/> : <Main onFormSwitch={toggleForm}/>
      }
    </div>
  );
}

export default App;
