package com.st.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.model.Shipment;
import com.st.repo.ShipmentRepo;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Autowired
	private ShipmentRepo repo;
	

	@Override
	public Integer saveShipment(Shipment s) {
		Integer id= repo.save(s).getSid();
		return id;
	}

	@Override
	public void updateShipment(Shipment s) {
		repo.save(s);

	}

	@Override
	public void deleteShipment(Integer Id) {
		repo.deleteById(Id);

	}

	
	@Override
	public List<Shipment> getAllShipment() {
		List<Shipment> list=repo.findAll();
		return list;
	}

	@Override
	public boolean isShipmentExist(Integer Id) {
		boolean exists=repo.existsById(Id);
		return exists;
	}

	@Override
	
	public Optional<Shipment> getOneShipment(Integer Id) {
		Optional<Shipment> opt=repo.findById(Id);
		if(opt.isPresent()) {
			opt.get();
		}
		return opt;
	}

	@Override
	public boolean isShipmentCodeExist(String shipmentCode) {
		int count=repo.getShipmentCodeCount(shipmentCode);
		boolean flag= (count>0 ? true: false);
		return flag;
	}
	

}
