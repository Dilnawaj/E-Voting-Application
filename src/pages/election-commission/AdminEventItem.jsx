import React, { useState } from 'react'

function AdminEventItem({ event }) {

  const [isOpen, setIsOpen] = useState(false); // local status
  
    const handleOpenMessage = async () => {
      console.log("DILNAWAJ")
      console.log("Notification clicked:", event);
      setIsOpen(!isOpen);
    };
  return (
   <div
  onClick={handleOpenMessage}
  className={`notification-card read ${isOpen ? 'expanded' : ''}`}
>

      <div className="flex items-center justify-between">
        <h4 className="text-md sm:text-lg font-semibold text-gray-800">{event.name}</h4>
        <span className="text-sm text-gray-500">
        
        </span>
      </div>
    {isOpen && (
  <div className="event-details mt-2 text-gray-700 text-sm">
    <p><strong>Message:</strong> {event.message}</p>
    <p><strong>Start:</strong> {event.startDate}</p>
    <p><strong>End:</strong> {event.endDate}</p>
  </div>
)}


    </div>
  )
}

export default AdminEventItem
