package com.microservice.tollparking.processing;

import java.time.LocalDateTime;

public class BillingResponse {
	
	private LocalDateTime enterDate;
	private LocalDateTime exitDate;
	private float bill;
	
	public BillingResponse(LocalDateTime enterDate, LocalDateTime exitDate, float bill) {
		this.enterDate = enterDate;
		this.exitDate = exitDate;
		this.bill = bill;
	}

	public LocalDateTime getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(LocalDateTime enterDate) {
		this.enterDate = enterDate;
	}

	public LocalDateTime getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDateTime exitDate) {
		this.exitDate = exitDate;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}
}
