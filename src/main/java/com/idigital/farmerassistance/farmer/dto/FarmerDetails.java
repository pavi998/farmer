package com.idigital.farmerassistance.farmer.dto;




import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;



public class FarmerDetails {
	@Range(min=1,max=99,message="Not a Valid Id!!!")
	private long farmerId;
	@Pattern(regexp="^[a-zA-Z]{1}[a-zA-Z\\s)]{1,}[\\s][a-zA-Z]{1,}$",message = "Please enter full name")
	private String farmerName;
	@NotNull(message="Please enter phone number")
	private long farmerContactNumber;
	@Email(message = "Please enter email")
	private String farmerEmail;
	@NotEmpty(message="Please enter your Address")
	private String farmerAddress;
	
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
		return farmerAddress;
	}
	public void setFarmerAddress(String farmerAddress) {
		this.farmerAddress = farmerAddress;
	}

	
}