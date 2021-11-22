import axios from "axios";
import React, { useState } from "react";
import "../../css/dashboard/Notifications.css";
import { ListNotifications } from "./NotificationsList";

// export interface IState {
//   messages: {
//     id: number;
//     subject: string;
//     sender: string;
//     body: string;
//   }[];
// }

export interface IState {
  messages: {
    notificationId: number;
    username: string;
    messageBody: string;
  }[];
}

function Notifications() {
  const [messages, setMessages] = useState<IState["messages"]>([]);
  axios
    .get<typeof messages>("http://localhost:8080/notifications/get/all")
    .then((response) => {
      setMessages(response.data);
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });

  return (
    <div>
      <div className={"notifications"}>
        <h5 className={"header-component"}>Notifications</h5>

        <section className={"body"}>
          <div className={"messages"}>
            <ListNotifications messages={messages} />
          </div>
        </section>
      </div>
    </div>
  );
}

export default Notifications;
