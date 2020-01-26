package com.microservice.tollparking.model;

import java.time.LocalDateTime;

public class Slot {
	
	private final int slotNumber;
	private boolean available;
	private LocalDateTime startTimeOccupation;
	
	public Slot(int slotNumber, boolean available) {
		this.slotNumber = slotNumber;
		this.available = available;
	}
	
	public Slot(int slotNumber) {
		this.slotNumber = slotNumber;
		this.available = true;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public LocalDateTime getStartTimeOccupation() {
		return startTimeOccupation;
	}

	public void setStartTimeOccupation(LocalDateTime startTimeOccupation) {
		this.startTimeOccupation = startTimeOccupation;
	}
	
	public void takeSlot() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		this.startTimeOccupation = currentDateTime;
		this.available = false;
	}
	
}
