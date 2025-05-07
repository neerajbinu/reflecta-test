// import React, { useState } from 'react';
// import { Line } from 'react-chartjs-2';
// import {
//   Chart as ChartJS,
//   CategoryScale,
//   LinearScale,
//   PointElement,
//   LineElement,
//   Title,
//   Tooltip,
//   Legend
// } from 'chart.js';
// import { Button, Row, Col, Card } from 'react-bootstrap';
// import { FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
// import { useNavigate } from 'react-router-dom';
// import  '../Components/water.css';

// ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

// const WaterIntakePage = () => {
//   const [waterConsumed, setWaterConsumed] = useState(0); // in ml
//   const [weeklyData, setWeeklyData] = useState([2400, 2000, 3000, 1700, 2600, 2800, 3000]); // Mock weekly data

//   const addWater = () => {
//     if (waterConsumed < 3000) {
//       setWaterConsumed(waterConsumed + 200);
//     }
//   };

//   const waterPercentage = (waterConsumed / 3000) * 100;

//   const chartData = {
//     labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
//     datasets: [
//       {
//         label: 'Water Intake (ml)',
//         data: weeklyData,
//         fill:false,
//         borderColor: 'rgba(26, 176, 176, 1)',
//         tension: 0.05,
//       },
//     ],
//   };

//   const navigate = useNavigate();

//   return (
//     <div className="d-flex vh-100">
      
//       {/* Sidebar */}
//       <div className="d-flex flex-column bg-dark text-white p-3" style={{ width: "300px" }}>
//         <h4>Reflecta</h4>
//         <p className="text-white fst-italic mb-4">Reflects you</p>
//         <SidebarLink icon={<FaHome />} label="Overview" onClick={() => navigate("/home")} />
//         <SidebarLink icon={<FaDumbbell />} label="Exercise"onClick={() => navigate("/exercise")} />
//         <SidebarLink icon={<FaUtensils />} label="Diet"onClick={() => navigate("/diet")} />
//         <SidebarLink icon={<FaTint />} label="Water"onClick={() => navigate("/water")} />
//         <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")}/>
//         <SidebarLink icon={<FaBook />} label="Journal"onClick={() => navigate("/journal")} />
//         <SidebarLink icon={<FaSignOutAlt />} label="Logout"onClick={() => navigate("/")} />
//       </div>

//       {/* Main Content */}
//       <div className="flex-grow-1 p-4 overflow-auto">
        
//         {/* Navbar */}
//         <div className="d-flex justify-content-between align-items-center mb-4">
//           <h3 className="text-dark m-0">Water Intake Tracker</h3>
//         </div>

//         {/* Glass of Water + Button */}
//         <Row>
//           <Col className="text-center">
//             <div className="glass-container">
//               <div className="glass-water" style={{ height: `${waterPercentage}%` }}></div>
//               <div className="glass-text">{waterConsumed} ml</div>
//             </div>

//             <Button
//               variant="primary"
//               className="mt-3"
//               onClick={addWater}
//               style={{ backgroundColor: "#1AB0B0", borderColor: "#1AB0B0" }}
//             >
//               Add a Glass + ({3000 - waterConsumed} ml left)
//             </Button>
//           </Col>
//         </Row>

//         {/* Weekly Chart */}
//         <Row className="mt-5">
//           <Col>
//             <h4>Water Intake Over the Week</h4>
//             <Card className="p-3 shadow">
//               <Line data={chartData} />
//             </Card>
//           </Col>
//         </Row>

//       </div>
//     </div>
//   );
// };

// const SidebarLink = ({ icon, label, selected, onClick }) => (
//   <div
//     className={`d-flex align-items-center gap-2 p-2 rounded ${selected ? "text-white" : "text-white"}`}
//     style={{
//       cursor: "pointer",
//       backgroundColor: selected ? "#1AB0B0" : "transparent",
//       transition: "background-color 0.2s",
//     }}
//     onClick={onClick}
//     onMouseOver={(e) => {
//       if (!selected) e.currentTarget.style.backgroundColor = "#1AB0B0";
//     }}
//     onMouseOut={(e) => {
//       if (!selected) e.currentTarget.style.backgroundColor = "transparent";
//     }}
//   >
//     {icon}
//     <span>{label}</span>
//   </div>
// );

// export default WaterIntakePage;
import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { format } from 'date-fns';
import { Home, Dumbbell, Apple, Droplets, Moon, BookText, LogOut, PlusCircle } from 'lucide-react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler
} from 'chart.js';
import 'bootstrap/dist/css/bootstrap.min.css';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler
);

const WaterIntake = () => {
  const [waterConsumed, setWaterConsumed] = useState(0);
  const [currentDate, setCurrentDate] = useState('');
  const [currentDay, setCurrentDay] = useState('');
  const DAILY_GOAL = 3000;
  const GLASS_SIZE = 200;

  useEffect(() => {
    const today = new Date();
    setCurrentDay(format(today, 'EEEE'));
    setCurrentDate(format(today, 'MMMM d, yyyy'));
  }, []);

  const addWater = () => {
    if (waterConsumed < DAILY_GOAL) {
      setWaterConsumed(Math.min(waterConsumed + GLASS_SIZE, DAILY_GOAL));
    }
  };

  const SidebarLink = ({ icon: Icon, label, active }) => (
    <li className={`sidebar-link ${active ? 'active' : ''}`}>
      <a href="#" className="d-flex align-items-center text-decoration-none text-white-50 py-2 px-3">
        <Icon size={24} className="me-3" />
        <span>{label}</span>
      </a>
    </li>
  );

  const chartData = {
    labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    datasets: [{
      label: 'Water Intake (ml)',
      data: [0, 1500, 2000, 2500, 1800, 2200, waterConsumed],
      fill: true,
      backgroundColor: 'rgba(26, 176, 176, 0.2)',
      borderColor: '#1AB0B0',
      tension: 0.4
    }]
  };

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { display: false },
      tooltip: {
        callbacks: {
          label: (context) => `${context.raw} ml`
        }
      }
    },
    scales: {
      y: {
        beginAtZero: true,
        max: 3500,
        ticks: {
          callback: (value) => `${value} ml`
        }
      }
    }
  };

  return (
    <div className="d-flex">
      <nav className="sidebar bg-dark" style={{ width: '300px', height: '100vh', position: 'fixed' }}>
        <div className="p-4">
          <div className="d-flex align-items-center mb-4">
            <Droplets size={32} className="text-info me-2" />
            <h1 className="h4 text-white m-0">Hydrate</h1>
          </div>
          <ul className="list-unstyled">
            <SidebarLink icon={Home} label="Home" />
            <SidebarLink icon={Dumbbell} label="Exercise" />
            <SidebarLink icon={Apple} label="Diet" />
            <SidebarLink icon={Droplets} label="Water" active />
            <SidebarLink icon={Moon} label="Sleep" />
            <SidebarLink icon={BookText} label="Journal" />
          </ul>
          <div className="mt-auto pt-4 border-top border-secondary">
            <SidebarLink icon={LogOut} label="Logout" />
          </div>
        </div>
      </nav>

      <main style={{ marginLeft: '300px', flex: 1 }}>
        <Container className="py-4">
          <Row className="text-center mb-4">
            <Col>
              <h1 className="display-4 mb-2">Water Intake Tracker</h1>
              <p className="text-muted">{currentDay}, {currentDate}</p>
            </Col>
          </Row>

          <Row className="justify-content-center mb-4">
            <Col xs={12} sm={8} md={6} lg={4}>
              <div className="position-relative" style={{ height: '300px' }}>
                <div className="glass">
                  <div 
                    className="water-fill"
                    style={{
                      height: `${(waterConsumed / DAILY_GOAL) * 100}%`,
                      transition: 'height 0.8s ease'
                    }}
                  />
                  <div className="text-center position-relative" style={{ zIndex: 2 }}>
                    <h3 className="h2 mb-2">{waterConsumed} ml</h3>
                    <p className="h4">{Math.round((waterConsumed / DAILY_GOAL) * 100)}%</p>
                  </div>
                </div>
              </div>
            </Col>
          </Row>

          <Row className="justify-content-center mb-5">
            <Col xs="auto">
              <Button
                variant="info"
                size="lg"
                onClick={addWater}
                disabled={waterConsumed >= DAILY_GOAL}
                className="d-flex align-items-center"
              >
                <PlusCircle size={20} className="me-2" />
                Add a Glass
                <span className="ms-2 opacity-75">
                  ({DAILY_GOAL - waterConsumed} ml left)
                </span>
              </Button>
            </Col>
          </Row>

          <Row>
            <Col>
              <div className="bg-white rounded shadow-sm p-4">
                <h2 className="h4 text-center mb-4">Weekly Progress</h2>
                <div style={{ height: '300px' }}>
                  <Line data={chartData} options={chartOptions} />
                </div>
              </div>
            </Col>
          </Row>
        </Container>
      </main>

      <style jsx>{`
        .glass {
          position: relative;
          height: 100%;
          background: linear-gradient(to right, rgba(255,255,255,0.1), rgba(255,255,255,0.3));
          border-radius: 20px 20px 60px 60px;
          overflow: hidden;
          box-shadow: 0 0 0 1px rgba(255,255,255,0.2), 0 20px 40px rgba(0,0,0,0.1);
          backdrop-filter: blur(10px);
        }

        .water-fill {
          position: absolute;
          bottom: 0;
          left: 0;
          width: 100%;
          background: linear-gradient(135deg, #1AB0B0 0%, #0597B7 100%);
          border-radius: 0 0 60px 60px;
        }

        .sidebar-link {
          border-radius: 8px;
          transition: all 0.2s ease;
        }

        .sidebar-link:hover {
          background-color: rgba(255,255,255,0.1);
        }

        .sidebar-link.active {
          background-color: rgba(26,176,176,0.15);
        }

        @media (max-width: 768px) {
          .sidebar {
            width: 70px !important;
          }
          
          .sidebar span {
            display: none;
          }
          
          main {
            margin-left: 70px !important;
          }
        }
      `}</style>
    </div>
  );
};

export default WaterIntake;