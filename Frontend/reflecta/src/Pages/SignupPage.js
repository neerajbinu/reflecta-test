import React from "react";
import LoginCard from "../Components/SignupCard";
import SignupCard from "../Components/SignupCard";


const   SignupPage = () => {
  return (
    <div  style={{
      backgroundImage: `url('/images/login 4.jpg')`,
      backgroundSize: "cover",
      backgroundPosition: "center",
      height: "100vh",
      width: "100vw",
      display: "flex",
      justifyContent: "center", // push card to the center
      alignItems: "center",
    }} >
      <SignupCard />
    </div>
  );
};

export default SignupPage;