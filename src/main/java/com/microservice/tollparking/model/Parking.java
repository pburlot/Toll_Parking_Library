package com.microservice.tollparking.model;

import java.util.Map;
import java.util.Set;

public class Parking {
	
	private int id;
    private String name;
    private Policy policy;
    private Map<SlotType, Set<Slot>> carParkingSlots;
    
    public Parking() {
	}
    
	public Parking(int id, String name, Policy policy, Map<SlotType, Set<Slot>> carParkingSlots) {
		this.id = id;
		this.name = name;
		this.policy = policy;
		this.carParkingSlots = carParkingSlots;
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


	public Map<SlotType, Set<Slot>> getCarParkingSlots() {
		return carParkingSlots;
	}
	
	public void setCarParkingSlots(Map<SlotType, Set<Slot>> carParkingSlots) {
		this.carParkingSlots = carParkingSlots;
	}
	
	public int getNumberOfSlotsPerType(SlotType type) {
		Set <Slot> setOfSlots;
		setOfSlots = this.carParkingSlots.get(type);
		return setOfSlots.size();
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", name=" + name + ", policy=" + policy + ", carParkingSlots=" + carParkingSlots
				+ "]";
	}
	
}
