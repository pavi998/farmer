package com.idigital.farmerassistance.farmer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.idigital.farmerassistance.farmer.entities.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {


	Optional<Farmer> findByfarmerName(String farmerName);

	List<Farmer> findByfarmerContactNumber(long farmerContactNumber);

	List<Farmer> findByfarmerEmail(String farmerEmail);


	


	

}
