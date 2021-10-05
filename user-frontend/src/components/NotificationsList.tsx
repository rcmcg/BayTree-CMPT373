import {IState as Props} from "./Notifications"
import React from "react";
import {useHistory} from "react-router-dom";

interface IProps {
    messages: Props["messages"]
}

export const ListNotifications: React.FC<IProps> = ({messages}) => {

    const history = useHistory();

    const renderList = () : JSX.Element[] => {
        return messages.map(message => {

            function routeChange() {
                let notificationId = message.id;
                history.push(`/SingleNotification/${notificationId}`);
            }

            let body = getShortBody(message.body);

            return (
                <span key={message.id.toString()}
                      onClick={routeChange}>
                    <p>{message.subject}</p>
                    <p>{message.sender}</p>
                    <p> {body}</p>
                </span>
            )
        })

        function getShortBody(body: any){
            let shortBody = body.split(" ").slice(0,2).join(' ') + "...";
            if(body.length > 30) {
                body = shortBody
            }
            return body
        }
    }

    return (
        <>{renderList()}</>)
}

