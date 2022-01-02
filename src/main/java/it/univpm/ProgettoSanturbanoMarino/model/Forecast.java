package it.univpm.ProgettoSanturbanoMarino.model;

public class Forecast {
	private String date;
	
	private double Temperature;
	private double TemperatureMin;
	private double TemperatureMax;
	private double FeelsLike;
	private int Umidity;
 

	private String Main;
	private String Description;
	
	public Forecast(String date, double Temperature, double TemperatureMin, double TemperatureMax, double FeelsLike, int Umidity, String Main, String Description) {
		this.date=date;
		this.Temperature=Temperature;
		this.TemperatureMin=TemperatureMin;
		this.TemperatureMax=TemperatureMax;
		this.FeelsLike=FeelsLike;
		this.Umidity=Umidity;
		this.Main=Main;
		this.Description=Description;
	}
	
	public int getUmidity() {
		return Umidity;
	}
	public void setUmidity(int umidity) {
		Umidity = umidity;
	}	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public double getTemperatureMin() {
		return TemperatureMin;
	}
	public void setTemperatureMin(double temperatureMin) {
		TemperatureMin = temperatureMin;
	}
	public double getTemperatureMax() {
		return TemperatureMax;
	}
	public void setTemperatureMax(double temperatureMax) {
		TemperatureMax = temperatureMax;
	}
	public double getFeelsLike() {
		return FeelsLike;
	}
	public void setFeelsLike(double feelsLike) {
		FeelsLike = feelsLike;
	}
	public String getMain() {
		return Main;
	}
	public void setMain(String main) {
		Main = main;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
