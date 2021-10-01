import React, {Component} from 'react';
import "../css/Notifications.css"

class Notifications extends Component {
    render() {
        return (
            <div>
                <div className={"notifications"}>

                    <h5 className={"header-component"}>
                        Notifications
                    </h5>

                    <div className={"body-inbox"}>
                        body
                    </div>
                </div>
            </div>
        );
    }
}

export default Notifications;
