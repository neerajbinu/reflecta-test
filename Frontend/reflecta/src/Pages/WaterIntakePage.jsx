import React, { useState } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';
import { Button, Container, Row, Col } from 'react-bootstrap';

// Chart.js registration
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const WaterIntakePage = () => {
  const [waterConsumed, setWaterConsumed] = useState(0); // in ml
  const [weeklyData, setWeeklyData] = useState([0, 500, 1000, 1500, 2000, 2500, 3000]); // Mock weekly data

  // Function to handle the button click to add 200 ml
  const addWater = () => {
    if (waterConsumed < 3000) {
      setWaterConsumed(waterConsumed + 200);
    }
  };

  // Water Droplet fill percentage based on daily consumption
  const waterPercentage = (waterConsumed / 3000) * 100;

  // Chart Data (Water Intake per Day over the Week)
  const chartData = {
    labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    datasets: [
      {
        label: 'Water Intake (ml)',
        data: weeklyData,
        fill: false,
        borderColor: 'rgba(26, 176, 176, 1)',
        tension: 0.1,
      },
    ],
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col className="text-center">
          <h2>Water Intake Tracker</h2>
          <div className="mt-4" style={{ position: 'relative', width: '150px', height: '150px', borderRadius: '50%', border: '5px solid #1AB0B0', overflow: 'hidden' }}>
            <div style={{
              position: 'absolute',
              bottom: '0',
              width: '100%',
              height: `${waterPercentage}%`,
              backgroundColor: '#1AB0B0',
              transition: 'height 0.3s ease-in-out',
            }}></div>
            <div className="text-center text-white" style={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)' }}>
              {waterConsumed} ml
            </div>
          </div>
          <Button variant="primary" className="mt-3" onClick={addWater}>
            + 200 ml
          </Button>
        </Col>
      </Row>
      
      <Row className="mt-5">
        <Col>
          <h3>Water Intake Over the Week</h3>
          <Line data={chartData} />
        </Col>
      </Row>
    </Container>
  );
};



export default WaterIntakePage;
