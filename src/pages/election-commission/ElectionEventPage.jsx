import React, { useEffect, useState } from "react";
import { getAllEvents } from "../../api/AdminApi";
import Base from "../../Base";
import { Button } from "reactstrap";

function ElectionEventPage() {
  const [events, setEvents] = useState([]);
  const fetchEvents = async () => {
    try {
      const events = await getAllEvents();
      setEvents(events);
      console.log("Fetched events:", events);
    } catch (error) {
      console.error("Error fetching events:", error);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);

  return (
    <Base>
      <div className="p-6 space-y-6">
        <div className="flex justify-between items-center">
          <h1 className="text-2xl font-bold">Election Events</h1>

          <Button>Create New Event</Button>
        </div>

        <p className="text-gray-600">
          Analyze and understand data about your voters.
        </p>
      </div>
    </Base>
  );
}

export default ElectionEventPage;
