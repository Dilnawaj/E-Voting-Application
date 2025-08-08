import React, { useContext, useEffect, useState } from 'react'
import { AdminContext } from '../../context/AdminContext';
import Base from '../../Base';
import AdminNotificationItem from './AdminEventItem';
import { Button, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import { getAllEventsByEmail } from '../../api/AdminApi';

function AdminEventList() {
  
   const{admin} =useContext(AdminContext);
  const [events, setEvents] = useState([]);

  const fetchEvents = async () => {
    try {
      
      const response = await getAllEventsByEmail(admin.emailAddress);
      setEvents(response);
    } catch (error) {
      console.error("Error fetching events:", error);
    }
  };

 

  useEffect(() => {
    fetchEvents();
  }, []);

  return (
    
    <Base >
    <div className="notification-page-container">
      <h2>📬 Events</h2>
      {events.length > 0 ? (
        events.map((event) => (
          
          <AdminNotificationItem
            key={event.id}
            event={event}
            
          />
        ))
      ) : (
        <p className="notification-title" style={{ textAlign: "center" }}>
          No event available
        </p>
      )}
      
       <Container className="text-center">
              <div className="d-flex justify-content-center">
              
                <Link to="/admin/dashboard">
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

export default AdminEventList
