import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from '../components/Navbar'; // Reuse your existing navbar

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

  return (
    <div className="d-flex flex-column" style={{ minHeight: '100vh' }}>
      <Navbar />

      <div className="container mt-4">
        <h2 className="text-center mb-4" style={{ color: '#1AB0B0' }}>
          Exercise Tracker
        </h2>

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

export default ExercisePage;
