import React, { useContext, useState } from "react";
import { registerCandidate } from "../api/candidateApi";
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
import { CandidateContext } from "../context/CandidateContext";

function CandidateRegistrationPage() {
  const { setCandidates } = useContext(CandidateContext);
  const [passwordError, setPasswordError] = useState("");
  const [candidate, setCandidate] = useState({
    name: "",
    party: "",
    constituency: "",
    emailAddress: "",
    state: "",
    password: "",
  });

  // Mapping of state to constituencies
  const stateConstituencies = {
    Maharashtra: ["Mumbai South", "Pune", "Nagpur"],
    Karnataka: ["Bangalore South", "Mysore", "Mangalore"],
    TamilNadu: ["Chennai Central", "Coimbatore", "Madurai"],
    Delhi: ["New Delhi", "Chandni Chowk", "South Delhi"],
    Haryana: ["Gurgaon", "Faridabad", "Ambala"],
    Punjab: ["Amritsar", "Ludhiana", "Jalandhar"],
    Gujarat: ["Ahmedabad East", "Surat", "Vadodara"],
    Rajasthan: ["Jaipur", "Jodhpur", "Udaipur"],
    UttarPradesh: ["Lucknow", "Varanasi", "Agra"],
    WestBengal: ["Kolkata North", "Howrah", "Darjeeling"],
    Telangana: ["Hyderabad", "Secunderabad", "Warangal"],
    AndhraPradesh: ["Visakhapatnam", "Tirupati", "Vijayawada"],
    Kerala: ["Thiruvananthapuram", "Kochi", "Kozhikode"],
    Bihar: ["Patna Sahib", "Pataliputra", "Ara"],
    Odisha: ["Bhubaneswar", "Cuttack", "Berhampur"],
    MadhyaPradesh: ["Bhopal", "Indore", "Gwalior"],
    Chhattisgarh: ["Raipur", "Bilaspur", "Durg"],
    Jharkhand: ["Ranchi", "Jamshedpur", "Dhanbad"],
    Uttarakhand: ["Dehradun", "Haridwar", "Nainital"],
    HimachalPradesh: ["Shimla", "Kullu", "Mandi"],
    JammuKashmir: ["Srinagar", "Jammu", "Anantnag"],
    Goa: ["North Goa", "South Goa"],
    ArunachalPradesh: ["Arunachal West", "Arunachal East"],
  };

  const resetCandidate = () => {
    setCandidate({
      name: "",
      party: "",
      constituency: "",
      emailAddress: "",
      state: "",
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

    setCandidate((prev) => ({
      ...prev,
      [type]: value,
      // If state changes, reset constituency
      ...(type === "state" ? { constituency: "" } : {}),
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerCandidate(candidate);
      alert("Candidate registered successfully");
      setCandidates((prevCandidates) => [...prevCandidates, candidate]);
    } catch (error) {
       console.error("Error registering voter:", error.response.data.error);
      alert(error.response.data.error);
    }
    resetCandidate();
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
                value={candidate.name}
                onChange={(e) => handleChange("name", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="party">Party</Label>
              <Input
                type="text"
                id="party"
                placeholder="Enter Party"
                value={candidate.party}
                onChange={(e) => handleChange("party", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="email">Email</Label>
              <Input
                type="email"
                id="emailAddress"
                placeholder="Enter Email"
                value={candidate.emailAddress}
                onChange={(e) => handleChange("emailAddress", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="state">State</Label>
              <Input
                type="select"
                id="state"
                value={candidate.state}
                onChange={(e) => handleChange("state", e)}
                required
              >
                <option value="">Select State</option>
                <option value="Maharashtra">Maharashtra</option>
                <option value="Karnataka">Karnataka</option>
                <option value="TamilNadu">Tamil Nadu</option>
                <option value="Delhi">Delhi</option>
                <option value="Haryana">Haryana</option>
                <option value="Punjab">Punjab</option>
                <option value="Gujarat">Gujarat</option>
                <option value="Rajasthan">Rajasthan</option>
                <option value="UttarPradesh">Uttar Pradesh</option>
                <option value="WestBengal">West Bengal</option>
                <option value="Telangana">Telangana</option>
                <option value="AndhraPradesh">Andhra Pradesh</option>
                <option value="Kerala">Kerala</option>
                <option value="Bihar">Bihar</option>
                <option value="Odisha">Odisha</option>
                <option value="MadhyaPradesh">Madhya Pradesh</option>
                <option value="Chhattisgarh">Chhattisgarh</option>
                <option value="Jharkhand">Jharkhand</option>
                <option value="Uttarakhand">Uttarakhand</option>
                <option value="Himachal Pradesh">Himachal Pradesh</option>
                <option value="JammuKashmir">Jammu & Kashmir</option>
                <option value="Goa">Goa</option>
                <option value="ArunachalPradesh">Arunachal Pradesh</option>
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="constituency">Constituency</Label>
              <Input
                type="select"
                id="constituency"
                value={candidate.constituency}
                onChange={(e) => handleChange("constituency", e)}
                required
                disabled={!candidate.state} // Disable until state selected
              >
                <option value="">Select Constituency</option>
                {candidate.state &&
                  stateConstituencies[candidate.state].map((c) => (
                    <option key={c} value={c}>
                      {c}
                    </option>
                  ))}
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="password">Password</Label>
              <Input
                type="password"
                id="password"
                placeholder="Enter Password"
                value={candidate.password}
                onChange={(e) => handleChange("password", e)}
                required
              />
              {passwordError && (
                <small style={{ color: "red" }}>{passwordError}</small>
              )}
            </FormGroup>
            <Container className="text-center">
              <div className="d-flex justify-content-center">
                <Button type="submit"  disabled={!!passwordError || candidate.password===""}           >Register</Button>
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

export default CandidateRegistrationPage;
