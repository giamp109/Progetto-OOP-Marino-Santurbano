package it.univpm.ProgettoSanturbanoMarino.exceptions;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe TimeeNotFoundException contiene il metodo che segnala l'eccezione in caso di orario non trovato.
* 
*/
public class TimeNotFoundException extends Exception {
	String errormex;

	/**
	*
	* Costruttore della classe TimeNotFoundException.
	*
	*/
	public TimeNotFoundException (String errormex) {
		super();
		this.errormex= errormex;
	}
	
	/**
	*
	* Il metodo getExceptionMessage restituisce un messaggio di errore in caso di data non trovata.
	*
	*/
	public String getExceptionMessage() {
		return errormex;
	}
}
