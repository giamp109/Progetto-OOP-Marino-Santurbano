package it.univpm.ProgettoSanturbanoMarino.model;
import java.util.ArrayList;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe City descrive le proprietà di una città e contine un ArrayList contenente le previsioni meteo relative ad una specifica città.
*
*/

public class City {
	private String id;
	private String cityname;
	private String citycountry;
	
	private ArrayList<Time> forecastlist= new ArrayList<Time>();
      
       /** Costruttore 
       * @param id               ID della città
       * @param cityname         Nome della città
       * @param citycountry      Stato della città
       */
	
	public City(String id,String cityname, String citycountry) {
		this.id=id;
		this.citycountry=citycountry;
		this.cityname=cityname;
	}
       
       /** 
       * Il metodo 'getId()' restituisce l'id della città.
       * @return id
       */
	public String getId() {
		return id;
	}

       /** 
       * Il metodo 'setId()' permette di settare l'id della città.
       * @return id
       */	
	public void setId(String id) {
		this.id = id;
	}
      
       /** 
       * Il metodo 'getCityname()' restituisce il nome della città.
       * @return cityname
       */
	public String getCityname() {
		return cityname;
	}

       /** 
       * Il metodo 'setCityname()' permette di settare il nome della città.
       * @return cityname
       */	
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
       
       /** 
       * Il metodo 'getCitycountry()' restituisce lo Stato in cui si trova la città.
       * @return citycountry
       */
	public String getCitycountry() {
		return citycountry;
	}
       
       /** 
       * Il metodo 'setCityCountry()' permette di settare lo Stato in cui si trova la città.
       * @return forecastlist
       */
	public void setCitycountry(String citycountry) {
		this.citycountry = citycountry;
	}
      
       /** 
       * Il metodo 'getForecastlist()' restituisce l'ArrayList contenente il meteo relativo alla città.
       * @return forecastlist
       */
	public ArrayList<Time> getForecastlist() {
		return forecastlist;
	}

       /** 
       * Il metodo 'setForecastlist()' permette di settare l'ArrayList contenente il meteo relativo alla città.
       * @return forecastlist
       */	
	public void setForecastlist(ArrayList<Time> forecastlist) {
		this.forecastlist = forecastlist;
	}
	
	


	

}
