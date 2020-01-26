package com.microservice.tollparking.model;


import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	
	public int getNumberOfSlotsAvailablePerType(SlotType type) {
		Set <Slot> setOfSlots;
		setOfSlots = this.carParkingSlots.get(type);
		int nbSlotAvail = 0;
		Iterator<Slot> itr = setOfSlots.iterator();
		while(itr.hasNext())
		{
			Slot slot = itr.next();
			if (slot.isAvailable())
			{
				nbSlotAvail++;
			}
		}
		return nbSlotAvail;
	}
	
	public void initNbSlotsPerType(SlotType type, int nbOfSlot, int threshold) {
		Set <Slot> setOfSlots = new HashSet<>(nbOfSlot);
        for (int i = 0; i < nbOfSlot; i++) {
        	setOfSlots.add(new Slot(i+threshold));
        }
        this.carParkingSlots.put(type, setOfSlots);
	}
	
	public boolean parkCar(SlotType type) {
		Set <Slot> setOfSlots;
		setOfSlots = this.carParkingSlots.get(type);
		Iterator<Slot> itr = setOfSlots.iterator();
		while(itr.hasNext())
		{
			Slot slot = itr.next();
			if (slot.isAvailable())
			{
				slot.takeSlot();
				return true;
			}
		}
		return false;
	}
	
	public Slot getSlotbyID(int slotID) {
		for(Set<Slot> SetOfSlots : this.getCarParkingSlots().values())
		{
			Iterator<Slot> itr = SetOfSlots.iterator();
			while(itr.hasNext())
			{
				Slot slot = itr.next();
				if(slot.getSlotNumber() == slotID)
				{
					return slot;
				}
			}
		}
		return null;
	}
	
	public boolean isSlotTypeExistingForParking(SlotType type) {
		Set<SlotType> setOfSlotTypes = this.getCarParkingSlots().keySet();
		if (setOfSlotTypes.contains(type))
		{
			return true;
		}
		return false;
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
