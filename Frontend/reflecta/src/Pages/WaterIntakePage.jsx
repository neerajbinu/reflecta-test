import React, { useState } from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js';
import { Button, Row, Col, Card } from 'react-bootstrap';
import { FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBullseye, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';
import  '../Components/water.css';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const WaterIntakePage = () => {
  const [waterConsumed, setWaterConsumed] = useState(0); // in ml
  const [weeklyData, setWeeklyData] = useState([2400, 2000, 3000, 1700, 2600, 2800, 3000]); // Mock weekly data

  const addWater = () => {
    if (waterConsumed < 3000) {
      setWaterConsumed(waterConsumed + 200);
    }
  };

  const waterPercentage = (waterConsumed / 3000) * 100;

  const chartData = {
    labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    datasets: [
      {
        label: 'Water Intake (ml)',
        data: weeklyData,
        fill:false,
        borderColor: 'rgba(26, 176, 176, 1)',
        tension: 0.05,
      },
    ],
  };

  const navigate = useNavigate();

  return (
    <div className="d-flex vh-100">
      
      {/* Sidebar */}
      <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
  <h4>Reflecta</h4>
  <p className="text-white fst-italic mb-4">Reflects you</p>
    <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
                <SidebarLink icon={<FaDumbbell />} label="Exercise" onClick={() => navigate("/exercise")} />
                <SidebarLink icon={<FaUtensils />} label="Diet" onClick={() => navigate("/diet")}  />
                <SidebarLink icon={<FaTint />} label="Water" onClick={() => navigate("/water")} selected />
                <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")}/>
                <SidebarLink icon={<FaBook />} label="Journal" onClick={() => navigate("/journal")} />
                <SidebarLink icon={<FaBullseye />} label="Goals" onClick={() => navigate("/goals")}/>
                <SidebarLink icon={<FaSignOutAlt />} label="Logout" onClick={() => navigate("/")} />
</div>

      {/* Main Content */}
      <div className="flex-grow-1 p-4 overflow-auto">
        
        {/* Navbar */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h3 className="text-dark m-0">Water Intake Tracker</h3>
        </div>

        {/* Glass of Water + Button */}
        <Row>
          <Col className="text-center">
            <div className="glass-container">
              <div className="glass-water" style={{ height: `${waterPercentage}%` }}></div>
              <div className="glass-text">{waterConsumed} ml</div>
            </div>

            <Button
              variant="primary"
              className="mt-3"
              onClick={addWater}
              style={{ backgroundColor: "#1AB0B0", borderColor: "#1AB0B0" }}
            >
              Add a Glass + ({3000 - waterConsumed} ml left)
            </Button>
          </Col>
        </Row>

        {/* Weekly Chart */}
        <Row className="mt-5">
          <Col>
            <h4>Water Intake Over the Week</h4>
            <Card className="p-3 shadow">
              <Line data={chartData} />
            </Card>
          </Col>
        </Row>

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

export default WaterIntakePage;
