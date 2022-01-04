package it.univpm.ProgettoSanturbanoMarino.model;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time extends Temperature{
	
	LocalDate date;
    LocalTime time;
	public Time(int humidity, String main, String description, double temperature, double temperaturemin,double temperaturemax, double feelslike,LocalDate date,LocalTime time) {
		super(humidity, main, description, temperature, temperaturemin, temperaturemax, feelslike);
		this.date=date;
		this.time=time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}


	
	
}
