import React, { useState, useEffect } from "react";
import {
  FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook, FaSignOutAlt, FaBullseye,
} from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const GoalPage = () => {
  const navigate = useNavigate();
  const [goals, setGoals] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    type: "EXERCISE",
    frequency: "DAILY",
    metric: "DURATION",
    target: "",
    startDate: new Date().toISOString().split("T")[0],
    endDate: new Date().toISOString().split("T")[0],
    weightGoal: null,
  });

  useEffect(() => {
    fetchGoals();
  }, []);

  const fetchGoals = () => {
    const storedGoals = localStorage.getItem("goals");
    if (storedGoals) {
      setGoals(JSON.parse(storedGoals));
    }
  };

  const saveGoals = (goalsToSave) => {
    localStorage.setItem("goals", JSON.stringify(goalsToSave));
    setGoals(goalsToSave);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newGoal = {
      ...formData,
      status: "ACTIVE", // Default status
    };
    const updatedGoals = [...goals, newGoal];
    saveGoals(updatedGoals);
    setShowForm(false);
  };

  return (
    <div className="d-flex vh-100">
      {/* Sidebar */}
      <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
              <h4>Reflecta</h4>
              <p className="text-white fst-italic mb-4">Reflects you</p>
              <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
              <SidebarLink icon={<FaDumbbell />} label="Exercise" onClick={() => navigate("/exercise")} />
              <SidebarLink icon={<FaUtensils />} label="Diet" onClick={() => navigate("/diet")}  />
              <SidebarLink icon={<FaTint />} label="Water" onClick={() => navigate("/water")} />
              <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")}/>
              <SidebarLink icon={<FaBook />} label="Journal" onClick={() => navigate("/journal")} />
              <SidebarLink icon={<FaDumbbell />} label="Goals" onClick={() => navigate("/goals")}/>
              <SidebarLink icon={<FaSignOutAlt />} label="Logout" onClick={() => navigate("/")} />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 p-4">
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h3>My Goals</h3>
          <button className="btn btn-primary" onClick={() => setShowForm(!showForm)}>
            {showForm ? "Cancel" : "New Goal +"}
          </button>
        </div>

        {/* Create Goal Form */}
        {showForm && (
          <form onSubmit={handleSubmit} className="border p-3 mb-4 rounded shadow-sm bg-light">
            <div className="row mb-2">
              <div className="col-md-4">
                <label>Goal Type:</label>
                {["EXERCISE", "MEDITATION", "SLEEP", "DIET"].map((type) => (
                  <div key={type}>
                    <input type="radio" id={type} name="type" value={type}
                      checked={formData.type === type} onChange={handleInputChange} />
                    <label htmlFor={type} className="ms-1">{type}</label>
                  </div>
                ))}
              </div>
              <div className="col-md-4">
                <label>Frequency:</label>
                <select className="form-select" name="frequency" value={formData.frequency} onChange={handleInputChange}>
                  <option value="DAILY">DAILY</option>
                  <option value="WEEKLY">WEEKLY</option>
                  <option value="MONTHLY">MONTHLY</option>
                </select>
              </div>
              <div className="col-md-4">
                <label>Metric:</label>
                <select className="form-select" name="metric" value={formData.metric} onChange={handleInputChange}>
                  <option value="DURATION">DURATION</option>
                  <option value="CALORIES_BURNED">CALORIES BURNED</option>
                  <option value="CALORIES_CONSUMED">CALORIES CONSUMED</option>
                </select>
              </div>
            </div>
            <div className="row mb-2">
              <div className="col-md-4">
                <label>Target:</label>
                <input type="number" className="form-control" name="target" value={formData.target} onChange={handleInputChange} required />
              </div>
              <div className="col-md-4">
                <label>Start Date:</label>
                <input type="date" className="form-control" name="startDate" value={formData.startDate} readOnly />
              </div>
              <div className="col-md-4">
                <label>End Date:</label>
                <input type="date" className="form-control" name="endDate" value={formData.endDate} readOnly />
              </div>
            </div>
            <button type="submit" className="btn btn-success mt-2">Create Goal</button>
          </form>
        )}

        {/* Goal Cards */}
        {goals.length === 0 ? (
          <p>No goals set yet.</p>
        ) : (
          <div className="row">
            {goals.map((goal, index) => (
               <div className="shadow-sm mb-4" key={index}> 
                <div className="card border-info">
                  <div className="card-body">
                    <h5 className="card-title">{goal.type} Goal</h5>
                    <p className="card-text"><strong>Target:</strong> {goal.target}</p>
                    <p className="card-text"><strong>From:</strong> {goal.startDate}</p>
                    <p className="card-text"><strong>To:</strong> {goal.endDate}</p>
                    <p className="card-text"><strong>Status:</strong> {goal.status}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

const SidebarLink = ({ icon, label, selected, onClick }) => (
  <div
    className={`d-flex align-items-center gap-2 p-2 rounded text-white`}
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

export default GoalPage;
