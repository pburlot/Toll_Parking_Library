package com.microservice.tollparking.processing;

public class SlotResponse {
	
	private String slotType;
	private int nbSlotAvailable;
	
	public SlotResponse(String slotType, int nbSlotAvailable) {
		this.slotType = slotType;
		this.nbSlotAvailable = nbSlotAvailable;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public int getNbSlotAvailable() {
		return nbSlotAvailable;
	}

	public void setNbSlotAvailable(int nbSlotAvailable) {
		this.nbSlotAvailable = nbSlotAvailable;
	}

	
	
}
