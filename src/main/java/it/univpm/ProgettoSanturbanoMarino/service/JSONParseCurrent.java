package it.univpm.ProgettoSanturbanoMarino.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.ProgettoSanturbanoMarino.model.*;


import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabaseCurrent;
import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;

public class JSONParseCurrent {

public static City ParseCity(String cityname) throws CityNotFoundException{

	Time temporary;
	
	long dt;		
	double temp,tempmin,tempmax,feelslike;
	int humidity,timezone;
	String id,name,country,mainweather,description;
	LocalDateTime datetime;
	LocalDate date;
	LocalTime time;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	JSONObject databasecurrent=WeatherDatabaseCurrent.getForecastCurrent(cityname);
	JSONObject main=(JSONObject) databasecurrent.get("main");
	JSONArray weatherarray=(JSONArray) databasecurrent.get("weather");
	JSONObject weather=(JSONObject) weatherarray.get(0);
	JSONObject sys=(JSONObject) databasecurrent.get("sys");
	
	id=databasecurrent.get("id").toString();
	name=(String)(databasecurrent.get("name"));
    country=(String)(sys.get("country"));
    
    City parsedcity= new City(id,name,country);
	
	humidity = Integer.parseInt(main.get("humidity").toString());

	temp=Double.parseDouble(main.get("temp").toString());
	tempmin=Double.parseDouble(main.get("temp_min").toString());
	tempmax=Double.parseDouble(main.get("temp_max").toString());
	feelslike=Double.parseDouble(main.get("feels_like").toString());
	
	mainweather=(String)(weather.get("main"));
	description=(String)(weather.get("description"));
	
	dt=Long.parseLong(databasecurrent.get("dt").toString());
	timezone=Integer.parseInt(databasecurrent.get("timezone").toString());
    
	datetime=DateCalculator.datecalculator(dt, timezone);
	date=datetime.toLocalDate();
	time=datetime.toLocalTime();
	
	temporary= new Time(humidity,mainweather,description,temp,tempmin,tempmax,feelslike,date,time);
	parsedcity.getForecastlist().add(temporary);
	
	
	
	return parsedcity;
	
	
	
}

}
