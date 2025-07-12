import React, { useState } from "react";
import {
  Card,
  CardBody,
  CardHeader,
  Container,
  FormGroup,
  Input,
  Label,
  Form,
  Button,
} from "reactstrap";

import { login } from "../api/LoginApi";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { emailAtom } from "../state/emailAtom";
function LoginPage() {
  const [email, setEmail] = useRecoilState(emailAtom)
  
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();
  const handleChange = (type, e) => {
    const value = e.target.value;
    if (type === "password") {
      // Validate password: at least one uppercase, one special char, length > 8
      const regex = /^(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}[\]:;<>,.?~\\/-]).{9,}$/;
      if (!regex.test(value)) {
        setPasswordError(
          "Password must be >8 chars, include 1 uppercase & 1 special char"
        );
      } else {
        setPasswordError("");
      }
    }
    if (type === "email") {
      setEmail(value);
    } else {
      setPassword(value);
    }
  };

  const resetLogin = () => {
    setEmail("");
    setPassword("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log("Email:", email);
    try {
      const response = await login({ email, password });
console.log("Login response:", response);
      const { userType } = response;
      console.log("User Type:", userType);

      if (userType === "admin") {
        navigate("/admin/dashboard");
      } else if (userType === "CANDIDATE") {
        navigate("/candidate/dashboard");
      } else if (userType === "VOTER") {
        navigate("/voter/dashboard");
      }
    } catch (error) {
      console.error("Error login:", error.response.data.error);
      alert(error.response.data.error);
      navigate("/login");
      resetLogin();
    }

    // Redirect to the home page or dashboard

    
  };
  return (
    <Container>
      <div className="responsive-login-wrapper">
        <Card inverse style={{ backgroundColor: "black" }}>
          <CardHeader>
            <h3 style={{ textAlign: "center" }}>Login Page </h3>
          </CardHeader>
          <CardBody>
            <Form onSubmit={handleSubmit}>
              <FormGroup>
                <Label for="email">Enter email</Label>
                <Input
                  type="text"
                  id="email"
                  placeholder="Enter email"
                  value={email}
                  onChange={(e) => handleChange("email", e)}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="password">Enter password</Label>
                <Input
                  type="password"
                  id="password"
                  placeholder="Enter password"
                  value={password}
                  onChange={(e) => handleChange("password", e)}
                  required
                />
                {passwordError && (
                  <small style={{ color: "red" }}>{passwordError}</small>
                )}
              </FormGroup>
              <Container className="text-center">
                <div className="d-flex justify-content-center">
                  <Button
                    color="info"
                    type="submit"
                    size="lg"
                    className="mr-2 mx-2"
                    disabled={!!passwordError || password === ""}
                  >
                    Login
                  </Button>
                </div>
              </Container>
            </Form>
          </CardBody>
        </Card>
      </div>
    </Container>
  );
}

export default LoginPage;
