package com.idigital.farmerassistance.farmer.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idigital.farmerassistance.farmer.dto.FarmerDetails;
import com.idigital.farmerassistance.farmer.entities.Farmer;
import com.idigital.farmerassistance.farmer.exception.IdNotFoundException;
import com.idigital.farmerassistance.farmer.mapper.FarmerMapper;
import com.idigital.farmerassistance.farmer.repository.FarmerRepository;

@Service("fs")
public class FarmerService implements IFarmerService {

	private static final String EXP_MESSAGE = "Oops, Trainee Not Found !!!";
	
	@Autowired
	FarmerRepository fr;
	
	@Override
	public Farmer getFarmersbyId(long farmerId) {
		Optional<Farmer> farm = fr.findById(farmerId);
		if(farm.isPresent())
			return farm.get();
		else
			return null;
	}

	@Override
	public Farmer getFarmerbyName(String farmerName) {
		Optional<Farmer> farm = fr.findByfarmerName(farmerName);
		if(farm.isPresent())
			return farm.get();
		else
			throw new IdNotFoundException(EXP_MESSAGE);
	}
	
	@Override
	public List<Farmer> getFarmerbyContactNumber(long farmerContactNumber) 
	{
		List<Farmer> farm = fr.findByfarmerContactNumber(farmerContactNumber);
		if(farm.isEmpty())
			throw new  IdNotFoundException(EXP_MESSAGE);
		else
			return farm;	 
	}
	
	
	

	@Override
	public List<Farmer> getFarmerbyEmail(String farmerEmail) 
	{
		List<Farmer> farm = fr.findByfarmerEmail(farmerEmail);
		if(farm.isEmpty())
			throw new  IdNotFoundException(EXP_MESSAGE);
		else
			return farm;
	}
	
	@Override
	public String insert(FarmerDetails fsd) {
		Farmer farm = FarmerMapper.dtoToEntity(fsd);
		fr.save(farm);
		return farm.getFarmerName()+" is added successfully";
	}

	@Override
	public List<Farmer> getAllFarmer() {
		return (List<Farmer>) fr.findAll();
	}
	
	@Override
	public String update(Farmer fus) {
		Farmer farm = fr.save(fus);
		if(farm.toString().isEmpty())
			throw new IdNotFoundException(EXP_MESSAGE);
		else
			return fus.getFarmerName()+" is updated succesfully";
	}

	@Override
	public String delete(Farmer fus) {
		if(fus.toString().isEmpty())
			throw new IdNotFoundException(EXP_MESSAGE);
		else
		{
			fr.delete(fus);
			return fus.getFarmerName()+ " is deleted successfully";
		}	
	}
	
	@Override
	public String delete(long id) {
		return null;
	}

	@Override
	public String update(long id, Farmer fr) {
		return null;
	}

	@Override
	public String insert(Farmer fr) {
		return null;
	}

}

