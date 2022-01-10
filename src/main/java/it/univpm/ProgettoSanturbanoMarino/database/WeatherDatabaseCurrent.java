package it.univpm.ProgettoSanturbanoMarino.database;

import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.model.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe WeatherDatabaseCurrent continene la connessione con l'API 'Current Weather Data' .
*
*/

public class WeatherDatabaseCurrent {
	
	/**
	* Il metodo getForecastCurrent() effettua la connessione con l'API 'Current Weather Data' e restituisce
	* le informazioni meteo rispetto alla città presa in considerazione.
	* 
	* @param City rappresenta la città di cui si vogliono ottenere le previsioni meteo.
	* @return JSONObject contenente le previsioni meteo della citta presa in considerazione.
	*
	*/

	public static JSONObject getForecastCurrent(String City) throws CityNotFoundException {
		String data = "";
		String line = "";
		String APIkey = "839a51d0900812c291d2ba48954f3052";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=";
		JSONObject Forecast = null;
		try {
			URLConnection OpenConnection = new URL(url+City + "&units=metric&appid=" + APIkey).openConnection();	
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
		Forecast = (JSONObject) JSONValue.parseWithException(data);
		}catch (IOException  e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Forecast == null) throw new CityNotFoundException("City Not Found");
		return Forecast;		
	}
}

