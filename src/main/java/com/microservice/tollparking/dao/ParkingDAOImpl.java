package com.microservice.tollparking.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.microservice.tollparking.model.Parking;
import com.microservice.tollparking.model.Policy;
import com.microservice.tollparking.model.Slot;
import com.microservice.tollparking.model.SlotType;

@Repository
public class ParkingDAOImpl implements ParkingDAO {

	public static Set<Slot> setOfSlotsP1 = new HashSet<Slot>();
	static{
		setOfSlotsP1.add(new Slot(1, true));
	}
	
	public static Set<Slot> setOfSlotsP2 = new HashSet<Slot>();
	static{
		setOfSlotsP2.add(new Slot(1, true));
		setOfSlotsP2.add(new Slot(2, true));
	}
	public static Set<Slot> setOfSlotsP3 = new HashSet<Slot>();
	static{
		setOfSlotsP3.add(new Slot(1, true));
		setOfSlotsP3.add(new Slot(2, true));
		setOfSlotsP3.add(new Slot(3, true));
	}
	
	public static Map<SlotType, Set<Slot>> carParkingSlots1 = new HashMap<>();
	static {
		carParkingSlots1.put(SlotType.Standard, setOfSlotsP1);
		carParkingSlots1.put(SlotType.Electric_20kw, setOfSlotsP2);
		carParkingSlots1.put(SlotType.Electric_50kw, setOfSlotsP3);
	}
	
	public static Map<SlotType, Set<Slot>> carParkingSlots2 = new HashMap<>();
	static {
		carParkingSlots2.put(SlotType.Standard, setOfSlotsP3);
		carParkingSlots2.put(SlotType.Electric_20kw, setOfSlotsP1);
	}
	
	public static List<Parking> parkings = new ArrayList<>();
    static {
        parkings.add(new Parking(1, new String("P1"), new Policy(3.5f), carParkingSlots1));
        parkings.add(new Parking(2, new String("P2"), new Policy(1.5f, 3), carParkingSlots2)); 
    }	
	
	@Override
	public List<Parking> findAll() {
		return parkings;
	}
	
	@Override
	public Parking findById(int id) {
		for (Parking parking : parkings) 
		{  
            if(parking.getId() == id)
            {
                return parking;
            }
        }
		return null;
	}

	@Override
	public Parking save(Parking parking) {
		parkings.add(parking);
		return parking;
	}

}
