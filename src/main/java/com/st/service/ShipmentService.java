package com.st.service;

import java.util.List;
import java.util.Optional;

import com.st.model.Shipment;


public interface ShipmentService {

	public Integer saveShipment(Shipment s);
	public void updateShipment(Shipment s);
	public void deleteShipment(Integer Id);
	public Optional<Shipment> getOneShipment(Integer Id);
	List<Shipment> getAllShipment();
	public boolean isShipmentExist(Integer Id);
	public boolean isShipmentCodeExist(String shipmentCode);
}
