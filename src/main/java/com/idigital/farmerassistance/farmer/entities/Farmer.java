package com.idigital.farmerassistance.farmer.entities;







import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




import io.swagger.annotations.ApiModel;

@ApiModel("This is the farmer table connected to database")
@Entity
public class Farmer {

	
	@Id
	@GeneratedValue 
	private long farmerId;
	private String farmerName;
	private long farmerContactNumber;
	private String farmerEmail;
	
	private String FarmerAddress;
	
	

	public Farmer() {
		super();
		
	}



	public Farmer(long farmerId, String farmerName, long farmerContactNumber, String farmerEmail,
			String farmerAddress) {
		super();
		this.farmerId = farmerId;
		this.farmerName = farmerName;
		this.farmerContactNumber = farmerContactNumber;
		this.farmerEmail = farmerEmail;
		FarmerAddress = farmerAddress;
	}



	public long getFarmerId() {
		return farmerId;
	}



	public void setFarmerId(long farmerId) {
		this.farmerId = farmerId;
	}



	public String getFarmerName() {
		return farmerName;
	}



	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}



	public long getFarmerContactNumber() {
		return farmerContactNumber;
	}



	public void setFarmerContactNumber(long farmerContactNumber) {
		this.farmerContactNumber = farmerContactNumber;
	}



	public String getFarmerEmail() {
		return farmerEmail;
	}



	public void setFarmerEmail(String farmerEmail) {
		this.farmerEmail = farmerEmail;
	}



	public String getFarmerAddress() {
		return FarmerAddress;
	}



	public void setFarmerAddress(String farmerAddress) {
		FarmerAddress = farmerAddress;
	}



	@Override
	public String toString() {
		return "Farmer [farmerId=" + farmerId + ", farmerName=" + farmerName + ", farmerContactNumber="
				+ farmerContactNumber + ", farmerEmail=" + farmerEmail + ", FarmerAddress=" + FarmerAddress + "]";
	}



}
	
	

	
	
	

	


