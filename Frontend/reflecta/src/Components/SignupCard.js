import React, { useState } from "react";
import { signupUser } from "../api/auth"; // Make sure this path is correct!
import { Button, Form, Card } from "react-bootstrap";
import { FcGoogle } from "react-icons/fc";
import { FaRunning } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom"; // Import useNavigate hook

const SignupCard = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Declare useNavigate hook

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }
    try {
      await signupUser(username, email, password); // Send email along with username and password
      navigate('/'); // Redirect to login page after successful signup
    } catch (error) {
      console.error('Signup error:', error);
      setError('Signup failed. Please try again.');
    }
  };

  return (
    <Card
      style={{
        width: "28rem",
        backgroundColor: "rgba(255, 255, 255, 0.4)",
       // backdropFilter: "blur(10px)",
       // WebkitBackdropFilter: "blur(10px)",
        //border: "1px solid rgba(255, 255, 255, 0.3)",
      }}
      className="shadow-lg p-4 rounded-4"
    >
      <Card.Body>
        <h3 className="mb-4 text-center">Create Your Account</h3>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="formUsername" className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formEmail" className="mb-3">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formPassword" className="mb-3">
            <Form.Label>New Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Create password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formConfirmPassword" className="mb-4">
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Confirm password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </Form.Group>

          <Button variant="primary" type="submit" className="w-100 mb-3">
            Sign Up
          </Button>

          {/* Show error if signup fails */}
          {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}

          <div className="d-flex flex-column gap-2">
            <Button variant="outline-primary" className="w-100">
              <FcGoogle className="me-2" /> Sign up with Google
            </Button>
            <Button variant="outline-primary" className="w-100">
              <FaRunning className="me-2" /> Sign up with Fitbit
            </Button>
          </div>

          <div className="text-center mt-3">
            Already have an account?{" "}
            <Link to="/" className="text-primary fw-semibold">
              Login
            </Link>
          </div>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default SignupCard;
