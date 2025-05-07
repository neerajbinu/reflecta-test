import React, { useState, useEffect } from "react";
import { Card, Button } from "react-bootstrap";
import { FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const SleepPage = () => {
  const [sleepEntries, setSleepEntries] = useState([]);
 



  const navigate = useNavigate();

  return (
    <div className="d-flex min-vh-100">
      {/* Sidebar */}
      <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
        <h4>Reflecta</h4>
        <p className="text-white fst-italic mb-4">Reflects you</p>
        <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
        <SidebarLink icon={<FaDumbbell />} label="Exercise" onClick={() => navigate("/home")} />
        <SidebarLink icon={<FaUtensils />} label="Diet" onClick={() => navigate("/home")} />
        <SidebarLink icon={<FaTint />} label="Water" onClick={() => navigate("/water")} />
        <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")} />
        <SidebarLink icon={<FaBook />} label="Journal" onClick={() => navigate("/journal")} />
        <SidebarLink icon={<FaSignOutAlt />} label="Logout" onClick={() => navigate("/")} />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 d-flex flex-column p-4" style={{ height: "100vh", overflow: "hidden" }}>
        {/* Navbar */}
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h3 className="text-dark m-0">Sleep</h3>
        </div>


       
      </div>
    </div>
  );
 };

  const SidebarLink = ({ icon, label, selected, onClick }) => (
  <div
    className={`d-flex align-items-center gap-2 p-2 rounded ${selected ? "text-white" : "text-white"}`}
    style={{
      cursor: "pointer",
      backgroundColor: selected ? "#1AB0B0" : "transparent",
      transition: "background-color 0.2s",
    }}
    onClick={onClick}
    onMouseOver={(e) => {
      if (!selected) e.currentTarget.style.backgroundColor = "#1AB0B0";
    }}
    onMouseOut={(e) => {
      if (!selected) e.currentTarget.style.backgroundColor = "transparent";
    }}
  >
    {icon}
    <span>{label}</span>
  </div>
);

export default SleepPage;
