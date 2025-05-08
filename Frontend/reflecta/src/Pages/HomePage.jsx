import React, { useEffect, useState } from "react";
import { Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
//import { jwtDecode } from "jwt-decode";
import {
  FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook,
  FaSignOutAlt, FaBell, FaUserCircle, FaRunning, FaFire,
  FaHeartbeat
} from "react-icons/fa";
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, LineElement, PointElement, LinearScale, CategoryScale } from 'chart.js';
import { CircularProgressbar, buildStyles } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';
import 'bootstrap/dist/css/bootstrap.min.css';

ChartJS.register(LineElement, PointElement, LinearScale, CategoryScale);

const HomePage = () => {
  const [userData, setUserData] = useState(null);
  const [showProfileSidebar, setShowProfileSidebar] = useState(false);

   const navigate = useNavigate();

  // useEffect(() => {
  //   const token = localStorage.getItem("token");
  //   if (!token) {
  //     navigate("/"); // Redirect if token is missing
  //     return;
  //   }
  
  //   try {
  //     const decoded = jwtDecode(token);
  //     console.log("Decoded token:", decoded);
  //     // You can optionally use decoded info here (like user name, ID, etc.)
  //   } catch (err) {
  //     console.error("Invalid token", err);
  //     localStorage.removeItem("token");
  //     navigate("/");
  //   }
  // }, []);


  useEffect(() => {
    const mockData = {
      name: "Neeraj",
      age: 28,
      weight: "72 kg",
      height: "178 cm",
      sleepHours: 6,
      sleepGoal: 8,
      restfulness: 85,
      waterLitres: 1.8,
      waterGoalLitres: 2.5,
      steps: 7500,
      stepsGoal: 10000,
      calories: 1700,
      calorieGoal: 2200,
      heartRate: [70, 72, 68, 74, 73, 69, 71],
    };
    setUserData(mockData);
  }, []);



  if (!userData) return <div>Loading...</div>;

  const weekDates = Array.from({ length: 7 }, (_, i) => {
    const date = new Date();
    date.setDate(date.getDate() - date.getDay() + i);
    return date;
  });

  const isToday = (date) => {
    const today = new Date();
    return date.getDate() === today.getDate() &&
      date.getMonth() === today.getMonth() &&
      date.getFullYear() === today.getFullYear();
  };

  return (
    <div className="d-flex vh-100" >

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
        <SidebarLink icon={<FaDumbbell />} label="Goals" onClick={() => navigate("/goals")} />
        <SidebarLink icon={<FaSignOutAlt />} label="Logout" />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 p-4 position-relative">
      <div className="d-flex justify-content-between align-items-center p-1">
  <h3 className="text-dark m-0">Welcome, {userData.name}!</h3>
  <div className="d-flex gap-3">
    <FaBell size={25} style={{ cursor: "pointer", color: "black" }} />
    <FaUserCircle size={25} style={{ cursor: "pointer", color: "black" }} onClick={() => setShowProfileSidebar(!showProfileSidebar)}
    />
  </div>
  </div>

        {/* Stat Cards */}
        <div className="d-flex flex-wrap gap-3 my-4">
          <Card className="text-white p-2" style={{ backgroundColor: "#1AB0B0", width: "130px", height: "120px" }}>
            <p><FaRunning className="me-1" />Steps</p>
            <div className="progress" style={{ height: "5px" }}>
              <div className="progress-bar bg-white" style={{ width: `${(userData.steps / userData.stepsGoal) * 100}%` }}></div>
            </div>
            <p className="mt-2 mb-0">{userData.steps} <small>steps</small></p>
          </Card>


          <Card className="text-white p-2 text-center" style={{ backgroundColor: "#FF7443", width: "130px", height: "120px" }}>
            <p><FaTint className="me-1" />Water</p>
            <div style={{ width: 40, margin: "0 auto" }}>
              <CircularProgressbar
                value={(userData.waterLitres / userData.waterGoalLitres) * 100}
                styles={buildStyles({ pathColor: "#fff", textColor: "#fff", trailColor: "#ffa07a", textSize: "16px" })}
              />
            </div>
            <small>{userData.waterLitres}L</small>
          </Card>


          <Card className="text-white p-2" style={{ backgroundColor: "#FA5A7D", width: "130px", height: "120px" }}>
            <p><FaFire className="me-1" />Calories</p>
            <p>{userData.calories} / {userData.calorieGoal}</p>
          </Card>


          <Card className="text-white p-2" style={{ backgroundColor: "#8676FE", width: "130px", height: "120px" }}>
            <p><FaRunning className="me-1" />Restfulness</p>
            <p className="fw-bold" style={{ fontSize: "2rem" }}>{userData.restfulness}<span className="fs-5 fw-light"> /100</span></p>
          </Card>


          <Card className="text-white p-2" style={{ backgroundColor: "#B7A76C", width: "130px", height: "120px" }}>
            <p><FaBed className="me-1" />Sleep</p>
            <p className="fw-bold" style={{ fontSize: "2.8rem", marginBottom: "0" }}>{userData.sleepHours}<span className="fs-6 fw-normal"> Hours</span></p>
          </Card>


          <Card className="text-white p-2" style={{ backgroundColor: "#A05351", width: "320px", height: "140px" }}>
            <p><FaHeartbeat className="me-1" />Heart Rate</p>
            <Line
              data={{
                labels: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
                datasets: [{
                  data: userData.heartRate,
                  borderColor: "#fff",
                  tension: 0.7,
                }]
              }}
              options={{
                scales: {
                  x: { ticks: { color: '#fff' }, grid: { display: false } },
                  y: { ticks: { color: '#fff' }, grid: { color: 'rgba(255,255,255,0.1)' } }
                },
                plugins: { legend: { display: false } },
                elements: { point: { radius: 3 } }
              }}
              height={70}
            />
          </Card>
        </div>

        {/* Weekly Calendar */}
        <div className="d-flex p-3 bg-white rounded shadow position-absolute bottom-0 start-0 ms-4 mb-3 gap-2 overflow-auto">
          {weekDates.map((date, i) => (
            <div key={i} className={`text-center p-2 rounded ${isToday(date) ? 'border border-dark' : 'border border-secondary bg-light'}`} style={{ backgroundColor: isToday(date) ? "#1AB0B0" : "",minWidth: "50px" }}>
              <div>{date.toLocaleDateString('en-US', { weekday: 'short' })}</div>
              <div>{date.getDate()}</div>
            </div>
          ))}
        </div>

        {/* Profile Sidebar */}
        {showProfileSidebar && (
          <div className="position-fixed top-0 end-0 h-100 bg-white shadow p-3" style={{ width: "250px", zIndex: 1000 }}>
            <h5>User Profile</h5>
            <p><strong>Name:</strong> {userData.name}</p>
            <p><strong>Age:</strong> {userData.age}</p>
            <p><strong>Weight:</strong> {userData.weight}</p>
            <p><strong>Height:</strong> {userData.height}</p>
            <Button variant="secondary" size="sm" onClick={() => setShowProfileSidebar(false)}>Close</Button>
          </div>
        )}
      </div>
    </div>
  );
};

const SidebarLink = ({ icon, label, selected, onClick }) => (
  <div
    className={`d-flex align-items-center gap-2 p-2 rounded ${
      selected ? "text-white" : "text-white"
    }`}
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


export default HomePage;