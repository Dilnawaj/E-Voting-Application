import React, { useContext, useEffect, useState } from "react";
import { CandidateContext } from "../../context/CandidateContext";
import { getNotifications } from "../../api/candidateApi";
import NotificationItem from "./NotificationItem";
import { Container,Button } from "reactstrap";
import { Link } from "react-router-dom";
import Base from "../../Base";
function NotificationList() {
  const { candidate } = useContext(CandidateContext);
  const [notifications, setNotifications] = useState([]);

  const fetchNotifications = async () => {
    try {
      const response = await getNotifications(candidate.emailAddress);
      setNotifications(response);
    } catch (error) {
      console.error("Error fetching notifications:", error);
    }
  };

  const handleMessageRead = (id) => {
    setNotifications((prev) =>
      prev.map((n) => (n.notification_id === id ? { ...n, status: 1 } : n))
    );
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  return (
    <Base >
    <div className="notification-page-container">
      <h2>📬 Notifications</h2>
      {notifications.length > 0 ? (
        notifications.map((notification) => (
          <NotificationItem
            key={notification.notificationId}
            notification={notification}
            onMessageRead={handleMessageRead}
          />
        ))
      ) : (
        <p className="notification-title" style={{ textAlign: "center" }}>
          No notifications available
        </p>
      )}
      
       <Container className="text-center">
              <div className="d-flex justify-content-center">
              
                <Link to="/candidate/dashboard">
                  <Button color="info" size="lg" className="mr-2 mx-2">
                    Back
                  </Button>
                </Link>
              </div>
            </Container>
    </div>
    </Base>
  );
}

export default NotificationList;
