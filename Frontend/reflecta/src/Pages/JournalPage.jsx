import React, { useState, useEffect } from "react";
import { Card, Button } from "react-bootstrap";
import { FaHome, FaDumbbell, FaUtensils, FaBullseye, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const JournalPage = () => {
  const [journalEntries, setJournalEntries] = useState([]);
  const [smile, setSmile] = useState("");
  const [dealingWith, setDealingWith] = useState("");
  const [lookingForwardTo, setLookingForwardTo] = useState("");

  useEffect(() => {
    const savedEntries = JSON.parse(localStorage.getItem("journalEntries")) || [];
    setJournalEntries(savedEntries);
  }, []);

  const handleAddEntry = () => {
    if (smile.trim() && dealingWith.trim() && lookingForwardTo.trim()) {
      const newEntry = {
        id: Date.now(),
        smile,
        dealingWith,
        lookingForwardTo,
        date: new Date().toLocaleDateString(),
      };
      const newEntries = [...journalEntries, newEntry];
      setJournalEntries(newEntries);
      localStorage.setItem("journalEntries", JSON.stringify(newEntries));
      setSmile("");
      setDealingWith("");
      setLookingForwardTo("");
    }
  };

  const navigate = useNavigate();

  return (
    <div className="d-flex min-vh-100">
      {/* Sidebar */}
      <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
        <h4>Reflecta</h4>
        <p className="text-white fst-italic mb-4">Reflects you</p>
          <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
          <SidebarLink icon={<FaDumbbell />} label="Exercise" onClick={() => navigate("/exercise")} />
          <SidebarLink icon={<FaUtensils />} label="Diet" onClick={() => navigate("/diet")}  />
          <SidebarLink icon={<FaTint />} label="Water" onClick={() => navigate("/water")} />
          <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")}/>
          <SidebarLink icon={<FaBook />} label="Journal" onClick={() => navigate("/journal")} selected/>
          <SidebarLink icon={<FaBullseye />} label="Goals" onClick={() => navigate("/goals")}/>
          <SidebarLink icon={<FaSignOutAlt />} label="Logout" onClick={() => navigate("/")} />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 d-flex flex-column p-4" style={{ height: "100vh", overflow: "hidden" }}>
        {/* Navbar */}
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h3 className="text-dark m-0">My Journal</h3>
        </div>

        {/* Scrollable Journal Entries */}
        <div className="flex-grow-1 overflow-auto pe-2 mb-3">
          <div className="d-flex flex-column gap-3">
            {journalEntries.map((entry) => (
              <Card key={entry.id} className="p-3">
                <div className="d-flex justify-content-between">
                  <strong>{entry.date}</strong>
                </div>
                <p><strong>Made me smile today:</strong> {entry.smile}</p>
                <p><strong>Currently dealing with:</strong> {entry.dealingWith}</p>
                <p><strong>Looking forward to:</strong> {entry.lookingForwardTo}</p>
              </Card>
            ))}
          </div>
        </div>

        {/* New Entry Form */}
        <div className="d-flex gap-2 align-items-end">
          <textarea
            className="form-control"
            value={smile}
            onChange={(e) => setSmile(e.target.value)}
            placeholder="What made you smile today?"
            rows="2"
          />
          <textarea
            className="form-control"
            value={dealingWith}
            onChange={(e) => setDealingWith(e.target.value)}
            placeholder="What are you currently dealing with?"
            rows="2"
          />
          <textarea
            className="form-control"
            value={lookingForwardTo}
            onChange={(e) => setLookingForwardTo(e.target.value)}
            placeholder="What are you looking forward to?"
            rows="2"
          />
          <Button
            variant="primary"
            onClick={handleAddEntry}
            style={{
              backgroundColor: "#1AB0B0",
              borderColor: "#1AB0B0",
              height: "40px",
              whiteSpace: "nowrap",
            }}
          >
            Add
          </Button>
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

export default JournalPage;
