import React, {Component} from 'react';


import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export default class NavigationBar extends Component{
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className =  "navbar-brand">
                <img src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAah467_rGGSQ0G5vR3IrVJWCKDU3rPCuCnw&usqp=CAU" width ="50" height="50" alt="brand"/>Farmer Assistance
                </Link>
             <Nav className="me-auto">
             <Link to={"add"} className =  "navbar-link">Add Personal Details</Link>
             <Link to={"list"} className =  "navbar-link"> Farmers List </Link>
              </Nav>
              </Navbar>
        );
    }
}