package it.univpm.ProgettoSanturbanoMarino.model;

public class Forecast {


	private int humidity;
 

	private String main;
	private String description;
	
	public Forecast(int humidity, String main, String description) {
		this.humidity=humidity;
		this.main=main;
		this.description=description;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
