import React, { useEffect ,useState} from 'react'
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
import { CandidateContext } from "../../context/CandidateContext";
import { useRecoilValue } from 'recoil';
import { emailAtom } from '../../state/emailAtom';
import { getCandidateByEmail } from '../../api/candidateApi';

const CandidateProfile = () => {
const[candidate,setCandidate]= useState({
  name: "",
  party: "",
  constituency: "",
  emailAddress: "",
  state: "",
})
  const email = useRecoilValue(emailAtom)
  useEffect(()=>{
   
    const fetchCandidate = async () => {
      try {
        const candidateData = await getCandidateByEmail(email);
        setCandidate(candidateData);
      } catch (error) {
        console.error("Error fetching candidate data:", error);
      }
    };
    fetchCandidate();


  },[email])




  return (
     <Container style={{ marginTop: "20px" }}>
      <Card inverse style={{ backgroundColor: "black", marginBottom: "145px" }}>
        <CardHeader>
          <h3>{candidate.name} Profile</h3>
        </CardHeader>
        <CardBody>
          
            <FormGroup>
              <Label for="name">Name</Label>
              <Input
                type="text"
                id="name"
                placeholder="Enter Name"
                value={candidate.name}
                readOnly
              />
            </FormGroup>
            <FormGroup>
              <Label for="party">Party</Label>
              <Input
                type="text"
                id="party"
                placeholder="Enter Party"
                value={candidate.party}
               readOnly
              />
            </FormGroup>
            <FormGroup>
              <Label for="email">Email</Label>
              <Input
                type="email"
                id="emailAddress"
                placeholder="Enter Email"
                value={candidate.emailAddress}
              readOnly
              />
            </FormGroup>
            <FormGroup>
              <Label for="state">State</Label>
              <Input
                type="text"
                id="state"
                value={candidate.state}
              readOnly
              >
               </Input>
            </FormGroup>
            <FormGroup>
              <Label for="constituency">Constituency</Label>
              <Input
                type="text"
                id="constituency"
                value={candidate.constituency}
              readOnly 
              >
                
              </Input>
            </FormGroup>
           
            <Container className="text-center">
              <div className="d-flex justify-content-center">
              
                <Link to="/candidate/dashboard">
                  <Button color="info" size="lg" className="mr-2 mx-2">
                    Back
                  </Button>
                </Link>
              </div>
            </Container>
          
        </CardBody>
      </Card>
    </Container>
   
  )
}

export default CandidateProfile
