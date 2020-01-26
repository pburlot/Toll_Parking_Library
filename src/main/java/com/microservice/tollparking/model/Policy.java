package com.microservice.tollparking.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Policy {
	
	@NotNull
	@Min(value=0)
	private final float hourlyAmount;
	
	@Min(value=0)
	private final float fixedAmount;
	
	public Policy(float hourlyAmount) {
		this.hourlyAmount = hourlyAmount;
		this.fixedAmount = 0;
	}
	
	public Policy(float hourlyAmount, float fixedAmount) {
		this.hourlyAmount = hourlyAmount;
		this.fixedAmount = fixedAmount;
	}
	
	public float getHourlyAmount() {
		return hourlyAmount;
	}

	public float getFixedAmount() {
		return fixedAmount;
	}

	public float getBill(LocalDateTime enterTime, LocalDateTime leaveTime) {
		float bill;
        Duration duration = Duration.between(enterTime, leaveTime);
        long hoursSpent = duration.toHours();
		bill = (float)hoursSpent * (float)this.hourlyAmount + (float)this.fixedAmount;
		return (float) bill;
	}
	
}
