package com.microservice.tollparking.processing;


public class ParkCarCreationRequest {
	
	private String typeOfCar;
	
	public ParkCarCreationRequest() {
	}

	public ParkCarCreationRequest(String typeOfCar) {
		this.typeOfCar = typeOfCar;
	}

	public String getTypeOfCar() {
		return typeOfCar;
	}

	public void setTypeOfCar(String typeOfCar) {
		this.typeOfCar = typeOfCar;
	}

}
