package it.univpm.ProgettoSanturbanoMarino.service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.model.*;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe JSONFile contiene il metodo per creare il JSONObject a partire dall'oggetto City.
*
*/

public class JSONFile {
	
	/**
	*
	* Il metodo saveJSON permette di creare il JSONObject a partire dall'oggeto City ricavato dal metodo 'ParseCity()' della classe 'JSONParse'
	* e di creare il file (nel caso non esista) "cityname.txt" e scriverci sopra il JSONObject creato.
	*
	* @param cityname utilizzata per ricavare l'ogetto city e per denominare il file (txt) dove verrà trascritto il JSONObject.
	* @return path che indica la destinazione in cui il file viene salvato.
	*/
	public static String saveJSON(String cityname) throws ParseException, CityNotFoundException, FileNotFoundException {
		
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
			outputfile.println("\n");
			outputfile.close();
			
		}
		
		catch (Exception e) {
			System.err.println("Error: " + e);
		}
		return path;
		
	}

}
