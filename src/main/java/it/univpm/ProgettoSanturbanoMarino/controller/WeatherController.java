package it.univpm.ProgettoSanturbanoMarino.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProgettoSanturbanoMarino.database.*;
import it.univpm.ProgettoSanturbanoMarino.service.*;
import it.univpm.ProgettoSanturbanoMarino.util.*;
import it.univpm.ProgettoSanturbanoMarino.model.*;
import it.univpm.ProgettoSanturbanoMarino.filter.*;
import it.univpm.ProgettoSanturbanoMarino.exceptions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe WeatherController continene le rotte che implementano le possibili operazioni che l'intero programma è in grado di svolgere.
*
*/

@RestController
public class WeatherController {
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherController.class, args);
	}
	
	
	/**
	*
	* La rotta di tipo GET '/saveJSON' restituisce le directory nelle quali vengono salvati due file 'CityNameCurrent.txt' e 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo
	* @return path 'CityName.txt' e 'CityNameCurrent.txt'
	*
	*/
	
	@GetMapping("/saveJSON")
	public ResponseEntity<Object> saveJSON(@RequestParam (value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, CityNotFoundException, FileNotFoundException {		
		try {
			String path = "JSONfile API forecast 5days/3hours saved in: " + JSONFile.saveJSON(CityName) + "\nJSONfile API current saved in: " + JSONFileCurrent.saveJSONCurrent(CityName);
			return new ResponseEntity<>(path, HttpStatus.OK);
		}catch (CityNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	*
	* La rotta di tipo GET '/WeatherCurrent' restituisce le previsioni meteo di una determinata città (Ancona di default) 
	* leggendo le informazioni dal JSONObject ricavato dall'API.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*/
	@GetMapping("/WeatherCurrent")
	public ResponseEntity<Object> WeatherCurrent(@RequestParam(value="CityName", defaultValue="Ancona")String CityName) throws ParseException, CityNotFoundException{
		try {
		return new ResponseEntity<>(JSONParseCurrent.ParseCity(CityName),HttpStatus.OK);
		}
		catch(CityNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(),HttpStatus.BAD_REQUEST);
			}
	}
	
	/**
	*
	* La rotta di tipo GET '/WeatherForecast' restituisce le previsioni meteo di una determinata città (Ancona di default) a partire
	* dal momento corrispondente della chiamata fino ai 5 giorni seguenti ogni 3 ore leggendo le informazioni dal JSONObject ricavato dall'API.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*/
	@GetMapping("/WeatherForecast")
	public ResponseEntity<Object> Forecast5day(@RequestParam(value="CityName", defaultValue="Ancona")String CityName) throws ParseException, CityNotFoundException{
		try {
		return new ResponseEntity<>(JSONParse.ParseCity(CityName),HttpStatus.OK);
		}
		catch(CityNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(),HttpStatus.BAD_REQUEST);
			}
	}
	
	/**
	*
	* La rotta di tipo GET '/getCurrentWeather' restituisce le previsioni meteo di un determinata città (Ancona di dafault) leggendo le informazioni 
	* dal file 'CityNameCurrent.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @return CityObject
	*
	*/
	
	@GetMapping("/getCurrentWeather")
	public ResponseEntity<Object> getForecastCurrent(@RequestParam (value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, FileNotFoundException, IOException{		
		try {
			return new ResponseEntity<>(JSONFileCurrentParser.FileCurrentParse(CityName), HttpStatus.OK);
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("Invalid City Name", HttpStatus.OK);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/getForecastWeather' restituisce le previsioni meteo di una determinata città (Ancona di default) a partire
	* dal momento corrispondente della chiamata fino ai 5 giorni seguenti ogni 3 ore leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @return CityObject
	* 
	*/
	
	@GetMapping("/getForecastWeather")
	public ResponseEntity<Object> getForecast(@RequestParam (value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, IOException {		
		try {
			return new ResponseEntity<>(JSONFileParser.FileParse(CityName), HttpStatus.OK);
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("Invalid City Name", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/filterTime' restituisce le previsioni meteo di un determinata città (Ancona di default) ad una fascia oraria richiesta 
	* leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @param Time rappresente la fascia oraria di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*/
	
	@GetMapping("/filterTime")
	public ResponseEntity<Object> getfilterTime(@RequestParam(value = "CityName", defaultValue = "Ancona") String CityName, @RequestParam(value = "Time", defaultValue = "12:00") String Time) throws ParseException, FileNotFoundException, IOException, TimeNotFoundException   {
		try {
			try{
			return new ResponseEntity<>(Filter.timeslot(CityName, Time), HttpStatus.OK);
			}catch(FileNotFoundException e){
			return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
			}
		}catch (TimeNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/filterOneDay' restituisce le previsioni meteo di un determinata città (Ancona di default) di una data richiesta
	* leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @param Date rappresente la data di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*/
	
	@GetMapping("/filterOneDay")
	public ResponseEntity<Object> getfilterOneDay(@RequestParam(value = "CityName", defaultValue = "Ancona") String CityName, @RequestParam(value = "Date", defaultValue = "2022-01-06") String Date) throws ParseException, FileNotFoundException, IOException, DateNotFoundException   {
		try {
			try{
			return new ResponseEntity<>(Filter.onedayslot(CityName, Date), HttpStatus.OK);
			}catch(FileNotFoundException e){
			return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
			}
		}catch (DateNotFoundException e ) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/filterFiveDays' restituisce le previsioni meteo di un determinata città (Ancona di default) per 5 giorni ogni 3 ore  
	* a partire dal momento della chiamata leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @return CityObject
	*
	*/
	
	@GetMapping("/filterFiveDays")
	public ResponseEntity<Object> getfilterFiveDays(@RequestParam(value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, FileNotFoundException, IOException   {
		try{
		return new ResponseEntity<>(Filter.fivedaysslot(CityName), HttpStatus.OK);
		}catch(FileNotFoundException e){
		return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
		}
	}
		
	
	/**
	*
	* La rotta di tipo GET '/HumidityStatsTime' restituisce le statistiche rigurdanti il valore di umidità di un determinata città (Ancona di default) 
	* ad una fascia oraria richiesta leggendo l einrmazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @param Time rappresente la fascia oraria di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*/
	
	@GetMapping("/HumidityStatsTime")
	public ResponseEntity<Object> HumidityStatsTime(@RequestParam(value = "CityName", defaultValue = "Ancona") String CityName, @RequestParam(value = "Time", defaultValue = "12:00") String Time) throws ParseException, FileNotFoundException, IOException, TimeNotFoundException   {
		try {
			try{
				String stats=HumidityStats.HumidityMaxMin(Filter.timeslot(CityName, Time))+"\n"+HumidityStats.HumidityAverage(Filter.timeslot(CityName, Time))+"\n"+HumidityStats.HumidityVariance(Filter.timeslot(CityName, Time));
				return new ResponseEntity<>(stats, HttpStatus.OK);
			}catch(FileNotFoundException e){
		         	 return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
			}	
		}catch (TimeNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/HumidityStatsOneDay' restituisce le statistiche rigurdanti il valore di umidità di un determinata città (Ancona di default) 
	* alla data richiesta leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @param Date rappresente la data di cui si vogliono conoscere le previsioni meteo
	* @return CityObject
	*
	*
	*/
	
	@GetMapping("/HumidityStatsOneDay")
	public ResponseEntity<Object> HumidityStatsOneDay(@RequestParam(value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Date", defaultValue="2022-01-05")String Date) throws ParseException, FileNotFoundException, IOException, DateNotFoundException  {
		try {
			try{
			String stats=HumidityStats.HumidityMaxMin(Filter.onedayslot(CityName, Date))+"\n"+HumidityStats.HumidityAverage(Filter.onedayslot(CityName, Date))+"\n"+HumidityStats.HumidityVariance(Filter.onedayslot(CityName, Date));
			return new ResponseEntity<>(stats, HttpStatus.OK);
			}catch(FileNotFoundException e){
			  return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
			}
		}catch (DateNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	/**
	*
	* La rotta di tipo GET '/HumidityStatsFiveDays' restituisce le statistiche rigurdanti il valore di umidità di un determinata città (Ancona di default) 
	* per 5 giorni ogni 3 ore a partire dal momento della chiamata leggendo le informazioni dal file 'CityName.txt'.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @return CityObject
	*
	*/
	
	@GetMapping("/HumidityStatsFiveDays")
	public ResponseEntity<Object> HumidityStatsFiveDays(@RequestParam(value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, FileNotFoundException, IOException   {
		try{
		String stats=HumidityStats.HumidityMaxMin(Filter.fivedaysslot(CityName))+"\n"+HumidityStats.HumidityAverage(Filter.fivedaysslot(CityName))+"\n"+HumidityStats.HumidityVariance(Filter.fivedaysslot(CityName));
		return new ResponseEntity<>(stats, HttpStatus.OK);
		}catch(FileNotFoundException e){
		return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	*
	* La rotta di tipo GET '/ErrorCurrentForecast' restituisce l'errore matematico tra i valori delle previsioni meteo salvate
	* nel file 'CityNameCurrent.txt' e i valori delle previsioni meteo salvate nel file 'CityName.txt' rispetto ad una
	* determinata data e una determinata ora.
	* @param CityName rappresenta la città di cui si vogliono conoscere le previsioni meteo 
	* @param Date rappresente la data di cui si vogliono conoscere le previsioni meteo
	* @param Time rappresente la fascia oraria di cui si vogliono conoscere le previsioni meteo
	* @return String contenente i valori di errore calcolati
	*
	*/
		
	@GetMapping("/ErrorCurrentForecast")
	public ResponseEntity<Object> getErrorForecastCurrent(@RequestParam (value = "CityName", defaultValue = "Ancona") String CityName, @RequestParam(value = "Date", defaultValue = "2022-01-07") String Date, @RequestParam(value = "Time", defaultValue = "18:00") String Time) throws ParseException, FileNotFoundException, IOException{			
		try {
			return new ResponseEntity<>(ErrorForecastCurrent.ErrorCalculator(JSONFileCurrentParser.FileCurrentParse(CityName), JSONFileParser.FileParse(CityName), Date, Time), HttpStatus.OK);	
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("File non trovato o errore nella scrittura del nome della città, utilizza /saveJSON per creare il file", HttpStatus.BAD_REQUEST);
		}
	}
}
