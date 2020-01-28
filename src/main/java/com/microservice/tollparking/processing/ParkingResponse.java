package com.microservice.tollparking.processing;

import java.util.List;

import com.microservice.tollparking.model.Policy;

public class ParkingResponse {
	
	private int id;
	private String name;
    private Policy policy;
    private List<SlotResponse> nbCarParkingSlotsPerType;
    
	public ParkingResponse(int id, String name, Policy policy) {
		this.id = id;
		this.name = name;
		this.policy = policy;
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

	public List<SlotResponse> getNbCarParkingSlotsPerType() {
		return nbCarParkingSlotsPerType;
	}

	public void setNbCarParkingSlotsPerType(List<SlotResponse> nbCarParkingSlotsPerType) {
		this.nbCarParkingSlotsPerType = nbCarParkingSlotsPerType;
	}
}
