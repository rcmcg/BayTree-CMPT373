import {useParams} from "react-router-dom";

export function SingleQuestionnaire () {

    let { month } = useParams<{ month: string}>();

    return (
        <>
            <div style={{height: "10vh"}} />

            <h1> Questionnaire Information </h1>
            <p> Month: {month}</p>
        </>
    );
}