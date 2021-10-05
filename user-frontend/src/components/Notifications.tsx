import React, {useState} from 'react';
import "../css/Notifications.css"
import { ListNotifications} from "./NotificationsList";

export interface IState {
    messages: {
        id: number
        subject: string
        sender: string
        body: string
    }[]
}

function Notifications() {

    const [messages] = useState<IState["messages"]>([
        { id: 1, subject: "Short notification", sender:"Lux", body: "Short notification"},
        { id: 2, subject: "Medium notification", sender: "Fizz",
            body:"Medium notification to test notifications function"},
        { id: 3, subject: "Long notification", sender: "Rengar",
            body:"A really long notification for our notification function to test"},

        { id: 4, subject: "Short notification", sender:"Liz", body: "Short notification"},
        { id: 5, subject: "Medium notification", sender: "Fizz",
            body:"Medium notification to test notifications function"},
        { id: 6, subject: "Long notification", sender: "Rengar",
            body:"A really long notification for our notification function to test"},
    ])

    return (
        <div>
            <div className={"notifications"}>

                <h5 className={"header-component"}>
                    Notifications
                </h5>

                <section className={"body"}>
                    <div className={"messages"}>
                        <ListNotifications messages={messages}/>
                    </div>
                </section>

            </div>
        </div>
    );
}


export default Notifications;
