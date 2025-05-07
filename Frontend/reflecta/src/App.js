import './App.css';
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import SignupPage from "./Pages/SignupPage";
import HomePage from "./Pages/HomePage"
import JournalPage from './Pages/JournalPage';
import WaterIntakePage from './Pages/WaterIntakePage';
import SleepPage from './Pages/SleepPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/journal" element={<JournalPage />} />
        <Route path="/water" element={<WaterIntakePage />} />
        <Route path="/sleep" element={<SleepPage />} />

      </Routes>
    </Router>
  );
}

export default App;

