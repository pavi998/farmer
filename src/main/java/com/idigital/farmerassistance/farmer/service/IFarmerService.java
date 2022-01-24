package com.idigital.farmerassistance.farmer.service;

import java.util.List;

import com.idigital.farmerassistance.farmer.dto.FarmerDetails;
import com.idigital.farmerassistance.farmer.entities.Farmer;

public interface IFarmerService {
	
	List<Farmer> getAllFarmer();
	Farmer getFarmerbyName(String name);
	Farmer getFarmersbyId(long id);
	String update(long id,Farmer cr);
	String  delete(long id);
	String delete(Farmer cr);
	String update(Farmer cr);
	String insert(FarmerDetails csd);
	String insert(Farmer cr);
	List<Farmer> getFarmerbyEmail(String email);
	List<Farmer> getFarmerbyContactNumber(long contactNumber);
	

}
