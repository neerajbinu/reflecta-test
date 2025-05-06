import React, { useState, useEffect } from "react";
import { Card, Button } from "react-bootstrap";
import { FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const JournalPage = () => {
  const [journalEntries, setJournalEntries] = useState([]);
  const [smile, setSmile] = useState("");
  const [dealingWith, setDealingWith] = useState("");
  const [lookingForwardTo, setLookingForwardTo] = useState("");

  useEffect(() => {
    // Load journal entries from localStorage (or from backend when connected)
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
        date: new Date().toLocaleDateString()
      };
      const newEntries = [...journalEntries, newEntry];
      setJournalEntries(newEntries);
      localStorage.setItem("journalEntries", JSON.stringify(newEntries));  // Save to localStorage
      setSmile("");  // Clear input
      setDealingWith(""); // Clear input
      setLookingForwardTo(""); // Clear input
    }
  };

  const navigate = useNavigate();

  return (
    <div className="d-flex vh-100">

      {/* Sidebar */}
      <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
        <h4>Reflecta</h4>
        <p className="text-white fst-italic mb-4">Reflects you</p>
        <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
        <SidebarLink icon={<FaDumbbell />} label="Exercise" />
        <SidebarLink icon={<FaUtensils />} label="Diet" />
        <SidebarLink icon={<FaTint />} label="Water" />
        <SidebarLink icon={<FaBed />} label="Sleep" />
        <SidebarLink icon={<FaBook />} label="Journal" />
        <SidebarLink icon={<FaSignOutAlt />} label="Logout" />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 p-4">
        {/* Navbar */}
        <div className="d-flex justify-content-between align-items-center p-1">
          <h3 className="text-dark m-0">My Journal</h3>
        </div>

        {/* Journal Entries */}
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

        {/* New Journal Entry Form */}
        <div className="d-flex justify-content-between mt-4">
          <div className="flex-grow-1">
            <textarea
              className="form-control mb-2"
              value={smile}
              onChange={(e) => setSmile(e.target.value)}
              placeholder="What made you smile today?"
              rows="2"
            />
            <textarea
              className="form-control mb-2"
              value={dealingWith}
              onChange={(e) => setDealingWith(e.target.value)}
              placeholder="What are you currently dealing with?"
              rows="2"
            />
            <textarea
              className="form-control mb-2"
              value={lookingForwardTo}
              onChange={(e) => setLookingForwardTo(e.target.value)}
              placeholder="What are you looking forward to?"
              rows="2"
            />
          </div>
          <Button
            variant="primary"
            onClick={handleAddEntry}
            style={{ backgroundColor: "#1AB0B0", borderColor: "#1AB0B0" }}
            className="ms-3"
          >
            Add Entry
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
