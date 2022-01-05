package it.univpm.ProgettoSanturbanoMarino.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import it.univpm.ProgettoSanturbanoMarino.model.*;

public class JSONFile {
	
	public static String saveJSON(String cityname) throws ParseException {
		
		City currentcity=JSONParse.ParseCity(cityname);
		
		JSONObject object = new JSONObject();
        
		object.put("id",currentcity.getId());
		object.put("name",currentcity.getCityname());
		object.put("Country",currentcity.getCitycountry());
	
		JSONArray temparray= new JSONArray();

		String path = System.getProperty("user.dir")+"\\"+cityname+".txt";
		
		
		for (int i=0;i<currentcity.getForecastlist().size();i++) {
          JSONObject tempweather=new JSONObject();
          tempweather.put("temp",currentcity.getForecastlist().get(i).getTemperature());
          tempweather.put("temp_max",currentcity.getForecastlist().get(i).getTemperatureMax());
          tempweather.put("temp_min",currentcity.getForecastlist().get(i).getTemperatureMin());
          tempweather.put("feels_like",currentcity.getForecastlist().get(i).getFeelsLike());
          tempweather.put("humidity",currentcity.getForecastlist().get(i).getHumidity());
          tempweather.put("date",currentcity.getForecastlist().get(i).getDate());
          tempweather.put("time",currentcity.getForecastlist().get(i).getTime());
          tempweather.put("main",currentcity.getForecastlist().get(i).getMain());
          tempweather.put("description",currentcity.getForecastlist().get(i).getDescription());
          temparray.put(tempweather);
		}
		object.put("Weather",temparray);
		
		try{
			
			PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter(path)));
						
			outputfile.println(object.toString());
			outputfile.close();
			
		}
		
		catch (Exception e) {
			System.err.println("Error: " + e);
		}
		return path;
		
	}

}
