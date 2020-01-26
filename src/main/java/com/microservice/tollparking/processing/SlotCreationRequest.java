package com.microservice.tollparking.processing;

public class SlotCreationRequest {
	
	private String slotType;
	private int nbSlot;
	
	public SlotCreationRequest(String slotType, int nbSlot) {
		this.slotType = slotType;
		this.nbSlot = nbSlot;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public int getNbSlot() {
		return nbSlot;
	}

	public void setNbSlot(int nbSlot) {
		this.nbSlot = nbSlot;
	}
	
	
	
	

}
