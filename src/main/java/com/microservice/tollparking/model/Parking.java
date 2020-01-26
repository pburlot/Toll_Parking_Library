package com.microservice.tollparking.model;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Parking {
	
	private int id;
    private String name;
    private Policy policy;
    private Map<SlotType, Set<Slot>> carParkingSlots;
    
    public Parking() {
	}
    
    public Parking(int id, String name, Policy policy) {
		this.id = id;
		this.name = name;
		this.policy = policy;
		this.carParkingSlots = new EnumMap<>(SlotType.class);
	}
    
	/*public Parking(int id, String name, Policy policy, Map<SlotType, Set<Slot>> carParkingSlots) {
		this.id = id;
		this.name = name;
		this.policy = policy;
		this.carParkingSlots = carParkingSlots;
	}*/

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
	
	public void initNbSlotsPerType(SlotType type, int nbOfSlot) {
		Set <Slot> setOfSlots = new HashSet<>(nbOfSlot);
        for (int i = 1; i <= nbOfSlot; i++) {
        	setOfSlots.add(new Slot(i));
        }
        this.carParkingSlots.put(type, setOfSlots);
	}
	
	/*
	public void initNbSlotsForEachType(Map<SlotType, Integer> mapSlotsPerType) {
        this.carParkingSlots = new Map<SlotType, Set<Slot>>
	}*/

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
}
