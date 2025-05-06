
import React from "react";
import LoginCard from "../Components/LoginCard";

const LoginPage = () => {
  return (
    <div
      style={{
        backgroundImage: `url('/images/login 4.jpg')`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
        width: "100vw",
        display: "flex",
        justifyContent: "center", // push card to the center
        alignItems: "center",
      }}
    >
      <LoginCard />
    </div>
  );
};

export default LoginPage;

