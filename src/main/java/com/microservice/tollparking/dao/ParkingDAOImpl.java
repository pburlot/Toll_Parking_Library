package com.microservice.tollparking.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservice.tollparking.model.Parking;


@Repository
public class ParkingDAOImpl implements ParkingDAO {

	public static List<Parking> parkings = new ArrayList<>();
	
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
	
	@Override
	public List<Integer> getListOfParkingID(){
		List<Integer> listOfParkingID = new ArrayList<Integer>(parkings.size());
		for(int i=0; i<parkings.size(); i++)
		{
			listOfParkingID.add(parkings.get(i).getId());
		}
		return listOfParkingID;
	}
}
