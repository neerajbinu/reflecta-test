import React, { useState, useEffect } from "react";
import { Card, Button } from "react-bootstrap";
import { FaHome, FaDumbbell, FaUtensils, FaBullseye, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
const ExercisePage = () => {
  const [exercises, setExercises] = useState([
    { id: 1, name: 'Running', duration: '30 mins', date: '2025-05-06' },
    { id: 2, name: 'Yoga', duration: '45 mins', date: '2025-05-05' },
  ]);
  const [newExercise, setNewExercise] = useState({
    name: '',
    duration: '',
    date: '',
  });

  const handleChange = (e) => {
    setNewExercise({ ...newExercise, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: Send newExercise to backend
    setExercises([...exercises, { ...newExercise, id: exercises.length + 1 }]);
    setNewExercise({ name: '', duration: '', date: '' });
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
          <h3 className="text-dark m-0">Excercise Tracker</h3>
        </div>

        <div className="row">
          {/* Exercise List */}
          <div className="col-md-8">
            <div className="card">
              <div className="card-header" style={{ backgroundColor: '#1AB0B0', color: '#fff' }}>
                Logged Exercises
              </div>
              <ul className="list-group list-group-flush">
                {exercises.map((exercise) => (
                  <li key={exercise.id} className="list-group-item d-flex justify-content-between">
                    <div>
                      <strong>{exercise.name}</strong> - {exercise.duration}
                    </div>
                    <span className="text-muted">{exercise.date}</span>
                  </li>
                ))}
              </ul>
            </div>
          </div>

          {/* Add Exercise Form */}
          <div className="col-md-4 mt-4 mt-md-0">
            <div className="card">
              <div className="card-header" style={{ backgroundColor: '#1AB0B0', color: '#fff' }}>
                Add New Exercise
              </div>
              <div className="card-body">
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Exercise Name</label>
                    <input
                      type="text"
                      name="name"
                      className="form-control"
                      value={newExercise.name}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Duration</label>
                    <input
                      type="text"
                      name="duration"
                      className="form-control"
                      value={newExercise.duration}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Date</label>
                    <input
                      type="date"
                      name="date"
                      className="form-control"
                      value={newExercise.date}
                      onChange={handleChange}
                      required
                    />
                  </div>
                  <button type="submit" className="btn" style={{ backgroundColor: '#1AB0B0', color: '#fff' }}>
                    Log Exercise
                  </button>
                </form>
                {/* TODO: Connect this form submission to backend */}
              </div>
            </div>
          </div>
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

export default ExercisePage;
