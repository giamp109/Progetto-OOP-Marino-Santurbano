package it.univpm.ProgettoSanturbanoMarino.model;

public class Temperature extends Forecast {


	private double temperature;
	private double temperaturemin;
	private double temperaturemax;
	private double feelslike;
	
	public Temperature(int humidity, String main, String description,double temperature,double temperaturemin, double temperaturemax, double feelslike) {
		super(humidity, main, description);
		this.temperature=temperature;
		this.temperaturemin=temperaturemin;
		this.temperaturemax=temperaturemax;
		this.feelslike=feelslike;
	
	}


	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getTemperatureMin() {
		return temperaturemin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperaturemin = temperatureMin;
	}

	public double getTemperatureMax() {
		return temperaturemax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperaturemax = temperatureMax;
	}

	public double getFeelsLike() {
		return feelslike;
	}

	public void setFeelsLike(double feelsLike) {
		this.feelslike = feelsLike;
	}
	

	
    

}
