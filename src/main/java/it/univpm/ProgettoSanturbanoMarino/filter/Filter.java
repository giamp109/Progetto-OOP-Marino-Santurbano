package it.univpm.ProgettoSanturbanoMarino.filter;

import java.util.ArrayList;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import it.univpm.ProgettoSanturbanoMarino.service.JSONParse;
import it.univpm.ProgettoSanturbanoMarino.model.Time;
import it.univpm.ProgettoSanturbanoMarino.model.City;
import java.io.FileReader;
import java.util.Iterator;


public class FilterImplements {
	
	    
	public static City timeslot(String cityname,String time) throws ParseException{

	       City city=JSONParse.ParseCity(cityname);
	       City filteredcity = new City(city.getId(),city.getCityname(),city.getCitycountry());
	       LocalTime timeparsed= LocalTime.parse(time);
		  
	       for (int i=0;i<city.getForecastlist().size();i++) {			   
			
	    	if(city.getForecastlist().get(i).getTime().equals(timeparsed)) filteredcity.getForecastlist().add(city.getForecastlist().get(i));		   
	       }
		   return filteredcity;	   
	   }
	     
	     
	public static City onedayslot(String cityname,String date) throws ParseException{
	          
	    	  City city=JSONParse.ParseCity(cityname);
		      City filteredcity = new City(city.getId(),city.getCityname(),city.getCitycountry());
		      LocalDate dateparsed= LocalDate.parse(date);
			  
		       for (int i=0;i<city.getForecastlist().size();i++) {			   
				
		    	if(city.getForecastlist().get(i).getDate().equals(dateparsed)) filteredcity.getForecastlist().add(city.getForecastlist().get(i));		   
		       }
			   return filteredcity;	
	      }
	     
	public static City fivedaysslot(String cityname) throws ParseException{
			return JSONParse.ParseCity(cityname);
	    	  
	      }
}
