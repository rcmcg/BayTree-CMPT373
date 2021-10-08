import {useParams} from "react-router-dom";

export function SingleNotification () {

    let {id} = useParams<{ id: string }>();

    return (
        <>
            <div style={{height: "10vh"}} />

            <h1> Single Notification Information </h1>
            <p> id: {id}</p>

        </>
    );
}