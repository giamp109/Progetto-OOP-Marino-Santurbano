package it.univpm.ProgettoSanturbanoMarino.service;
import it.univpm.ProgettoSanturbanoMarino.database.*;
import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe JSONFileCurrent contiene il metodo per creare il JSONObject con le informazion di interesse a partire dal JSONObject ricavato
* dal metodo 'getForecastCurrent()' della classe WeatherDatabaseCurrent.
*/

public class JSONFileCurrent {
	
	static JSONArray array=new JSONArray ();
	
	/**
	* Il metodo 'saveJSONCurrent()' permette di creare il JSONObject con le informazioni di interesse riguardo le previsioni meteo e
	* di trascrivere quest'ultimo sul file denominato 'citynamecurrent.txt'. Il codice relativo al JSONArray e la scrittura su file è implementato
	* all'interno di un file che permette quindi l'aggiornamento delle previsioni meteo ogni ora.
	*
	* @param String cityname utilizzato per ricavare il JSONObject dal metodo 'getForecastCurrent()' e per settare il nome del file
	* in cui il JSONObject con le informazioni utili viene trascritto
	*
	* @return path che rappresenta la destinazione dove il file viene salvato.
	*/	
	public static String saveJSONCurrent(String cityname) throws ParseException, CityNotFoundException{
		
		JSONObject currentfile=new JSONObject();
		JSONObject databasecurrent=WeatherDatabaseCurrent.getForecastCurrent(cityname);
		
		
		String path = System.getProperty("user.dir")+"\\"+cityname+"current.txt";
		
		JSONObject main=(JSONObject) databasecurrent.get("main");
		JSONArray weatherarray=(JSONArray) databasecurrent.get("weather");
		JSONObject weather=(JSONObject) weatherarray.get(0);
		JSONObject sys=(JSONObject) databasecurrent.get("sys");
		
		currentfile.put("id", databasecurrent.get("id").toString());
		currentfile.put("country", sys.get("country").toString());
		currentfile.put("name", databasecurrent.get("name").toString());
		currentfile.put("timezone", databasecurrent.get("timezone").toString());
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {	
    	
		    	try {
					array.add(JSONFileCurrent.arrayupdate(cityname));
				} catch (CityNotFoundException e) {
					e.getExceptionMessage();
				}
		    	currentfile.put("weather", array);
		
		    	try{

			
		    		PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter(path)));			
		    		outputfile.println(currentfile.toString());
		    		outputfile.println("\n");
		    		outputfile.close();
			
		    	}
		
		    	catch (Exception e) {
		    		e.printStackTrace();
		    	}	
		    System.out.println("eseguito"); 
		   
		    	}
			}, 0, 1, TimeUnit.HOURS);
	
		
return path;
	}
	
	
/**
*
* Il metodo 'arrayupdate()' permette di creare un JSONObject con le informazioni del meteo (esempio temperatura,temperatura minima...) di una determinata città.
* Questo metodo viene richiamato in 'saveJSONCurrent()' per aggiornare le previsioni meteo ogni ora.
*
* @param String cityname rappresenta la città di interesse
* @return JSONObject weatherapp contenente le previsioni meteo 
*/
	public static JSONObject arrayupdate(String cityname) throws CityNotFoundException {
	
	
		JSONObject databasecurrent=WeatherDatabaseCurrent.getForecastCurrent(cityname);	
		JSONObject main=(JSONObject) databasecurrent.get("main");	
		JSONArray weatherarray=(JSONArray) databasecurrent.get("weather");	
		JSONObject weather=(JSONObject) weatherarray.get(0);
		JSONObject weatherapp= new JSONObject();

		weatherapp.put("dt", databasecurrent.get("dt").toString());
		weatherapp.put("temp",main.get("temp").toString());
		weatherapp.put("temp_max",main.get("temp_max").toString());
		weatherapp.put("temp_min",main.get("temp_min").toString());
		weatherapp.put("feels_like",main.get("feels_like").toString());
		weatherapp.put("humidity",main.get("humidity").toString());
		weatherapp.put("main", weather.get("main").toString());
		weatherapp.put("description", weather.get("description").toString());
		
	
	return weatherapp;
	
}
}
