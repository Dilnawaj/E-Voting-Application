import React, { use, useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { VoterContext } from "../../context/VoterContext";
import { getCandidateByConstituency } from "../../api/candidateApi";
import CandidateCard from "./CandidateCard";
import Base from "../../Base";
import { castVote } from "../../api/votingApi";

function VoterDashboard() {
  const [candidates, setCandidates] = useState([]);
  const { voter } = useContext(VoterContext);
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  const fetchCandidates = async () => {
    try {
      const response = await getCandidateByConstituency(voter.constituency);
      setCandidates(response);
    } catch (error) {
      console.error("Error fetching candidates:", error);
    }
  };

  const handleCastVote = async(candidate)=>{
    try {
      await castVote(candidate.candidateId,  voter.aadharNumber );
      console.log("Vote cast successfully for:", candidate);
    } catch (error) {
      console.error("Error casting vote:", error);
    }
  }
  const handleVote = (candidate) => {
    setShowModal(true);
    console.log("Vote for candidate:", candidate);
handleCastVote(candidate);
    setTimeout(() => {
      setShowModal(false);
      navigate("/login");
    }, 3000);
  };

  useEffect(() => {
    fetchCandidates();
  }, [voter]);

  return (
    <Base>
      <div className="voter-dashboard">
        <div className="container mt-4">
          <div
            className="card shadow-lg p-3 mb-4 rounded mx-auto"
            style={{ backgroundColor: "#2fa6e1ff",
              height: "120px",             // Adjust this value as needed
    overflow: "hidden"
             }}
          >
            <div className="card-body text-center">
              <h3 className="card-title  fw-bold">🗳️ Vote for the Candidate</h3>
              <p className="card-text text-secondary">
                Choose your leader wisely and cast your vote.
              </p>
            </div>
          </div>
        </div>

        <div className="card-grid">
          {candidates.map((candidate) => (
            <CandidateCard
              key={candidate.candidateId}
              candidate={candidate}
              onVote={handleVote}
            />
          ))}
        </div>

        {showModal && (
          <div className="modal-overlay">
            <div className="vote-modal">
              <h2 style={{ color: "#2e7d32" }}>{voter.name}, thank you for your vote!</h2>
            </div>
          </div>
        )}
      </div>
    </Base>
  );
}

export default VoterDashboard;
