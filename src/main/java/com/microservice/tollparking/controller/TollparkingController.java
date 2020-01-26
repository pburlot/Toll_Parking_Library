package com.microservice.tollparking.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.tollparking.dao.ParkingDAO;
import com.microservice.tollparking.model.Parking;
import com.microservice.tollparking.model.Slot;
import com.microservice.tollparking.model.SlotType;
import com.microservice.tollparking.processing.ParkCarCreationRequest;
import com.microservice.tollparking.processing.ParkingCreationRequest;


@RestController
public class TollparkingController {
	
    @Autowired
    private ParkingDAO parkingDAO;
	
	@GetMapping(value="/Parkings")
    public List<Parking> listParkings() 
	{
        return parkingDAO.findAll();
    }
	
	@GetMapping(value = "/Parkings/{id}")
	public Parking displayParking(@PathVariable int id) 
	{
		return parkingDAO.findById(id);
	}
	
    @PostMapping(value = "/AddParking")
    public void AddParking(@RequestBody ParkingCreationRequest parkingRequest) {
    	Parking parking = new Parking(parkingRequest.getId(), parkingRequest.getName(), parkingRequest.getPolicy());
    	int threshold = 0;
    	for (int i = 0; i < parkingRequest.getNbCarParkingSlotsPerType().size(); i++) 
    	{
    		String typeSlot = new String(parkingRequest.getNbCarParkingSlotsPerType().get(i).getSlotType());
    		int nbSlot = parkingRequest.getNbCarParkingSlotsPerType().get(i).getNbSlot();
        	parking.initNbSlotsPerType(SlotType.valueOf(typeSlot), nbSlot, threshold);
        	threshold = threshold + nbSlot;
        }
    	parkingDAO.save(parking);
    }
    
    @PostMapping(value = "/Parkings/{id}/ParkCar")
    public void ParkCar(@PathVariable int id, @RequestBody ParkCarCreationRequest parkCarRequest) {
    	Parking parking = parkingDAO.findById(id);
    	String type = new String(parkCarRequest.getTypeOfCar());
    	parking.parkCar(SlotType.valueOf(type));
    	//parkingDAO.save(parking);
    }
    
	@GetMapping(value = "/Parkings/{ParkingID}/LeaveParking/{SlotID}")
	public void LeaveParkingSlot(@PathVariable int ParkingID, @PathVariable int SlotID) 
	{
		Parking parking = parkingDAO.findById(ParkingID);
		Slot slot = parking.getSlotbyID(SlotID);
		slot.freeSlot();
	}
}
