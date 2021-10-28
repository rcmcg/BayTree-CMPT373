import {useContext, useState, useEffect} from 'react';
import { MentorsContext } from "./UsersContextProvider";
import { Form, Button, Col, Row} from 'react-bootstrap';
import React from "react";

interface Props {
    mentor: any
}

export const AddForm: React.FC<Props> = ({mentor})=> {

    // const {addMentor} = useContext(MentorsContext);
    // const id = mentor.id;
    // const [password, setPassword] = useState(mentor.password);
    //
    const handleSubmit= () => {
        // e.preventDefault();
        // addMentor(id, mentors)
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group as={Row} className={"mb-3"}>
                <Form.Label column sm={"3"}>First Name</Form.Label>
                <Col sm={"7"}>
                    <Form.Control plaintext readOnly defaultValue={mentor.firstName}/>
                </Col>

                <Form.Label column sm={"3"}>Last Name</Form.Label>
                <Col sm={"7"}>
                    <Form.Control plaintext readOnly defaultValue={mentor.lastName}/>
                </Col>

                <Form.Label column sm={"3"}>Set Password</Form.Label>
                <Col sm={"7"}>
                    <Form.Control type={"text"} placeholder={"Password *"}
                                  name={"password"} value={mentor.password}
                                  // onChange={(e) => setPassword(e.target.value)}
                                  required />
                </Col>
            </Form.Group>
        </Form>
    )
}