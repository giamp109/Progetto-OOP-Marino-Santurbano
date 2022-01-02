package it.univpm.ProgettoSanturbanoMarino.service;

import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.ProgettoSanturbanoMarino.model.City;
import it.univpm.ProgettoSanturbanoMarino.model.Forecast;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class JSONParse {

	public static City ParseCity(String CityName) {
		
		JSONObject File = WeatherDatabase.getForecast(CityName);
		long prov;
		
		/*Parsing of informations about the city*/

		JSONObject CityInfo= (JSONObject) File.get("city");

		prov=(long)(CityInfo.get("id"));
		String CityID=Long.toString(prov);


		City NewCity= new City(CityID,(String)(CityInfo.get("name")),(String)(CityInfo.get("country")) );

		/*Parsing informations about forecast saved in the City entity's ArrayList */

		JSONArray ForecastArray = (JSONArray) File.get("list");  

		for (int i=0; i<ForecastArray.size();i++) {
			
			JSONObject List =(JSONObject) ForecastArray.get(i);
			JSONObject Main= (JSONObject) List.get("main");
			JSONArray WeatherList= (JSONArray) List.get("weather");
			JSONObject Weather=(JSONObject) WeatherList.get(0);
			
			long temporary_hu=(long)Main.get("humidity");
			int humidity = (int) temporary_hu;
			Forecast temporary=new Forecast((String)List.get("dt_txt"),(double)Main.get("temp"),(double)Main.get("temp_min"),(double)Main.get("temp_max"),(double)Main.get("feels_like"),humidity,(String)Weather.get("main"),(String)Weather.get("description"));
			NewCity.getForecastList().add(temporary); 
		}

		return NewCity;
	}
	
	
	public static ArrayList<Integer> getHumidity(JSONObject File){
		
		ArrayList<Integer> hu = new ArrayList<Integer>();

		JSONArray HuArray = (JSONArray) File.get("list"); 
		
		for (int i=0; i<HuArray.size();i++) {

			JSONObject List = (JSONObject) HuArray.get(i);
			JSONObject Main = (JSONObject) List.get("main");
			
			long temporary = (long)Main.get("humidity");
			int Humidity = (int)temporary;
		    hu.add(Humidity);
			
		}
		
		return hu; 
	}
	
}
