import React, {Component} from 'react';

import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare, faUndo} from '@fortawesome/free-solid-svg-icons';
import MyToast from './MyToast';
import {axios} from 'axios';

export default class Details extends Component {
    
  constructor(props){
    super(props);
    this.state = this.initialState;
    this.state.show = false ;
    this.detailsChange = this.detailsChange.bind(this);
    this.submitDetails = this.submitDetails.bind(this);
  }

  initialState = {
    farmerId:'', farmerName:'', farmerEmail:'', farmerContactNumber:'', farmerAddress:''
  }

  resetDetails = () => {
    this.setState(() => this.initialState);
  }

  submitDetails = event => {
    event.prevent.Default();

    const detail ={
      FarmerId: this.state.farmerId,
      FarmerName: this.state.farmerName,
      FarmerEmail: this.state.farmerEmail,
      FarmerContactNumber: this.state.farmerContactNumber, 
      FarmerAddress: this.state.farmerAddress
    };

    axios.post("http://localhost:9002/farmer", detail)
         .then(response => {
           if(response.data != null){
             this.setState({"show":true});
             setTimeout(() => this.setState({"show":false}), 3000);
           }else {
            this.setState({"show":false});
           }
         });
         this.setState(this.initialState);
  }

  detailsChange = event => {
    this.setState({
      [event.target.name]:event.target.value
    });
    
  }
  
  render(){
    const {farmerId, farmerName, farmerContactNumber, farmerEmail, farmerAddress} = this.state;
        
    return (
      <div>
        <div style ={{"dislay": this.state.show ? "block": "none"}}>
          <MyToast children = {{show:this.state.show, message:"Farmer Details Saved successfully."}} />
        </div>
        <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              <FontAwesomeIcon icon={faPlusSquare}/>Add Personal Details
              </Card.Header>
            <Form onReset={this.resetDetails} onSubmit={this.submitDetails} id="farmerDetailsId">
            <Form.Group as={Col} className="mb-3" controlId="formBasicFarmerId">
    <Form.Label>Farmer Id</Form.Label>
    <Form.Control  required autoComplete="off"
    type="test" name="farmerId"
    value={farmerId} onChange={this.detailsChange}
    placeholder="Enter FarmerId" />
  </Form.Group>

  
  <Form.Group  as={Col} className="mb-3" controlId="formBasicFarmerName">
    <Form.Label>Farmer Name</Form.Label>
    <Form.Control required autoComplete="off"
    type="Name" name="farmerName"
    value={farmerName} onChange={this.detailsChange}
    placeholder="Enter Farmer Name" />
    <Form.Text className="text-muted">
      Enter your Full Name.
    </Form.Text>
  </Form.Group>
  
  <Form.Group  as={Col} className="mb-3" controlId="formBasicFarmerContactNumber">
    <Form.Label>Farmer ContactNumber</Form.Label>
    <Form.Control required autoComplete="off"
    type="contactnumber" name="farmerContactNumber"
    value={farmerContactNumber} onChange={this.detailsChange}
    placeholder="Enter Contact Number" />
    <Form.Text className="text-muted">
      We'll never share your contact number with anyone else.
    </Form.Text>
  </Form.Group>

  <Form.Group as={Col} className="mb-3" controlId="formBasicFarmerEmail">
    <Form.Label>Farmer Email</Form.Label>
    <Form.Control required autoComplete="off"
     type="test" name="farmerEmail"
     value={farmerEmail} onChange={this.detailsChange} 
     placeholder="Enter EmailId" />
    <Form.Text className="text-muted">
      We'll never share your email with anyone else.
    </Form.Text>
  </Form.Group>

  

  <Form.Group as={Col} className="mb-3" controlId="formBasicAddress">
    <Form.Label>Farmer Address</Form.Label>
    <Form.Control required autoComplete="off"
    type="Address" name="farmerAddress"
    value={farmerAddress} onChange={this.detailsChange} 
    placeholder="Address" />
  </Form.Group>

  <Card.Footer style={{"textAlign":"right"}}>
 <Button size="sm" variant="success" type="submit">
 <FontAwesomeIcon icon={faSave}/>Submit
  </Button>{' '}
  <Button size="sm" variant="info" type="reset">
 <FontAwesomeIcon icon={faUndo}/>Submit
  </Button>
  </Card.Footer>
  </Form>
  </Card>
      </div>
       
       

        );
    }
}
