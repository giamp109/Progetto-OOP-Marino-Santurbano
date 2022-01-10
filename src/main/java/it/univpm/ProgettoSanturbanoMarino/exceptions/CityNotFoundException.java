package it.univpm.ProgettoSanturbanoMarino.exceptions;

public class CityNotFoundException extends Exception {
	
	String errormex;
	
	public CityNotFoundException (String errormex) {
		super();
		this.errormex= errormex;
	}
	
	public String getExceptionMessage() {
		return errormex;
	}
}
