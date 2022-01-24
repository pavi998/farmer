package com.idigital.farmerassistance.farmer.mapper;

import com.idigital.farmerassistance.farmer.dto.FarmerDetails;
import com.idigital.farmerassistance.farmer.entities.Farmer;

public class FarmerMapper {
	public static Farmer dtoToEntity(FarmerDetails frd)
	{
		Farmer farm = new Farmer(frd.getFarmerId(),frd.getFarmerName(),frd.getFarmerContactNumber(),frd.getFarmerEmail(),frd.getFarmerAddress());
		return farm;
	}

}
