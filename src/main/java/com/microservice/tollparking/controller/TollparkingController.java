package com.microservice.tollparking.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.tollparking.dao.ParkingDAO;
import com.microservice.tollparking.exception.ParkingIDAlreadyExistingException;
import com.microservice.tollparking.exception.ParkingNotExistingException;
import com.microservice.tollparking.exception.ParkingsEmptyException;
import com.microservice.tollparking.exception.SlotNotExistingException;
import com.microservice.tollparking.exception.SlotNotInUseException;
import com.microservice.tollparking.model.Parking;
import com.microservice.tollparking.model.Slot;
import com.microservice.tollparking.model.SlotType;
import com.microservice.tollparking.processing.BillingResponse;
import com.microservice.tollparking.processing.ParkCarCreationRequest;
import com.microservice.tollparking.processing.ParkingCreationRequest;
import com.microservice.tollparking.processing.ParkingResponse;
import com.microservice.tollparking.processing.SlotResponse;


@RestController
public class TollparkingController {
	
    @Autowired
    private ParkingDAO parkingDAO;
	
	@GetMapping(value="/Parkings")
    public List<ParkingResponse> listParkings() 
	{
		List<Parking> parkings = parkingDAO.findAll();
		
		if(parkings.isEmpty()) 
		{
			throw new ParkingsEmptyException("No parkings created yet");
		}
		
		List<ParkingResponse> parkingsResponse = new ArrayList<ParkingResponse>(parkings.size());
		
		for(int i=0; i<parkings.size(); i++)
		{
			ParkingResponse parkingResponse = new ParkingResponse(parkings.get(i).getId(), parkings.get(i).getName(), parkings.get(i).getPolicy());
			
			int sizeList = parkings.get(i).getCarParkingSlots().keySet().size();
			List<SlotResponse> ListOfNbSlotPerType = new ArrayList<SlotResponse>(sizeList);
			
			Set<SlotType> setSlotType = parkings.get(i).getCarParkingSlots().keySet();
			Iterator<SlotType> itr = setSlotType.iterator();
			while(itr.hasNext())
			{
				SlotType slotType = itr.next();
				int nbSlot = parkings.get(i).getNumberOfSlotsPerType(slotType);
				SlotResponse slotResponse = new SlotResponse(slotType.toString(), nbSlot);
				ListOfNbSlotPerType.add(slotResponse);
				parkingResponse.setNbCarParkingSlotsPerType(ListOfNbSlotPerType);
			}	
			parkingsResponse.add(parkingResponse);
		}				
		return parkingsResponse;
    }
	
	@GetMapping(value = "/Parkings/{id}")
	public Parking displayParking(@PathVariable int id) 
	{
		Parking parking = parkingDAO.findById(id);
		if(parking == null) 
		{
			throw new ParkingNotExistingException("Parking: " + id + " does not exist");
		}
		return parking;
	}
	
    @PostMapping(value = "/AddParking")
    public void AddParking(@Valid @RequestBody ParkingCreationRequest parkingRequest) {
    	Parking parking = new Parking(parkingRequest.getId(), parkingRequest.getName(), parkingRequest.getPolicy());
		int id = parking.getId();
    	for(int i=0; i< parkingDAO.getListOfParkingID().size(); i++)
    	{
    		if(id == parkingDAO.getListOfParkingID().get(i))
    		{
    			throw new ParkingIDAlreadyExistingException("Parking: " + id + " already exist");
    		}
    	}

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
	public BillingResponse LeaveParkingSlot(@PathVariable int ParkingID, @PathVariable int SlotID) 
	{
		Parking parking = parkingDAO.findById(ParkingID);
		if(parking == null) 
		{
			throw new ParkingNotExistingException("Parking: " + ParkingID + " does not exist");
		}
		Slot slot = parking.getSlotbyID(SlotID);
		if(slot == null) 
		{
			throw new SlotNotExistingException("Slot: " + SlotID + " does not exist in the parking: " + ParkingID);
		}
		if(slot.isAvailable() == true) 
		{
			throw new SlotNotInUseException("Slot: " + SlotID + " is not used currently in the parking: " + ParkingID);
		}
		LocalDateTime now = LocalDateTime.now();
		float bill = (float)parking.getPolicy().getBill(slot.getStartTimeOccupation(), now);
		BillingResponse billResponse = new BillingResponse(slot.getStartTimeOccupation(), now, bill);
		slot.freeSlot();
		return billResponse;
	}
}
