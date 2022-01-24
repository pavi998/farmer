package com.idigital.farmerassistance.farmer.controller;






import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.idigital.farmerassistance.farmer.dto.FarmerDetails;
import com.idigital.farmerassistance.farmer.entities.Farmer;

import com.idigital.farmerassistance.farmer.respentities.SuccessInfo;
import com.idigital.farmerassistance.farmer.service.IFarmerService;







@CrossOrigin( origins = "http://localhost:3000")
@RestController 
public class FarmerController {
	@Autowired
	IFarmerService fs;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	@GetMapping(value="/farmers/id/{id}",produces="application/json")
	public Farmer getFarmerById(@PathVariable("id") long fid) {
		return fs.getFarmersbyId(fid);
	}

	@GetMapping(value="/farmers/name/{name}",produces="application/json")
	public Farmer getFarmerByName(@PathVariable("name") String fname) {
		return fs.getFarmerbyName(fname);
	}
	
	@GetMapping(value="/farmers/contactNumber/{contactNumber}",produces="application/json")
	public List<Farmer> getFarmerByContactNumber(@PathVariable("contactNumber") long fcontactNumber) {
		return fs.getFarmerbyContactNumber(fcontactNumber);
	}
	
	@GetMapping(value="/farmers/email/{email}",produces="application/json")
	public List<Farmer> getFarmerByEmail(@PathVariable("email") String femail) {
		return fs.getFarmerbyEmail(femail);
	}
	
	@PostMapping(value="/farmer",produces="application/json")
	public ResponseEntity<SuccessInfo> insertFarmer(@Valid@RequestBody FarmerDetails fsd) {
		String str = fs.insert(fsd);
		return new ResponseEntity<>(new SuccessInfo(HttpStatus.CREATED, 201, str),HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/farmers",produces="application/json")
	public List<Farmer> getFarmer() {
		return fs.getAllFarmer();
	}
	
	@DeleteMapping(value="/farmers/{id}")
	public String deleteFarmer(@PathVariable("id") Farmer cr) {
		return fs.delete(cr);
	}
	
	@PutMapping(value="/farmers/{id}")
	public String updateFarmer(@PathVariable("id") long FarmerId,@RequestBody Farmer fr) {
		return fs.update(fr);
	}
	
}
		
	


