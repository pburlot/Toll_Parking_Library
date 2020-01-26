package com.microservice.tollparking.dao;
import java.util.List;
import com.microservice.tollparking.model.Parking;

public interface ParkingDAO {
	
    public List<Parking> findAll();
    public Parking findById(int id);
    public Parking save(Parking parking);

}
