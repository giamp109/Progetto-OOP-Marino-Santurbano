package it.univpm.ProgettoSanturbanoMarino.model;
import java.util.ArrayList;

public class City {
	private String ID;
	private String CityName;
	private String CityCountry;
	
	private ArrayList<Forecast> ForecastList= new ArrayList<Forecast>();
	
	public City(String ID,String CityName, String CityCountry) {
		this.ID=ID;
		this.CityCountry=CityCountry;
		this.CityName=CityName;
	}
	
	

	public ArrayList<Forecast> getForecastList() {
		return ForecastList;
	}
	public void setForecastList(ArrayList<Forecast> forecastList) {
		ForecastList = forecastList;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getCityCountry() {
		return CityCountry;
	}
	public void setCityCountry(String cityCountry) {
		CityCountry = cityCountry;
	}



	@Override
	public String toString() {
		return "City [ID=" + ID + ", CityName=" + CityName + ", CityCountry=" + CityCountry + ", ForecastList="
				+ ForecastList + "]";
	}
	

}
