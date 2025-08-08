import React, { useContext } from 'react'
import { AdminContext } from '../../context/AdminContext';
import { Link } from "react-router-dom";
import Base from '../../Base';
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
function AdminProfile() {

  
  const{admin} =useContext(AdminContext);
  return (

    <Base>
    
     <Container style={{ marginTop: "20px" }}>
      <Card inverse style={{ backgroundColor: "grey", marginBottom: "145px" }}>
        <CardHeader>

<h3 style={{ color: "white" ,textAlign: "center" }}>Profile of ({admin.name})</h3>

        </CardHeader>

        <CardBody>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input
            type ="text"
            id="name"
            value={admin.name}
            readOnly
            />
            
          </FormGroup>
            

<FormGroup>

            <Label for="email">Email</Label>
            <Input
            type ="email"
            id="emailAddress"
            value ={admin.emailAddress} 
            readOnly

            />
</FormGroup>
<FormGroup>
<Label for="role">Role</Label>
<Input
            type ="text"
            id="role"
            value="Admin"
            readOnly
            />

</FormGroup>

<Container className="text-center">
            <div className="d-flex justify-content-center">
<Link to ="/admin/dashboard">
<Button color="info" size="lg" className="mr-2 mx-2">Back</Button>
</Link>
            </div>
            </Container>


          </CardBody>
        </Card>
        </Container>
    
    
    </Base>
  )
}

export default AdminProfile
