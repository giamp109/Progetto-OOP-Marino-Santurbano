package it.univpm.ProgettoSanturbanoMarino.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoSanturbanoMarino.model.*;
import java.time.LocalDate;
import java.time.LocalTime;
/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe JSONFileParser contiene il metodo per effettuare il parsing delle informazioni prese dal file 'cityname.txt' e creare un oggetto City.
* Le informazioni all'interno di 'cityname.txt' riguardano il 5 day / 3 hour Forecast.
*/
public class JSONFileParser {
	
	/**
	* Il metodo 'FileParse()' permette di effettuare il parsing delle informazioni prese dal file 'cityname.txt' e creare l'oggetto City
	* con tutte le informazioni riguardanti le previsioni meteo. 
	* 
	* @param String cityname utilizzato per aprire il corretto file in lettura.
	* @return City parsedcity.
	* 
	*/
	public static City FileParse(String cityname) throws FileNotFoundException, IOException, ParseException {

		String id,name,country,main,description;
		long prov;
		LocalDate date;
		LocalTime time;
		double temp,tempmin,tempmax,feelslike;
		int humidity;
		Time temporary;

		String path = System.getProperty("user.dir")+"\\"+cityname+".txt";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));

		JSONObject object = (JSONObject) obj;   
		JSONArray objectarray= (JSONArray)object.get("Weather");   

		id=(String)(object.get("id"));
		name=(String)(object.get("name"));
		country=(String)(object.get("Country"));

		City parsedcity=new City(id,name,country);

		for (int i=0; i<objectarray.size();i++) {
			JSONObject weatherinfo=(JSONObject) objectarray.get(i);

			humidity = Integer.parseInt(weatherinfo.get("humidity").toString());

			temp=Double.parseDouble(weatherinfo.get("temp").toString());
			tempmin=Double.parseDouble(weatherinfo.get("temp_min").toString());
			tempmax=Double.parseDouble(weatherinfo.get("temp_max").toString());
			feelslike=Double.parseDouble(weatherinfo.get("feels_like").toString());

			date=LocalDate.parse(weatherinfo.get("date").toString());
			time=LocalTime.parse(weatherinfo.get("time").toString());

			main=(String)weatherinfo.get("main");
			description=(String)weatherinfo.get("description");
			temporary=new Time(humidity,main,description,temp,tempmin,tempmax,feelslike,date,time);
			parsedcity.getForecastlist().add(temporary);
		}

		return parsedcity;
	}
}
