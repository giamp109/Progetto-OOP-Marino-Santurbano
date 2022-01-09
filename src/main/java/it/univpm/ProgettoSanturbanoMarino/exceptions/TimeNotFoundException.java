package it.univpm.ProgettoSanturbanoMarino.exceptions;

public class TimeNotFoundException extends Exception {
	String errormex;
	
	public TimeNotFoundException (String errormex) {
		super();
		this.errormex= errormex;
	}
	
	public String getExceptionMessage() {
		return errormex;
	}
}
