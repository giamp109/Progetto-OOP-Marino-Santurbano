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

public class JSONFileCurrent {
	
	static JSONArray array=new JSONArray ();
	
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
}
