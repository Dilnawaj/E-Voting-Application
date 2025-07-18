import React, { useState } from 'react'
import { setNotificationStatus } from '../../api/candidateApi';


const NotificationItem = ({ notification, onMessageRead }) => {
  const [isOpen, setIsOpen] = useState(false);
 const [status, setStatus] = useState(notification.status); // local status

  const handleOpenMessage = async () => {
    console.log("DILNAWAJ")
    console.log("Notification clicked:", notification);
    if (!isOpen && status === false) {
      console.log("Opening message:", notification.notificationId);
      try {
        console.log("DON")
        await setNotificationStatus(notification.notificationId); // Don't forget await!
        console.log("SAY")
        onMessageRead(notification.notificationId);
        setStatus(true); // Update local status
         console.log("HAA")
      } catch (error) {
        console.error("Error opening message:", error);
      }
    }
    setIsOpen(!isOpen);
  };

  return (
    <div
  onClick={handleOpenMessage}
  className={`notification-card ${status ? 'read' : 'unread'} ${isOpen ? 'expanded' : ''}`}
>

      <div className="flex items-center justify-between">
        <h4 className="text-md sm:text-lg font-semibold text-gray-800">{notification.title}</h4>
        <span className="text-sm text-gray-500">
          {status === true? '✅ Read' : '🕒 Unread'}
        </span>
      </div>
     {isOpen && (
  <div className="mt-2 text-gray-700 text-sm transition-opacity duration-300 opacity-100">
    {notification.message}
  </div>
)}

    </div>
  )
}

export default NotificationItem;
