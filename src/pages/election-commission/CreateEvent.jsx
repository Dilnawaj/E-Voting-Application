import React, { useContext, useState } from "react";
import Base from "../../Base";
import {
  Container,
  Card,
  Label,
  Button,
  CardHeader,
  CardBody,
  FormGroup,
  Input
} from "reactstrap";
import { Form } from "react-router-dom";
import { registerEvent } from "../../api/AdminApi";
import { AdminContext } from "../../context/AdminContext";
import { Link } from "react-router-dom";
const CreateEvent = () => {
  
  const { admin } = useContext(AdminContext);
  const [event, setEvent] = useState({
    name: "",
    message: "",
    constituency: "",
    startDate: "",
    endDate: "",
  });

  const handleChange = (type, e) => {
    const value = e.target.value;
    setEvent({ ...event, [type]: value });
  };
  const resetEvent = () => {
    setEvent({
      name: "",
      message: "",
      constituency: "",
      startDate: "",
      endDate: "",
    });
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    try {
      registerEvent(event, admin.emailAddress);
      alert("Event created successfully.");
      resetEvent();
    } catch (error) {
      console.error("Error creating event:", error.response.data.error);
      alert(error.response.data.error);
    }
  };
  return (
    <Base>
      <Container style={{ marginTop: "20px" }}>
        <Card
          inverse
          style={{ backgroundColor: "grey", marginBottom: "145px" }}
        >
          <CardHeader>
            <h3 style={{ color: "white", textAlign: "center" }}>
              Create Election Event
            </h3>
          </CardHeader>
          <CardBody>
            <FormGroup>
              <Label for="name">Event Name</Label>
              <Input
                type="text"
                id="name"
                placeholder="Enter Event Name"
                onChange={(e) => handleChange("name", e)}
              />
            </FormGroup>

            <FormGroup>
              <Label for="eventDate">Event Message</Label>
              <Input
                type="textarea"
                placeholder="Enter Event Message"
                id="message"
                onChange={(e) => handleChange("message", e)}
                style={{ height: "50px" }}
              />
            </FormGroup>
            <FormGroup>
              <Label for="eventDate">Start Date</Label>
              <Input
                type="date"
                id="startDate"
                onChange={(e) => handleChange("startDate", e)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="eventDate">End Date</Label>
              <Input
                type="date"
                id="endDate"
                onChange={(e) => handleChange("endDate", e)}
              />
            </FormGroup>

            <Container className="text-center">
              <Button
                color="primary"
                size="lg"
                className="mr-2 mx-2"
                onClick={handleSubmit}
              >
                Create Event
              </Button>
              <Button
                color="warning"
                size="lg"
                className="mr-2 mx-2"
                onClick={resetEvent}
                >Reset
              </Button>
 <Link to="/admin/dashboard">
                  <Button color="info" size="lg" className="mr-2 mx-2">
                    Back
                  </Button>
                </Link>
            </Container>

    
          </CardBody>
        </Card>
      </Container>
    </Base>
  );
};

export default CreateEvent;
