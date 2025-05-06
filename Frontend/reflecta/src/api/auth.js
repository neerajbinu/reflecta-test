import axios from 'axios';

// Base URL for your Auth-Service
const AUTH_API_BASE_URL = 'http://localhost:8080/api/auth'; // change port if needed

export const loginUser = async (email, password) => {
  const response = await axios.post(`${AUTH_API_BASE_URL}/login`, {
    email,    // sending email instead of username
    password,
  });
  return response.data.token; // backend should return JWT token in 'token' field
};

export const signupUser = async (username, email, password) => {
  const response = await axios.post(`${AUTH_API_BASE_URL}/signup`, {
    username,
    email,    // sending email here
    password,
  });
  return response.data; // could be success message
};
