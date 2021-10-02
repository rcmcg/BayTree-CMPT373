import {IState as Props} from "./Questionnaire"
import React from "react";

interface IProps {
    questionnaires: Props["questionnaires"]
}

export const ListBody: React.FC<IProps> = ({questionnaires}) => {

    const renderList = () : JSX.Element[] => {
        return questionnaires.map(questionnaire => {
            return (
                <span key={questionnaire.id.toString()}>
                    {questionnaire.id}
                </span>
            )
        })
    }

    return (
        <>{renderList()}</>)
}

export const ListMonth: React.FC<IProps> = ({questionnaires}) => {

    const renderList = () : JSX.Element[] => {
        return questionnaires.map(questionnaire => {
            return (
                <span key={questionnaire.id.toString()}>
                    {questionnaire.month}
                </span>
            )
        })
    }

    return (
        <>{renderList()}</>)
}
