package it.univpm.ProgettoSanturbanoMarino.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import  it.univpm.ProgettoSanturbanoMarino.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.univpm.ProgettoSanturbanoMarino.model.City;
import it.univpm.ProgettoSanturbanoMarino.filter.FilterImplements;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


@RestController
public class WeatherController {

	public static void main(String[] args) {
		SpringApplication.run(WeatherController.class, args);
	}
	
	@GetMapping("/getWeather")
	public ResponseEntity<City> getForecast(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException{
		
	return new ResponseEntity<>(JSONParse.ParseCity(CityName),HttpStatus.OK);
	
	}
	
	@GetMapping("/getHumidity")
	public ResponseEntity<ArrayList<Integer>> getHumidity(@RequestParam (value="CityName", defaultValue="Ancona")String CityName){
		
	return new ResponseEntity<>(JSONParse.getHumidity(WeatherDatabase.getForecast(CityName)),HttpStatus.OK);
	
	}
	
	@GetMapping("/filterTimeSlot")
	public ResponseEntity<City> getfilterTime(@RequestParam(value="CityName",defaultValue="Ancona")String CityName,@RequestParam(value="Time",defaultValue="12:00")String Time) throws ParseException   {
    return new ResponseEntity<>(FilterImplements.timeslot(CityName,Time),HttpStatus.OK);
	}
	
	@GetMapping("/filterOneDaySlot")
	public ResponseEntity<City> getfilterOneDay(@RequestParam(value="CityName",defaultValue="Ancona")String CityName,@RequestParam(value="Date",defaultValue="2022-01-04")String Time) throws ParseException   {
    return new ResponseEntity<>(FilterImplements.onedayslot(CityName,Time),HttpStatus.OK);
	}
	
	@GetMapping("/filterFiveDaysSlot")
	public ResponseEntity<City> getfilterOneDay(@RequestParam(value="CityName",defaultValue="Ancona")String CityName) throws ParseException   {
    return new ResponseEntity<>(FilterImplements.fivedaysslot(CityName),HttpStatus.OK);
    }
	@GetMapping("/saveWeather")
	public ResponseEntity<Object> saveWeather(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException{
	String path=JSONFile.saveJSON(CityName);
	return new ResponseEntity<>(path,HttpStatus.OK);
	}
	
	@GetMapping("/getFile")
	public ResponseEntity<Object> getFile(@RequestParam (value="CityName", defaultValue="Ancona")String CityName) throws ParseException, FileNotFoundException, IOException{		
	return new ResponseEntity<>(JSONFileParser.FileParse(CityName),HttpStatus.OK);
	
	}
	
}
