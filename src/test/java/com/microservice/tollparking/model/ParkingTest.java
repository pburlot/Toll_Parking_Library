package com.microservice.tollparking.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ParkingTest {

	@Test
	void testinitNbSlotsPerType() {
		Parking parking = new Parking(1, new String("P1"), new Policy(3.5f, 2f));
		
		parking.initNbSlotsPerType(SlotType.Standard, 10, 0);
		parking.initNbSlotsPerType(SlotType.Electric_20kw, 5, 10);
		parking.initNbSlotsPerType(SlotType.Electric_50kw, 1, 15);
		
		Map<SlotType, Set<Slot>> carParkingSlots = new HashMap<>();;
		carParkingSlots = parking.getCarParkingSlots();
		
		assertTrue(carParkingSlots.containsKey(SlotType.Standard));
		assertTrue(carParkingSlots.containsKey(SlotType.Electric_20kw));
		assertTrue(carParkingSlots.containsKey(SlotType.Electric_50kw));
		
		Set<Slot> slotStandard = new HashSet<>();
		slotStandard = carParkingSlots.get(SlotType.Standard);
		Set<Slot> slotElectric20kw = new HashSet<>();
		slotElectric20kw = carParkingSlots.get(SlotType.Electric_20kw);
		Set<Slot> slotElectric50kw = new HashSet<>();
		slotElectric50kw = carParkingSlots.get(SlotType.Electric_50kw);
		
		assertNotNull(slotStandard);
		assertNotNull(slotElectric20kw);
		assertNotNull(slotElectric50kw);
		
		assertEquals(10, slotStandard.size());
		assertEquals(5, slotElectric20kw.size());
		assertEquals(1, slotElectric50kw.size());
	}
	
	@Test
	void testgetNumberOfSlotsAvailablePerType() {
		Parking parking = new Parking(1, new String("P1"), new Policy(3.5f, 2f));
		
		parking.initNbSlotsPerType(SlotType.Standard, 10, 0);
		parking.initNbSlotsPerType(SlotType.Electric_20kw, 5, 10);
		parking.initNbSlotsPerType(SlotType.Electric_50kw, 1, 15);
		
		assertEquals(10, parking.getNumberOfSlotsAvailablePerType(SlotType.Standard));
		assertEquals(5, parking.getNumberOfSlotsAvailablePerType(SlotType.Electric_20kw));
		assertEquals(1, parking.getNumberOfSlotsAvailablePerType(SlotType.Electric_50kw));
		
		parking.parkCar(SlotType.Standard);
		parking.parkCar(SlotType.Standard);
		parking.parkCar(SlotType.Electric_20kw);
		parking.parkCar(SlotType.Electric_50kw);
				
		assertEquals(8, parking.getNumberOfSlotsAvailablePerType(SlotType.Standard));
		assertEquals(4, parking.getNumberOfSlotsAvailablePerType(SlotType.Electric_20kw));
		assertEquals(0, parking.getNumberOfSlotsAvailablePerType(SlotType.Electric_50kw));
	}
	
	@Test
	void testparkCar() {
		Parking parking = new Parking(1, new String("P1"), new Policy(3.5f, 2f));
		
		parking.initNbSlotsPerType(SlotType.Standard, 1, 0);
		
		//1 Standard slot
		assertTrue(parking.parkCar(SlotType.Standard));
		assertFalse(parking.parkCar(SlotType.Electric_20kw));
		
		//0 Standard slot
		assertFalse(parking.parkCar(SlotType.Standard));
	}
	
	@Test
	void testgetSlotbyID() {
		Parking parking = new Parking(1, new String("P1"), new Policy(3.5f, 2f));
		
		parking.initNbSlotsPerType(SlotType.Standard, 2, 0);
		parking.initNbSlotsPerType(SlotType.Electric_50kw, 1, 2);
		
		//Slots created: 0 1 2
		
		assertNull(parking.getSlotbyID(26));
		assertNotNull(parking.getSlotbyID(0));
		assertNotNull(parking.getSlotbyID(1));
		assertNotNull(parking.getSlotbyID(2));	
	}

	@Test
	void testisSlotTypeExistingForParking() {
		Parking parking = new Parking(1, new String("P1"), new Policy(3.5f, 2f));
		
		parking.initNbSlotsPerType(SlotType.Standard, 2, 0);
		parking.initNbSlotsPerType(SlotType.Electric_50kw, 1, 2);
		
		//Slot type added : Standard Electric_20kw
		
		assertTrue(parking.isSlotTypeExistingForParking(SlotType.Standard));
		assertTrue(parking.isSlotTypeExistingForParking(SlotType.Electric_50kw));	
		assertFalse(parking.isSlotTypeExistingForParking(SlotType.Electric_20kw));
	
	}
}
