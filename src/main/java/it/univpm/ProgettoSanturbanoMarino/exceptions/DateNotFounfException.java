package it.univpm.ProgettoSanturbanoMarino.exceptions;

public class DateNotFoundException extends Exception{
	
	String errormex;
	
	public DateNotFoundException (String errormex) {
		super();
		this.errormex=errormex;
	}
	
	public String getExceptionMessage() {
		return errormex;
	}

}

