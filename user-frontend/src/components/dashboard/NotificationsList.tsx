import { IState as Props } from "./Notifications";
import React from "react";
import { useHistory } from "react-router-dom";

interface IProps {
  messages: Props["messages"];
}

export const ListNotifications: React.FC<IProps> = ({ messages }) => {
  const history = useHistory();

  const renderList = (): JSX.Element[] => {
    return messages.map((message) => {
      function routeChange() {
        let notificationId = message.notificationId;
        history.push(`/SingleNotification/${notificationId}`);
      }

      let body = message.messageBody;
      //   if (body.length > 30) {
      //     body = getShortBody(message.messageBody);
      //   }

      return (
        <span key={message.notificationId.toString()} onClick={routeChange}>
          <p> {body}</p>
        </span>
      );
    });

    function getShortBody(body: string) {
      return body.split(" ").slice(0, 2).join(" ") + "...";
    }
  };

  return <>{renderList()}</>;
};
