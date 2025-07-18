import React, { useContext, useState } from "react";
import { CandidateContext } from "../../context/CandidateContext";
import {
  Container,
  Card,
  CardBody,
  CardHeader,
  Button,
  Row,
  Col,
  Alert,
} from "reactstrap";
import { doVote } from "../../api/votingApi";
function VotingPage() {
  const { candidates } = useContext(CandidateContext);
  console.log("candidates", candidates);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const aadharNumber = "730955084905";

  const handleVote = async (candidateId) => {
    try {
      await doVote(aadharNumber, candidateId);
      setMessage("Vote cast successfully!");
      setError("");
    } catch (error) {
      console.error("Error while voting:", error);
      setError("Failed to cast vote. Please try again.");
      setMessage("");
    }
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <h2 className="text-center mb-4">Vote for Your Candidate</h2>

      {message && <Alert color="success">{message}</Alert>}
      {error && <Alert color="danger">{error}</Alert>}

      <Row>
        {candidates.length === 0 ? (
          <p>No candidate available</p>
        ) : (
          candidates.map((candidate) => {
            return (
              <Col key={candidate.id} md="4" className="mb-4">
                <Card>
                  <CardHeader>
                    <h5>{candidate.name}</h5>
                  </CardHeader>
                  <CardBody>
                    <p>
                      <strong>Party:</strong> {candidate.party}
                    </p>
                    <p>
                      <strong>Constituency:</strong> {candidate.constituency}
                    </p>
                    <Button
                      color="primary"
                      onClick={() => handleVote(candidate.id)}
                    >
                      Vote
                    </Button>
                  </CardBody>
                </Card>
              </Col>
            );
          })
        )}
      </Row>
    </Container>
  );
}

export default VotingPage;
