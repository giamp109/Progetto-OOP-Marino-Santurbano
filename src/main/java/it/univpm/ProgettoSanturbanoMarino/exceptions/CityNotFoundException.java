package it.univpm.ProgettoSanturbanoMarino.exceptions;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe CityNotFoundException contiene il metodo che segnala l'eccezione in caso di città non trovata.
* 
*/
public class CityNotFoundException extends Exception {
	
	String errormex;
	
	/**
	*
	* Costruttore della classe CityNotFoundException.
	*
	*/
	public CityNotFoundException (String errormex) {
		super();
		this.errormex = errormex;
	}
	
	/**
	*
	* Il metodo getExceptionMessage restituisce un messaggio di errore in caso di città non trovata.
	*
	*/
	public String getExceptionMessage() {
		return errormex;
	}
}
