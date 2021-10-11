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

            let body = message.body;
            if(body.length > 30) {
                body = getShortBody(message.body);
            }

            return (
                <span key={message.id.toString()}
                      onClick={routeChange}>
                    <p className={"subject"}>{message.subject}</p>
                    <p>{message.sender}</p>
                    <p> {body}</p>
                </span>
            )
        })

        function getShortBody(body: string){
            let shortBody = body.split(" ").slice(0,2).join(' ') + "...";
            return shortBody;
        }
    }

    return (
        <>{renderList()}</>)
}

