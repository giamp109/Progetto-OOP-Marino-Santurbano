package it.univpm.ProgettoSanturbanoMarino.model;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe Temperature definisce l'oggetto 'Temperature' e le relative variabili .
* 
*/
public class Temperature extends Forecast {

	private double temperature;
	private double temperaturemin;
	private double temperaturemax;
	private double feelslike;
	
	/**
	*
	* Costruttore della classe Temperature.
	*
	*/
	public Temperature(int humidity, String main, String description, double temperature, double temperaturemin, double temperaturemax, double feelslike) {
		super(humidity, main, description);
		this.temperature = temperature;
		this.temperaturemin = temperaturemin;
		this.temperaturemax = temperaturemax;
		this.feelslike = feelslike;
	
	}

	/**
	*
	* Il metodo 'getTemperature()' restituisce il valore della temperatura
	* @return temperature
	*
	*/
	public double getTemperature() {
		return temperature;
	}

	/**
	*
	* Il metodo 'setTemperature()' setta il valore della temperature
	* @param temperature
	* 
	*/
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	*
	* Il metodo 'getTemperatureMin()' restituisce il valore della temperatura minima
	* @return temperaturemin
	*
	*/
	public double getTemperatureMin() {
		return temperaturemin;
	}

	/**
	*
	* Il metodo 'setTemperatureMin()' setta il valore della temperatura minima
	* @param temperaturemin
	*
	*/
	public void setTemperatureMin(double temperatureMin) {
		this.temperaturemin = temperatureMin;
	}

	/**
	*
	* Il metodo 'getTemperatureMax()' restituisce il valore della temperatura massima
	* @return temperaturemax
	*
	*/
	public double getTemperatureMax() {
		return temperaturemax;
	}

	/**
	*
	* Il metodo 'setTemperatureMax()' setta il valore della temperatura massima
	* @param temperaturemax
	*
	*/
	public void setTemperatureMax(double temperatureMax) {
		this.temperaturemax = temperatureMax;
	}

	/**
	*
	* Il metodo 'getFeelsLike()' restituisce il valore della temperatura percepita
	* @return feelslike
	*
	*/
	public double getFeelsLike() {
		return feelslike;
	}

	/**
	*
	* Il metodo 'setFeelsLike()' setta il valore della temperatura percepita
	* @param feelslike
	*
	*/
	public void setFeelsLike(double feelsLike) {
		this.feelslike = feelsLike;
	}
	
}
