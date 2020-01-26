package com.microservice.tollparking.model;

public enum SlotType {
	
	  Standard ("standard parking slot for sedan cars"),
	  Electric_20kw ("parking slot for 20kw electric cars"),
	  Electric_50kw ("parking slot for 50kw electric cars");

	  private String detail = "";
	   
		SlotType(String string)
		{
			this.detail = string;
		}
	   
	  public String toString()
	  {
	    return detail;
	  }	
}
