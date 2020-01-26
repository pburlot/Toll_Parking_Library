package com.microservice.tollparking.processing;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.microservice.tollparking.model.Policy;

public class ParkingCreationRequest {
	
	private int id;
	
	@Length(min=1, max=20)
	private String name;
	
	@NotNull
    private Policy policy;
	
	@NotNull
    private List<SlotCreationRequest> nbCarParkingSlotsPerType;
    
	public ParkingCreationRequest(int id, String name, Policy policy,
			List<SlotCreationRequest> nbCarParkingSlotsPerType) {
		this.id = id;
		this.name = name;
		this.policy = policy;
		this.nbCarParkingSlotsPerType = nbCarParkingSlotsPerType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public List<SlotCreationRequest> getNbCarParkingSlotsPerType() {
		return nbCarParkingSlotsPerType;
	}

	public void setNbCarParkingSlotsPerType(List<SlotCreationRequest> nbCarParkingSlotsPerType) {
		this.nbCarParkingSlotsPerType = nbCarParkingSlotsPerType;
	}  
	
}
