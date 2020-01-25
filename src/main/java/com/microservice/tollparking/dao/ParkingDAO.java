package com.microservice.tollparking.dao;
import java.util.List;
import com.microservice.tollparking.model.Parking;

public interface ParkingDAO {
	
    public List<Parking>findAll();
    public Parking ParkingfindById(int id);
    public Parking Parkingsave(Parking parking);

}
