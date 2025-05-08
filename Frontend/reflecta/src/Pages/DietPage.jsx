import React, { useState, useEffect } from "react";
import { Button, Table, Form } from "react-bootstrap";
import { FaHome, FaDumbbell, FaUtensils, FaTint, FaBed, FaBook, FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const DietPage = () => {
  const [mealLogs, setMealLogs] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [name, setName] = useState("");
  const [servings, setServings] = useState("");
  const [mealType, setMealType] = useState("BREAKFAST");

  useEffect(() => {
    const savedMealLogs = JSON.parse(localStorage.getItem("mealLogs")) || [];
    setMealLogs(savedMealLogs);
  }, []);

  const handleAddMealClick = () => {
    setShowForm(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newMeal = {
      id: Date.now(),
      date: new Date().toLocaleDateString(),
      name,
      servings: parseFloat(servings),
      type: mealType,
      totalCalories: 400,   // Placeholder - Replace with actual logic if needed
      totalCarbs: 50,
      totalProtein: 20,
      totalFat: 15,
      totalFibre: 8,
      totalSugar: 12,
    };
    const updatedMeals = [...mealLogs, newMeal];
    setMealLogs(updatedMeals);
    localStorage.setItem("mealLogs", JSON.stringify(updatedMeals));

    // Reset form
    setName("");
    setServings("");
    setMealType("BREAKFAST");
    setShowForm(false);
  };

  const navigate = useNavigate();

  return (
    <div className="d-flex vh-100">
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
        <SidebarLink icon={<FaSignOutAlt />} label="Logout" />
      </div>

      <div className="flex-grow-1 p-4">
        <h3>Meal Log</h3>
        {!showForm && (
          <Button onClick={handleAddMealClick}>Add Meal</Button>
)}
        {showForm && (
        <Form className="mt-3 p-3 border rounded bg-light" onSubmit={handleSubmit}>
        <div className="d-flex justify-content-end">
        <button
        type="button"
        className="btn-close"
        aria-label="Close"
        onClick={() => setShowForm(false)}
        ></button>
    </div>

    <Form.Group className="mb-2">
      <Form.Label>Food Name</Form.Label>
      <Form.Control
        type="text"
        placeholder="Enter food name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
    </Form.Group>

            <Form.Group className="mb-2">
              <Form.Label>Servings</Form.Label>
              <Form.Control
                type="number"
                min="0"
                step="0.1"
                placeholder="Enter number of servings"
                value={servings}
                onChange={(e) => setServings(e.target.value)}
                required
              />
            </Form.Group>

            <Form.Group className="mb-2">
              <Form.Label>Meal Type</Form.Label>
              <Form.Select
                value={mealType}
                onChange={(e) => setMealType(e.target.value)}
              >
                <option value="BREAKFAST">Breakfast</option>
                <option value="LUNCH">Lunch</option>
                <option value="DINNER">Dinner</option>
                <option value="SNACK">Snack</option>
              </Form.Select>
            </Form.Group>

            <Button type="submit" variant="success" className="mt-2">Save Meal</Button>
          </Form>
        )}

        <Table striped bordered hover responsive className="mt-4">
          <thead>
            <tr>
              <th>Date</th>
              <th>Name</th>
              <th>Type</th>
              <th>Servings</th>
              <th>Calories</th>
              <th>Carbs</th>
              <th>Protein</th>
              <th>Fat</th>
              <th>Fibre</th>
              <th>Sugar</th>
            </tr>
          </thead>
          <tbody>
            {mealLogs.map((meal) => (
              <tr key={meal.id}>
                <td>{meal.date}</td>
                <td>{meal.name}</td>
                <td>{meal.type}</td>
                <td>{meal.servings}</td>
                <td>{meal.totalCalories}</td>
                <td>{meal.totalCarbs}</td>
                <td>{meal.totalProtein}</td>
                <td>{meal.totalFat}</td>
                <td>{meal.totalFibre}</td>
                <td>{meal.totalSugar}</td>
              </tr>
            ))}
          </tbody>
        </Table>
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

export default DietPage;
