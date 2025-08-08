import React from "react";
import Base from "../../Base";
import { useNavigate } from "react-router-dom";

const AdminDashboard = () => {
  const navigate = useNavigate();

  const handleProfile = () => {
    console.log("Profile clicked");
    navigate("/admin/profile");
  };

  const electionEvent = () => {
    navigate("/election/events");
  };

  const createEvent = () => {
    navigate("/create/event");
  };
  const handleNotification = () => {
    navigate("/candidate/notification");
  };

  const cards = [
    {
      title: "My Profile",
      description: "View and update your admin profile details.",
      color: "#4A90E2",
      onClick: handleProfile,
    },
    {
      title: "Election Events",
      description: "Analyze and understand data about your voters.",
      color: "#FF6F61",
      onClick: electionEvent,
    },
    {
      title: "Create Event",
      description:" Create a new election event.",
      color: "#F5A623",
      onClick: createEvent,
    },
    {
      title: "Manage Voters",
      description: "See updates and announcements.",
      color: "#9013FE",
      onClick: handleNotification,
    },
    {
      title: "Manage Candidates",
      description: "Monitor live voting statistics.",
      color: "#2ECC71",
     // onClick: ,
    },
    {
      title: "Notifications",
      description: "Manage notifications and alerts.",
      color: "#7D3C98",
      onClick: handleNotification,
    },
    {
      title: "Results",
      description: "View election results and statistics.",
      color: "#E67E22",
      onClick: () => navigate("/admin/results"),
    },

    {
      title: "Create National Party",
      description: "Create a new national political party.",
      color: "#C0392B",
      onClick: () => {
        // Add logout functionality here
        console.log("Logging out...");
        navigate("/login");
      },
    },
  ];

  return (
    <Base>
      <h1
        style={{
          textAlign: "center",
          marginBottom: "30px",
          fontSize: "36px",
          fontWeight: "bold",
          textDecoration: "underline",
        }}
      >
        Admin Dashboard
      </h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
          gap: "20px",
          maxWidth: "1000px",
          margin: "0 auto",
        }}
      >
        {cards.map((card, index) => (
          <div
            key={index}
            onClick={card.onClick}
            style={{
              backgroundColor: card.color,
              borderRadius: "15px",
              padding: "20px",
              cursor: "pointer",
              boxShadow: "0 8px 20px rgba(0,0,0,0.3)",
              transition: "transform 0.2s, box-shadow 0.2s",
            }}
            onMouseEnter={(e) => {
              e.currentTarget.style.transform = "translateY(-5px)";
              e.currentTarget.style.boxShadow = "0 12px 25px rgba(0,0,0,0.4)";
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.transform = "translateY(0)";
              e.currentTarget.style.boxShadow = "0 8px 20px rgba(0,0,0,0.3)";
            }}
          >
            <h3 style={{ fontSize: "22px", marginBottom: "10px" }}>
              {card.title}
            </h3>
            <p style={{ fontSize: "14px", opacity: 0.9 }}>{card.description}</p>
          </div>
        ))}
      </div>
    </Base>
  );
};

export default AdminDashboard;
