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
	
    @PostMapping(value = "/Parkings")
    public void AddParking(@RequestBody ParkingCreationRequest parkingRequest) {
    	Parking parking = new Parking(parkingRequest.getId(), parkingRequest.getName(), parkingRequest.getPolicy());
    	for (int i = 0; i < parkingRequest.getNbCarParkingSlotsPerType().size(); i++) 
    	{
    		String typeSlot = parkingRequest.getNbCarParkingSlotsPerType().get(i).getSlotType();
    		int nbSlot = parkingRequest.getNbCarParkingSlotsPerType().get(i).getNbSlot();
        	parking.initNbSlotsPerType(SlotType.valueOf(typeSlot), nbSlot);
        }
    	parkingDAO.save(parking);
    }
}
