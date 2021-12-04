import { IState as Props } from "./Notifications";
import React from "react";
import { useHistory } from "react-router-dom";

interface IProps {
  messages: Props["messages"];
}

export const ListNotifications: React.FC<IProps> = ({ messages }) => {
  const history = useHistory();

  const renderList = (): JSX.Element[] => {
    return messages
      .slice(0)
      .reverse()
      .map((message) => {
        function routeChange() {
          let notificationId = message.notificationId;
          history.push(`/SingleNotification/${notificationId}`);
        }

        let body = message.messageBody;

        return (
          <span key={message.notificationId.toString()} onClick={routeChange}>
            <p> {body}</p>
          </span>
        );
      });
  };

  return <>{renderList()}</>;
};
