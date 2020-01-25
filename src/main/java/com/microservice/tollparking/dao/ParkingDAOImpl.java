package com.microservice.tollparking.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservice.tollparking.model.Parking;

@Repository
public class ParkingDAOImpl implements ParkingDAO {

	public static List<Parking> parkings = new ArrayList<>();
    static {
        parkings.add(new Parking(1, new String("P1")));
        parkings.add(new Parking(2, new String("P2"))); 
        parkings.add(new Parking(3, new String("P3")));
    }	
	
	@Override
	public List<Parking> findAll() {
		return parkings;
	}
	
	@Override
	public Parking ParkingfindById(int id) {
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
	public Parking Parkingsave(Parking parking) {
		parkings.add(parking);
		return parking;
	}

}
