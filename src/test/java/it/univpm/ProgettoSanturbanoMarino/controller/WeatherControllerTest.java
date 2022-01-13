package it.univpm.ProgettoSanturbanoMarino.controller;

import static org.junit.Assert.assertEquals;


import java.io.FileNotFoundException;
import java.io.IOException;


import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.exceptions.DateNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.exceptions.TimeNotFoundException;


class WeatherControllerTest {

	private WeatherController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new WeatherController();
		
		}
	
	@Test
    @DisplayName("File saved correctly")
    void saveFile() throws IOException, ParseException, CityNotFoundException {
    	
        String cityname = "Trento";
    	String result="JSONfile API forecast 5days/3hours saved in: "+ System.getProperty("user.dir")+"\\"+cityname+".txt" +"\nJSONfile API current saved in: "+ System.getProperty("user.dir")+"\\"+cityname+"current.txt";
		
    	ResponseEntity<String> response= new ResponseEntity<>(result,HttpStatus.OK);
        assertEquals(controller.saveJSON(cityname),response);        
    }
	
	@Test
    @DisplayName("Correct Humidity Stats Time")
    void HumidityStatsTimeControl() throws FileNotFoundException, ParseException, IOException, TimeNotFoundException  {
    	
        String cityname = "Ancona";
        String time="12:00";
  
		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 71 minimum humidity : 44 Humidity Average: 59.8 Humidity Variance: 8.84",HttpStatus.OK);
        assertEquals(controller.HumidityStatsTime(cityname, time),response);        
    }
	
	@Test
    @DisplayName("Correct Humidity Stats OneDay")
    void HumidityStatsOneDayControl() throws FileNotFoundException, ParseException, IOException, DateNotFoundException  {
    	
        String cityname = "Ancona";
        String date="2022-01-13";
        
		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 78 minimum humidity : 59 Humidity Average: 68.75 Humidity Variance: 5.8",HttpStatus.OK);
        assertEquals(controller.HumidityStatsOneDay(cityname, date),response);        
    }
	
	@Test
    @DisplayName("Correct Humidity Stats Five Days")
    void HumidityStatsFiveDayControl() throws FileNotFoundException, ParseException, IOException {
    	
        String cityname = "Ancona";

		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 89 minimum humidity : 43 Humidity Average: 67.65 Humidity Variance: 10.18",HttpStatus.OK);
        assertEquals(controller.HumidityStatsFiveDays(cityname),response);        
    }
	


}
