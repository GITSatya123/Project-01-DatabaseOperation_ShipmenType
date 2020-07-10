package com.st.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Shipment {

	@Id
	@GeneratedValue(generator="shipment_type")
	@SequenceGenerator(name="shipment_type",sequenceName="shipment_type_seq")
	private Integer sid;
	@Column(name="shipment_mode", length=20, nullable = false)
	private String shipmentMode;
	@Column(name="shipment_code", length=10, nullable = false)
	private String shipmentCode;
	@Column(name="enable_shipment",length=5, nullable = false)
	private String enableShipment;
	@Column(name="shipment_Grade")
	private String shipmentGrade;
	@Column(length=200, nullable = false)
	private String description;
}
