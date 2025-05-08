import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  FaHome,
  FaDumbbell,
  FaUtensils,
  FaTint,
  FaBed,
  FaBullseye,
  FaBook,
  FaSignOutAlt,
} from "react-icons/fa";
import { BedDouble, Clock, Flame } from "lucide-react";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from "recharts";
import { Card } from "react-bootstrap";

const SidebarLink = ({ icon, label, onClick, selected }) => (
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

const SleepTracker = () => {
  const navigate = useNavigate();
  const [sessionActive, setSessionActive] = useState(false);
  const [startTime, setStartTime] = useState(null);
  const [sleepSessions, setSleepSessions] = useState([]);

  const sleepGoal = 8;
  const hoursSleptToday = 6.5;
  const sleepStreak = 4;
  const sleepScore = 82;
  const currentSleepSchedule = {
    start: "10:30 PM",
    end: "6:30 AM",
  };
  const weeklySleepData = [
    { day: "Mon", hours: 6.5 },
    { day: "Tue", hours: 7 },
    { day: "Wed", hours: 8 },
    { day: "Thu", hours: 5.5 },
    { day: "Fri", hours: 7.5 },
    { day: "Sat", hours: 6 },
    { day: "Sun", hours: 8 },
  ];

  const handleStartSession = () => {
    const now = new Date();
    setStartTime(now);
    setSessionActive(true);
  };

  const handleStopSession = () => {
    const endTime = new Date();
    const session = {
      start: startTime.toLocaleString(),
      end: endTime.toLocaleString(),
    };
    setSleepSessions([...sleepSessions, session]);
    setSessionActive(false);
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
                  <SidebarLink icon={<FaBed />} label="Sleep" onClick={() => navigate("/sleep")} selected/>
                  <SidebarLink icon={<FaBook />} label="Journal" onClick={() => navigate("/journal")} />
                  <SidebarLink icon={<FaBullseye />} label="Goals" onClick={() => navigate("/goals")}/>
                  <SidebarLink icon={<FaSignOutAlt />} label="Logout" onClick={() => navigate("/")} />
      </div>

      {/* Main Content */}
      <div className="flex-grow-1 p-4 overflow-auto">
        {/* Stats Cards */}
        <div className="row mb-4">
  <div className="col-md-4 mb-3">
    <Card
      className="shadow-sm"
      style={{ border: "4px solid #1AB0B0", minHeight: "150px" }}
    >
      <Card.Body className="d-flex align-items-center justify-content-between">
        <div>
          <h6>Today's Sleep</h6>
          <div>
            <span className="fw-bold display-6">{hoursSleptToday}</span>
            <span className="text-muted"> / {sleepGoal} hrs</span>
          </div>
          <br/>
          <br/>
          <p className="text-muted"> Your average sleep duration  </p>
        </div>
        <Clock size={70} color="#1AB0B0" />
      </Card.Body>
    </Card>
  </div>

  <div className="col-md-4 mb-3">
    <Card
      className="shadow-sm"
      style={{ border: "4px solid #1AB0B0", minHeight: "150px" }}
    >
      <Card.Body className="d-flex align-items-center justify-content-between">
        <div>
          <h6>Sleep Streak</h6>
          <div>
            <span className="fw-bold display-6">{sleepStreak}</span>
            <span className="text-muted"> days</span>
          </div>
          <br/>
          <p className="text-muted"> You've tracked your sleep for {sleepStreak} consecutive days</p>
        </div>
        <Flame size={86} color="#1AB0B0" />
      </Card.Body>
    </Card>
  </div>

  <div className="col-md-4 mb-3">
    <Card
      className="shadow-sm"
      style={{ border: "4px solid #1AB0B0", minHeight: "150px" }}
    >
      <Card.Body className="d-flex align-items-center justify-content-between">
        <div>
          <h6>Sleep Score</h6>
          <div>
            <span className="fw-bold display-6">{sleepScore}</span>
            <span className="text-muted"> / 100</span>
          </div>
          <br/>
          <p className="text-muted"> Based on recent sleep quality and duration</p>
        </div>
        <BedDouble size={76} color="#1AB0B0" />
      </Card.Body>
    </Card>
  </div>
</div>


        {/* Sleep Schedule */}
        <Card className="mb-4 shadow-sm" 
        style={{border:"2px solid #1AB0B0"}} >
          <Card.Body>
            <h5>Current Sleep Schedule</h5>
            <p>
              Start: {currentSleepSchedule.start} — End: {currentSleepSchedule.end}
            </p>
          </Card.Body>
        </Card>

        {/* Start/Stop Session Buttons */}
        <div className="text-center mb-4">
          {!sessionActive ? (
            <button  className="btn text-white"
            style={{ backgroundColor: "#1AB0B0", borderColor: "#1AB0B0" }} onClick={handleStartSession}>
              Start Sleep Session
            </button>
          ) : (
            <button className= "btn"
            style={{
              backgroundColor: "white",
              color: "#1AB0B0",
              border: "2px solid #1AB0B0",
              fontWeight: "bold"
            }} onClick={handleStopSession}>
              Stop Sleep Session
            </button>
          )}
        </div>

        {/* Sleep Sessions Table */}
        {sleepSessions.length > 0 && (
          <div className="mb-5">
            <h5>Sleep Sessions</h5>
            <table className="table table-bordered">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Start Time</th>
                  <th>End Time</th>
                </tr>
              </thead>
              <tbody>
                {sleepSessions.map((session, index) => (
                  <tr key={index}>
                    <td>{index + 1}</td>
                    <td>{session.start}</td>
                    <td>{session.end}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {/* Weekly Sleep Chart */}
        <Card className="mb-4 shadow-sm">
          <Card.Body>
            <h5 className="mb-3">Weekly Sleep Chart</h5>
            <ResponsiveContainer width="100%" height={300}>
              <LineChart data={weeklySleepData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="day" />
                <YAxis domain={[0, 10]} />
                <Tooltip />
                <Line type="monotone" dataKey="hours" stroke="#1AB0B0" strokeWidth={3} />
              </LineChart>
            </ResponsiveContainer>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
};

export default SleepTracker;
