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
import { Link } from "react-router-dom";
import { registerAdmin } from "../../api/AdminApi";
function AdminRegistration() {
  const [passwordError, setPasswordError] = useState("");

  const [admin, setAdmin] = useState({
    name: "",
    email: "",
    password: "",
  });

  const resetAdmin = () => {
    setAdmin({
      name: "",
      email: "",
      password: "",
    });
  };
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
    setAdmin({
      ...admin,
      [type]: value,
      ...(type === "state" ? { constituency: "" } : {}),
    });
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await registerAdmin(admin);
      alert("Admin registred succesfully.");
    } catch (error) {
      console.error("Error registering admin:", error.response.data.error);
      alert(error.response.data.error);
    }
    resetAdmin();
  };
  return (
    <Container style={{ marginTop: "20px" }}>
      <Card inverse style={{ backgroundColor: "black", marginBottom: "145px" }}>
        <CardHeader>
          <h3>Fill Information for Register (Candidate)</h3>
        </CardHeader>
        <CardBody>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for="name">Name</Label>
              <Input
                type="text"
                id="name"
                placeholder="Enter Name"
                value={admin.name}
                onChange={(e) => handleChange("name", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="email">Email</Label>
              <Input
                type="email"
                id="email"
                placeholder="Enter email"
                value={admin.email}
                onChange={(e) => handleChange("email", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="password">Password</Label>
              <Input
                type="password"
                id="password"
                placeholder="Enter password"
                value={admin.password}
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
                  type="submit"
                  color="primary"
                  disabled={!!passwordError || admin.password === ""}
                >
                  Register
                </Button>
                <Link to="/login">
                  <Button color="info" size="lg" className="mr-2 mx-2">
                    Login
                  </Button>
                </Link>
              </div>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </Container>
  );
}

export default AdminRegistration;
