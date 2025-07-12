import React from 'react';
import { useNavigate } from 'react-router-dom';

const CandidateDashboard = () => {
  const navigate = useNavigate();

  const handleLogout =()=>{

  }
  const handleProfile =()=>{
console.log("Profile clicked");
    navigate('/candidate/profile');
  }


  const handleElection=()=>{
    navigate('/candidate/election');
  }

  const handleVoted =()=>{
    navigate('/candidate/votes');
  }
  const handleNotification =()=>{
    navigate('/candidate/notification');
  }


  const cards = [
    {
      title: 'My Profile',
      description: 'View and update your candidate profile details.',
      color: '#4A90E2',
      onClick:  handleProfile
    },
    {
      title: 'My Elections',
      description: 'See elections you are contesting in.',
      color: '#50E3C2',
      onClick: handleElection
    },
    {
      title: 'Live Votes',
      description: 'Watch live voting data and analytics.',
      color: '#F5A623',
      onClick:  handleVoted
    },
    {
      title: 'Notifications',
      description: 'See updates and announcements.',
      color: '#9013FE',
      onClick: handleNotification
    }
  ];

  return (
    <div style={{
      minHeight: '100vh',
      background: 'linear-gradient(to right, #6a11cb, #2575fc)',
      color: '#fff',
      padding: '40px'
    }}>
      <h1 style={{ textAlign: 'center', marginBottom: '30px', fontSize: '36px', fontWeight: 'bold' }}>
        Candidate Dashboard
      </h1>

      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
        gap: '20px',
        maxWidth: '1000px',
        margin: '0 auto'
      }}>
        {cards.map((card, index) => (
          <div
            key={index}
            onClick={card.onClick}
            style={{
              backgroundColor: card.color,
              borderRadius: '15px',
              padding: '20px',
              cursor: 'pointer',
              boxShadow: '0 8px 20px rgba(0,0,0,0.3)',
              transition: 'transform 0.2s, box-shadow 0.2s'
            }}
            onMouseEnter={e => {
              e.currentTarget.style.transform = 'translateY(-5px)';
              e.currentTarget.style.boxShadow = '0 12px 25px rgba(0,0,0,0.4)';
            }}
            onMouseLeave={e => {
              e.currentTarget.style.transform = 'translateY(0)';
              e.currentTarget.style.boxShadow = '0 8px 20px rgba(0,0,0,0.3)';
            }}
          >
            <h3 style={{ fontSize: '22px', marginBottom: '10px' }}>{card.title}</h3>
            <p style={{ fontSize: '14px', opacity: 0.9 }}>{card.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CandidateDashboard;
