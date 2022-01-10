package it.univpm.ProgettoSanturbanoMarino.exceptions;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe DateNotFoundException contiene il metodo che segnala l'eccezione in caso di data non trovata.
* 
*/
public class DateNotFoundException extends Exception{
	
	String errormex;
	
	/**
	*
	* Costruttore della classe DateNotFoundException.
	*
	*/
	public DateNotFoundException (String errormex) {
		super();
		this.errormex=errormex;
	}
	
	/**
	*
	* Il metodo getExceptionMessage restituisce un messgagio di errore in caso di data non trovata.
	*
	*/
	public String getExceptionMessage() {
		return errormex;
	}

}

