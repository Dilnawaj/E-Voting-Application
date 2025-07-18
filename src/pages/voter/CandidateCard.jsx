import React from 'react'

const CandidateCard = ({candidate,onVote}) => {
  return (
    <div className="candidate-card">
      <img src={`/assets/${candidate.banner}`}  alt="Party Banner" className="party-banner" />
      <h3>{candidate.name} ({candidate.party}) </h3>
      <button className="vote-button" onClick={() => onVote(candidate)}>
        Vote
      </button>
    </div>
  )
}

export default CandidateCard
