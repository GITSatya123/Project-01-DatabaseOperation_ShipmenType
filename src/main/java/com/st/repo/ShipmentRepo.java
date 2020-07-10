package com.st.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.st.model.Shipment;

public interface ShipmentRepo extends JpaRepository<Shipment, Integer>{

	@Query("SELECT COUNT(ST.shipmentCode) FROM Shipment ST WHERE ST.shipmentCode=:code")
	public Integer getShipmentCodeCount(String code); 
}
  
