// src/components/Login.js

import React, { useState } from "react";
import { json, useNavigate } from "react-router-dom";
import "./Login.css"; // Import CSS file

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    let valid = true;
    if (username == "" || password == "") {
      valid = false;
      setError("Tài khoản và mật khẩu không được để trống");
    } else {
      try {
        const response = await fetch(
          "http://localhost:8080/api/v1/user/login",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ userName: username, password: password }),
          }
        );
        if (!response.ok) {
          throw new Error("Invalid username or password. Please try again.");
        }
        const data = await response.json();
        console.log("Login successful:", data);
        if (data.jwt) {
          localStorage.setItem("info", JSON.stringify(data));
        }
        navigate("/");
        // Redirect or do something else after successful login
      } catch (error) {
        setError("Tài khoản hoặc mật khẩu không đúng");
        console.error("Login error:", error);
      }
    }
  };

  return (
    <div
      style={{
        height: "100vh",
        display: "flex",
        marginTop: "150px",
        justifyContent: "center",
      }}
    >
      <form style={{ width: "450px" }}>
        <header
          style={{
            fontSize: "48px",
            color: "#0d6efd",
            fontWeight: "bold",
            textAlign: "center",
            marginBottom: "40px",
          }}
        >
          Login
        </header>
        <div class="form-outline mb-4">
          <label class="form-label" htmlFor="form2Example1">
            Tài khoản:{" "}
          </label>
          <input
            type="email"
            id="form2Example1"
            class="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>

        <div class="form-outline mb-2">
          <label class="form-label" htmlFor="form2Example2">
            Mật khẩu
          </label>
          <input
            type="password"
            id="form2Example2"
            class="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        {!!error && (
          <div className="form-outline mb-4">
            <div
              class="invalid-feedback"
              style={{ display: "block", color: "red" }}
            >
              {error}
            </div>
          </div>
        )}

        <button
          type="button"
          class="btn btn-primary btn-block mb-4"
          style={{ width: "100%" }}
          onClick={(e) => handleSubmit(e)}
        >
          Đăng nhập
        </button>
      </form>
    </div>
  );
};

export default Login;
