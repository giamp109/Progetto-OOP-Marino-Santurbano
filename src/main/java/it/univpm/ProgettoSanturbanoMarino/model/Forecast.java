package it.univpm.ProgettoSanturbanoMarino.model;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe Forecast definisce l'oggetto 'Forecast' e le relative variabili .
* 
*/
public class Forecast {

	private int humidity;
	private String main;
	private String description;
	
	/**
	*
	* Costruttore della classe Forecast.
	*
	*/
	public Forecast(int humidity, String main, String description) {
		this.humidity=humidity;
		this.main=main;
		this.description=description;
	}

	/**
	*
	* Il metodo 'getHumidity()' restituisce il valore di umidità
	* @return humidity
	*
	*/
	public int getHumidity() {
		return humidity;
	}

	/**
	*
	* Il metodo 'setHumidity()' setta il valore di umidità
	* @param humidity
	*
	*/
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	*
	* Il metodo 'getMain()' restituisce una stringa contenente le informazioni del main delle previsioni meteo
	* @return main
	*
	*/
	public String getMain() {
		return main;
	}

	/**
	*
	* Il metodo 'setMain()' setta il contenuto della stringa 'main'
	* @param main
	*
	*/
	public void setMain(String main) {
		this.main = main;
	}

	/**
	*
	* Il metodo 'getDescription()' restituisce una stringa contenente le informazioni di 'description' delle previsioni meteo
	* @return description
	*
	*/
	public String getDescription() {
		return description;
	}
	
	/**
	*
	* Il metodo 'setDescription()' setta il contenuto della stringa 'description'
	* @param description
	*
	*/
	public void setDescription(String description) {
		this.description = description;
	}	
}
