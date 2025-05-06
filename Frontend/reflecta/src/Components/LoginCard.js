import React, { useState } from "react";
import { loginUser } from "../api/auth"; // Make sure this path is correct!
import { Button, Form, Card } from "react-bootstrap";
import { FcGoogle } from "react-icons/fc";
import { FaRunning } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom"; // Import useNavigate hook

const LoginCard = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Declare useNavigate hook

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = await loginUser(email, password);  // Assuming this returns the token
      localStorage.setItem('authToken', token);
      navigate('/home'); // Redirect to the HomePage after successful login
    } catch (error) {
      console.error('Login error:', error);
      setError('Invalid email or password');
    }
  };

  return (
    <Card
      style={{
        width: "25rem",
        backgroundColor: "rgba(255, 255, 255, 0.4)",
       //backdropFilter: "blur(10px)",
       // WebkitBackdropFilter: "blur(10px)",
        //border: "1px solid rgba(255, 255, 255, 0.3)",
      }}
      className="shadow-lg p-4 rounded-4"
    >
      <Card.Body>
        <h3 className="mb-4 text-center">Sign In</h3>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="formBasicEmail" className="mb-3">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formBasicPassword" className="mb-4">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>

          <Button variant="primary" type="submit" className="w-100 mb-3">
            Sign In
          </Button>

          {/* Show error if login fails */}
          {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}

          <div className="d-flex flex-column gap-2">
            <Button variant="outline-primary" className="w-100">
              <FcGoogle className="me-2" /> Sign in with Google
            </Button>
            <Button variant="outline-primary" className="w-100">
              <FaRunning className="me-2" /> Sign in with Fitbit
            </Button>
          </div>

          <div className="text-center mt-3">
            Don't have an account?{" "}
            <Link to="/signup" className="text-primary fw-semibold">
              Sign Up
            </Link>
          </div>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default LoginCard;
