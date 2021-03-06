package it.univpm.ProgettoSanturbanoMarino.service;

import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.ProgettoSanturbanoMarino.model.City;
import org.json.simple.parser.ParseException;
import it.univpm.ProgettoSanturbanoMarino.model.Temperature;
import it.univpm.ProgettoSanturbanoMarino.model.Time;
import it.univpm.ProgettoSanturbanoMarino.model.Forecast;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La JSONParse contiene il metodo che permette di effettuare il parsing direttamente dal JSONObject ricavato dalla chiamata dell'API 5 day / 3 hour Forecast
* creando un oggetto di tipo City. 
*/
public class JSONParse {

	/**
	*
	* Il metodo 'ParseCity()' chiama il metodo 'getForecast()' della classe 'WeatherDatabase' ottenendo un JSONObject di cui effettua il parsing
	* e crea l'oggetto di tipo City
	*
	* @param String CityName che rappresenta la città di cui si vuole ottenere le previsioni meteo
	* @return City newcity 
	*/
	public static City ParseCity(String CityName) throws ParseException, CityNotFoundException{
		
		JSONObject File = WeatherDatabase.getForecast(CityName);
		Time temporary;
		
		long prov;		
		double temp,tempmin,tempmax,feelslike;
		int humidity;
		String cityid,cityname,citycountry,mainweather,description;
		LocalDateTime datetime;
		LocalDate date;
		LocalTime time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		/* Parsing delle informazioni riguardanti la città ovvero Nome,Stato e ID */
        
		JSONObject CityInfo= (JSONObject) File.get("city");

		prov=(long)(CityInfo.get("id"));
		cityid=Long.toString(prov);
        	cityname=(String)(CityInfo.get("name"));
        	citycountry=(String)(CityInfo.get("country"));
        
		City newcity= new City(cityid,cityname,citycountry);

		/* Parsing delle informazioni riguardanti il meteo della città salvate all'interno dell'ArrayList ForecastList */

		JSONArray forecastarray = (JSONArray) File.get("list");  

		for (int i=0; i<forecastarray.size();i++) {
			
			JSONObject list =(JSONObject) forecastarray.get(i);
			JSONObject main= (JSONObject) list.get("main");
			JSONArray weatherlist= (JSONArray) list.get("weather");
			JSONObject weather=(JSONObject) weatherlist.get(0);
			
			humidity = Integer.parseInt(main.get("humidity").toString());
			
       			temp=Double.parseDouble(main.get("temp").toString());
			tempmin=Double.parseDouble(main.get("temp_min").toString());
			tempmax=Double.parseDouble(main.get("temp_max").toString());
			feelslike=Double.parseDouble(main.get("feels_like").toString());
			
		    	datetime = LocalDateTime.parse(list.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate();
			time = datetime.toLocalTime();
			mainweather=(String)weather.get("main");
			description=(String)weather.get("description");	
			
			temporary= new Time(humidity,mainweather,description,temp,tempmin,tempmax,feelslike,date,time);
            		newcity.getForecastlist().add(temporary);	
            
		}

		return newcity;
	}


	

	
}
