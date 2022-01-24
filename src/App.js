import React from 'react';
import './App.css';




import{ Container, Col, Row} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';


import NavigationBar from './Component/NavigationBar';
import Details from './Component/Details';
import FarmersList from './Component/FarmersList';
import Footer from './Component/Footer';



function App() {
  const marginTop = {
    marginTop:"20px"

  }
  return (
    <Router>
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
          <div class="container-fluid bg-light text-light p-4">
             <div class="container bg-dark p-4">
             <h1 class="display-5">FARMER ASSISTANCE </h1>
             <p>TO THOSE WHO WORK IN ACRES, NOT IN HOURS</p>
              </div>
              </div>
            <Switch>
              <Route path="/add" exact component={Details}/>
              <Route path="/list" exact component={FarmersList}/>
            </Switch>
        </Col>
        </Row>
      </Container>
      <Footer/>
      </Router> 

     
    
  );
}

export default App;
