package it.univpm.ProgettoSanturbanoMarino.filter;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;

import it.univpm.ProgettoSanturbanoMarino.service.*;
import it.univpm.ProgettoSanturbanoMarino.model.Time;
import it.univpm.ProgettoSanturbanoMarino.model.City;
import it.univpm.ProgettoSanturbanoMarino.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
* La classe Filter contiene i metodi che permettono il filtraggio delle informazioni relativamente ad un arco di tempo scelto.
* 
*/
public class Filter {
	
	/**
	*
	* Il metodo 'timeslot' permette di filtrare le informazioni relative ad un orario richiesto.
	* @param cityname rappresenta la città di cui si vogliono conoscere le previsioni meteo filtrate
	* @param time rappresenta la fascia oraria di cui si vogliono conoscere le previsioni meteo
	* @return filteredcity rappresenta l'oggetto ti tipo 'City' contenente le informazioni fltrate
	* 
	*/
	public static City timeslot(String cityname, String time) throws ParseException, FileNotFoundException, IOException, TimeNotFoundException {

		boolean find = false;

		City city = JSONFileParser.FileParse(cityname);
		City filteredcity = new City(city.getId(), city.getCityname(), city.getCitycountry());
		LocalTime timeparsed = LocalTime.parse(time);

		for (int i=0; i<city.getForecastlist().size(); i++) {			   
			if(city.getForecastlist().get(i).getTime().equals(timeparsed)) {
				filteredcity.getForecastlist().add(city.getForecastlist().get(i));	
				find = true;
			}
		}
		if(!find) throw new TimeNotFoundException("Time Not Found");
		return filteredcity;	   
	}


	/**
	*
	* Il metodo 'onedayslot' permette di filtrare le informazioni relative ad un determinato giorno.
	* @param cityname rappresenta la città di cui si vogliono conoscere le previsioni meteo filtrate
	* @param date rappresenta la data di cui si vogliono conoscere le previsioni meteo
	* @return filteredcity rappresenta l'oggetto ti tipo 'City' contenente le informazioni fltrate
	* 
	*/
	public static City onedayslot(String cityname, String date) throws ParseException, FileNotFoundException, IOException, DateNotFoundException {

		boolean find=false;

		City city = JSONFileParser.FileParse(cityname);
		City filteredcity = new City(city.getId(), city.getCityname(),city.getCitycountry());
		LocalDate dateparsed = LocalDate.parse(date);

		for (int i=0; i<city.getForecastlist().size(); i++) {			   

			if(city.getForecastlist().get(i).getDate().equals(dateparsed)) {
				filteredcity.getForecastlist().add(city.getForecastlist().get(i));		
				find = true;
			}
		}
		if(!find) throw new DateNotFoundException("Date Not Found");
		return filteredcity;	
	}

	/**
	*
	* Il metodo 'fivedayslot' permette di filtrare le informazioni ogni 3 ore relative ai 5 giorni seguenti alla chiamata.
	* @param cityname rappresenta la città di cui si vogliono conoscere le previsioni meteo filtrate
	* @return CityObject
	* 
	*/
	public static City fivedaysslot(String cityname) throws ParseException, FileNotFoundException, IOException{
		return JSONFileParser.FileParse(cityname);
	}
}
