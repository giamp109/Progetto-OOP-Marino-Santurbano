package it.univpm.ProgettoSanturbanoMarino.model;
import java.util.ArrayList;

public class City {
	private String id;
	private String cityname;
	private String citycountry;
	
	private ArrayList<Time> forecastlist= new ArrayList<Time>();
	
	public City(String id,String cityname, String citycountry) {
		this.id=id;
		this.citycountry=citycountry;
		this.cityname=cityname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitycountry() {
		return citycountry;
	}

	public void setCitycountry(String citycountry) {
		this.citycountry = citycountry;
	}

	public ArrayList<Time> getForecastlist() {
		return forecastlist;
	}

	public void setForecastlist(ArrayList<Time> forecastlist) {
		this.forecastlist = forecastlist;
	}
	
	


	

}
