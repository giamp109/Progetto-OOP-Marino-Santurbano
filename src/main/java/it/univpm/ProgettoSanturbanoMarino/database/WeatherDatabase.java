package it.univpm.ProgettoSanturbanoMarino.database;
import  it.univpm.ProgettoSanturbanoMarino.model.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import  it.univpm.ProgettoSanturbanoMarino.exceptions.*;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe WeatherDatabase continene la connessione con l'API Forecast 5days/3hours.
*
*/

public class WeatherDatabase {
		   
			/**
			*
			* Il metodo getForecast() effettua la connessione con l'API '5 Day / 3 Hour Forecast'. 
			* 
			* @param City che rappresenta il nome della città di cui vogliamo ottenere
			* @return JSONObject contenente le informazioni meteo della città specificata
			*
			*/
			
			public static JSONObject getForecast(String City) throws CityNotFoundException {
				 String data = "";
				 String line = "";
				 String APIkey="839a51d0900812c291d2ba48954f3052";
				 String url = "https://api.openweathermap.org/data/2.5/forecast?q=";
				JSONObject Forecast = null;
				try {
					URLConnection OpenConnection = new URL(url+City+"&units=metric&appid="+APIkey).openConnection();	
					InputStream in = OpenConnection.getInputStream();	

					try {
						InputStreamReader inR = new InputStreamReader(in);
						BufferedReader BUF= new BufferedReader(inR);
						
						while ((line = BUF.readLine()) != null) {
							data += line;
						}
					}finally {
					in.close();
				}
					Forecast=(JSONObject) JSONValue.parseWithException(data);
	}catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
		        if (Forecast == null) throw new CityNotFoundException("City Not Found");
				return Forecast;		
			}

 
	
			

           
}


