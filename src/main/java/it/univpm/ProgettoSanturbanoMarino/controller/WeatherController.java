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
	
	@GetMapping("/getCurrentWeather")
	public ResponseEntity<Object> getForecastCurrent(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException, FileNotFoundException, IOException{		
		try {
			return new ResponseEntity<>(JSONFileCurrentParser.FileCurrentParse(CityName), HttpStatus.OK);
		}catch(FileNotFoundException e) {
			return new ResponseEntity<>("Invalid City Name", HttpStatus.OK);
		}
	}

	@GetMapping("/getForecastWeather")
	public ResponseEntity<Object> getForecast(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException, IOException{		
		try {
			return new ResponseEntity<>(JSONFileParser.FileParse(CityName), HttpStatus.OK);
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("Invalid City Name", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*filter fascia orarira*/
	@GetMapping("/filterTime")
	public ResponseEntity<Object> getfilterTime(@RequestParam(value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Time", defaultValue="12:00")String Time) throws ParseException, FileNotFoundException, IOException, TimeNotFoundException   {
		try {
			return new ResponseEntity<>(Filter.timeslot(CityName, Time), HttpStatus.OK);
		}catch(TimeNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*filter 1 giorno */
	@GetMapping("/filterOneDay")
	public ResponseEntity<Object> getfilterOneDay(@RequestParam(value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Date", defaultValue="2022-01-06")String Date) throws ParseException, FileNotFoundException, IOException, DateNotFoundException   {
		try {
			return new ResponseEntity<>(Filter.onedayslot(CityName, Date), HttpStatus.OK);
		}catch(DateNotFoundException e ) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*filter 5 giorni */
	@GetMapping("/filterFiveDays")
	public ResponseEntity<Object> getfilterFiveDays(@RequestParam(value="CityName", defaultValue="Ancona")String CityName) throws ParseException, FileNotFoundException, IOException   {
		return new ResponseEntity<>(Filter.fivedaysslot(CityName), HttpStatus.OK);
	}
		
	/*stats fascia oraria*/
	@GetMapping("/HumidityStatsTime")
	public ResponseEntity<Object> HumidityStatsTime(@RequestParam(value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Time", defaultValue="12:00")String Time) throws ParseException, FileNotFoundException, IOException, TimeNotFoundException   {
		try {
			String stats=HumidityStats.HumidityMaxMin(Filter.timeslot(CityName, Time))+HumidityStats.HumidityAverage(Filter.timeslot(CityName, Time))+HumidityStats.HumidityVariance(Filter.timeslot(CityName, Time));
			return new ResponseEntity<>(stats, HttpStatus.OK);
		}catch(TimeNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*stats 1 giorno */
	@GetMapping("/HumidityStatsOneDay")
	public ResponseEntity<Object> HumidityStatsOneDay(@RequestParam(value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Date", defaultValue="2022-01-05")String Date) throws ParseException, FileNotFoundException, IOException, DateNotFoundException  {
		try {
			String stats=HumidityStats.HumidityMaxMin(Filter.onedayslot(CityName, Date))+HumidityStats.HumidityAverage(Filter.onedayslot(CityName, Date))+HumidityStats.HumidityVariance(Filter.onedayslot(CityName, Date));
			return new ResponseEntity<>(stats, HttpStatus.OK);
		}catch(DateNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*stats 5 giorni */
	@GetMapping("/HumidityStatsFiveDays")
	public ResponseEntity<Object> HumidityStatsFiveDays(@RequestParam(value="CityName", defaultValue="Ancona")String CityName) throws ParseException, FileNotFoundException, IOException   {
		String stats=HumidityStats.HumidityMaxMin(Filter.fivedaysslot(CityName))+HumidityStats.HumidityAverage(Filter.fivedaysslot(CityName))+HumidityStats.HumidityVariance(Filter.fivedaysslot(CityName));
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}
	
	/*salvataggio file JSON in locale con le previsioni meteo prese dall' API current e forecast 5day/3hours*/
	@GetMapping("/saveJSON")
	public ResponseEntity<Object> saveJSONCurrent(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException, CityNotFoundException, FileNotFoundException{		
		try {
			String path="JSONfile API forecast 5days/3hours saved in: "+JSONFile.saveJSON(CityName)+"\nJSONfile API current saved in: "+JSONFileCurrent.saveJSONCurrent(CityName);
			return new ResponseEntity<>(path, HttpStatus.OK);
		}catch(CityNotFoundException e) {
			return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*errore tra forecast e attuale*/	
	@GetMapping("/ErrorCurrentForecast")
	public ResponseEntity<Object> getErrorForecastCurrent(@RequestParam (value="CityName", defaultValue="Ancona")String CityName, @RequestParam(value="Date", defaultValue="2022-01-07")String Date, @RequestParam(value="Time", defaultValue="18:00")String Time) throws ParseException, FileNotFoundException, IOException{			
		try {
			return new ResponseEntity<>(ErrorForecastCurrent.ErrorCalculator(JSONFileCurrentParser.FileCurrentParse(CityName), JSONFileParser.FileParse(CityName), Date, Time), HttpStatus.OK);	
		}catch(FileNotFoundException e) {
			return new ResponseEntity<>("File not found, use the route 'save JSON' to crate the files", HttpStatus.BAD_REQUEST);
		}
	}
}
