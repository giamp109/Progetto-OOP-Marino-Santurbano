package it.univpm.ProgettoSanturbanoMarino.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.ProgettoSanturbanoMarino.database.WeatherDatabase;
import  it.univpm.ProgettoSanturbanoMarino.service.JSONParse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.univpm.ProgettoSanturbanoMarino.model.City;

import org.json.simple.JSONObject;

import java.util.ArrayList;


@RestController
public class WeatherController {

	public static void main(String[] args) {
		SpringApplication.run(WeatherController.class, args);
	}
	
	@GetMapping("/getWeather")
	public ResponseEntity<City> getForecast(@RequestParam (value="CityName", defaultValue="Ancona")String CityName){
		
	return new ResponseEntity<>(JSONParse.ParseCity(CityName),HttpStatus.OK);
	
	}
	
	@GetMapping("/getHumidity")
	public ResponseEntity<ArrayList<Integer>> getHumidity(@RequestParam (value="CityName", defaultValue="Ancona")String CityName){
		
	return new ResponseEntity<>(JSONParse.getHumidity(WeatherDatabase.getForecast(CityName)),HttpStatus.OK);
	
	}
}
