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
    public void AddParking(@RequestBody Parking parking) {
    	parkingDAO.save(parking);
    }
}
