import React, { useContext, useState } from "react";
import { registerVoter } from "../../api/voterApi";
import { Container } from "reactstrap";
import {
  Card,
  CardBody,
  CardHeader,
  FormGroup,
  Input,
  Label,
  Form,
  Button,
} from "reactstrap";
import { Link } from "react-router-dom";
import { VoterContext } from "../../context/VoterContext";
function VoterRegistrationPage() {
  const { setVoters } = useContext(VoterContext);

  const [passwordError, setPasswordError] = useState("");
  const [voter, setVoter] = useState({
    name: "",
    aadharNumber: "",
    emailAddress: "",
    age: "",
    constituency: "",
    hasVoted: false,
    password: "",
    state: "",
  });

  const resetVoter = () => {
    setVoter({
      name: "",
      aadharNumber: "",
      emailAddress: "",
      age: "",
      constituency: "",
      hasVoted: false,
      password: "",
      state: "",
    });
  };
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
    setVoter((prev) => ({
      ...prev,
      [type]: value,
      // If state changes, reset constituency
      ...(type === "state" ? { constituency: "" } : {}),
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerVoter(voter);
      setVoters((prevVoters) => [...prevVoters, voter]);
      alert("Voter succesfully registred.");
    } catch (error) {
      console.error("Error registering voter:", error.response.data.error);
      alert(error.response.data.error);
    }
    resetVoter();
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <Card inverse style={{ backgroundColor: "black", marginBottom: "145px" }}>
        <CardHeader>
          <h3>Fill Information for Register (Voter)</h3>
        </CardHeader>
        <CardBody>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for="name">Name</Label>
              <Input
                type="text"
                id="name"
                placeholder="Enter Name"
                value={voter.name}
                onChange={(e) => handleChange("name", e)}
                required
              />
            </FormGroup>

            <FormGroup>
              <Label for="age">Age</Label>
              <Input
                id="age"
                type="number"
                placeholder="Enter age"
                onChange={(e) => handleChange("age", e)}
                value={voter.age}
                required
              />
            </FormGroup>

            <FormGroup>
              <Label for="aadharNumber">Enter Aadhar Number</Label>
              <Input
                id="aadharNumber"
                type="text"
                value={voter.aadharNumber}
                placeholder="Enter aadhar number"
                onChange={(e) => handleChange("aadharNumber", e)}
                required
              />
            </FormGroup>

            <FormGroup>
              <Label for="emailAddress">Enter email Address</Label>
              <Input
                type="email"
                id="emailAddress"
                value={voter.emailAddress}
                placeholder="Enter emailAddress"
                onChange={(e) => handleChange("emailAddress", e)}
                required
              />
            </FormGroup>
            <FormGroup>
              <Label for="state">State</Label>
              <Input
                type="select"
                id="select"
                value={voter.state}
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
              <Label for="constituency">Enter constituency</Label>
              <Input
                id="constituency"
                type="select"
                value={voter.constituency}
                placeholder="Enter constituency"
                onChange={(e) => handleChange("constituency", e)}
                required
                disabled={!voter.state}
              >
                <option value="">Select Constituency</option>

                {voter.state &&
                  stateConstituencies[voter.state].map((c) => (
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
                value={voter.password}
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
                  disabled={!!passwordError || voter.password === ""}
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

export default VoterRegistrationPage;
