package it.univpm.ProgettoSanturbanoMarino.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe Time definisce l'oggetto 'Time' e le relative variabili .
* 
*/
public class Time extends Temperature{
	
	LocalDate date;
	LocalTime time;
	
	/**
	*
	* Costruttore della classe Time.
	*
	*/
	public Time(int humidity, String main, String description, double temperature, double temperaturemin, double temperaturemax, double feelslike, LocalDate date, LocalTime time) {
		super(humidity, main, description, temperature, temperaturemin, temperaturemax, feelslike);
		this.date = date;
		this.time = time;
	}
	
	/**
	*
	* Il metodo 'getDate()' restituisce il valore della data
	* @return date
	*
	*/
	public LocalDate getDate() {
		return date;
	}
	
	/**
	*
	* Il metodo 'setDate()' setta il valore della data
	* @param data
	* 
	*/
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	*
	* Il metodo 'getTime()' restituisce il valore dell'ora
	* @return time
	*
	*/
	public LocalTime getTime() {
		return time;
	}
	
	/**
	*
	* Il metodo 'setTime()' setta il valore dell'ora
	* @param time
	* 
	*/
	public void setTime(LocalTime time) {
		this.time = time;
	}
}
