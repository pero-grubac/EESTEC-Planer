import React from 'react';
import ReactDOM from 'react-dom';
import './index.css'; 
import { BrowserRouter } from 'react-router-dom';
import { createRoot } from "react-dom/client";

import App from "./App";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);
root.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>
)

