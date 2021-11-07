import React, { useEffect, useState } from "react";
import {Modal, Button} from 'react-bootstrap'
import { AddForm } from "./AddForm";

interface Props {
    mentor: any
}

export const SingleUser: React.FC<Props> = ({mentor})=> {

    const [show, setShow] = useState(false);
    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    useEffect(() => {
        handleClose()
    }, [mentor])

    return (
        <>
            <td>{mentor.firstName}</td>
            <td>{mentor.lastName}</td>
            <td>{mentor.role}</td>
            <td>{mentor.phone}</td>
            <td>{mentor.email}</td>
            <td>
                <button onClick={handleShow}>
                    Add
                </button>
            </td>

            <Modal style={{opacity:1}} animation={false} show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title> Add User</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddForm mentor={mentor}/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}