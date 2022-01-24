import React, {Component} from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList,faEdit, faTrash} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

export default class FarmersList extends Component {

    constructor(props){
        super(props);
        this.state = {
            farmers : []
        };
    }

    componentDidMount(){
        this.findAllFarmerDetails();
    }

    findAllFarmerDetails(){
        axios.get("http://localhost:9002/farmers")
        .then(response => response.data)
        .then((data) =>{
            this.setState({farmers: data});
        });

    };

    deleteDetails =(farmerId) =>{
        axios.get("http://localhost:9002/farmers/" +farmerId) 
        .then(response => {
            if(response.data !=null)
            alert("Farmer Details deleted successfully.")
        
        });
    };

    render(){
        return (
        <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header><FontAwesomeIcon icon={faList} />Farmer List</Card.Header>
            <Card.Body>
                <Table bordered hover stripedd variant ="dark">
                <thead>
                    <tr>
      <th>Farmer Id</th>
      <th>Farmer Name</th>
      <th>Farmer Email</th>
      <th>Farmer ContactNumber</th>
      <th>Farmer Address</th>
    </tr>
    </thead>
    <tbody>
      {
          this.state.farmers.length === 0?
        <tr align= "center">
         <td colSpan="6">Farmer Details Available</td>
         </tr>:
         this.state.farmers.map((farmer) => (
             <tr>
                 <td>{farmer.farmerId}</td>
                 <td>{farmer.farmerName}</td>
                 <td>{farmer.farmerEmail}</td>
                 <td>{farmer.farmerContactNumber}</td>
                 <td>{farmer.farmerAddress}</td>
                 <td>
                     <ButtonGroup>
                         <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit} /></Button>
                         <Button size="sm" variant="outline-danger" onClick={this.deleteDetails.bind(this, farmer.farmerId)}><FontAwesomeIcon icon={faTrash} /></Button>
                     </ButtonGroup>
                 </td>
             </tr>

         ))
         }
    
  </tbody>

                </Table>
            </Card.Body>

        </Card>
        );
    }
}


