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

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe WeatherControllerTest è utilizzata per effettuare i test sui metodi.
*/
class WeatherControllerTest {

	private WeatherController controller;
	
	/**
	*
	* Vengono inizializzate i componenti utili ai fini dei test.
	* 
	*/
	@BeforeEach
	void setUp() throws Exception {
		controller = new WeatherController();
		
		}
	
	/**
	* Il test 'saveFile()' verifica se i path in cui vengono salvati i due file (cityname.txt e citynamecurrent.txt) è corretto.
	* 
	*/
	@Test
    	@DisplayName("File saved correctly")
    	void saveFile() throws IOException, ParseException, CityNotFoundException {
    	
		String cityname = "Trento";
		String result="JSONfile API forecast 5days/3hours saved in: "+ System.getProperty("user.dir")+"\\"+cityname+".txt" +"\nJSONfile API current saved in: "+ System.getProperty("user.dir")+"\\"+cityname+"current.txt";

		ResponseEntity<String> response= new ResponseEntity<>(result,HttpStatus.OK);
		assertEquals(controller.saveJSON(cityname),response);        
    }
	
	/**
	* Il test 'HumidityStatsTimeControl()' verifica la correttezza delle statistiche dell'umidità (fa riferimento al file 'Ancona.txt') 
	* eseguite per fascia oraria.
	*/
	@Test
   	@DisplayName("Correct Humidity Stats Time")
    	void HumidityStatsTimeControl() throws FileNotFoundException, ParseException, IOException, TimeNotFoundException  {
    	
		String cityname = "Ancona";
		String time="12:00";

		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 71\nminimum humidity : 44\n Humidity Average: 59.8\n Humidity Variance: 8.84",HttpStatus.OK);
		assertEquals(controller.HumidityStatsTime(cityname, time),response);        
    }
	
	/**
	* Il test 'HumidityStatsOneDayControl()' verifica la correttezza delle statistiche dell'umidità (fa riferimento al file 'Ancona.txt') 
	* eseguite in un determinato giorno.
	*/
	@Test
    	@DisplayName("Correct Humidity Stats OneDay")
    	void HumidityStatsOneDayControl() throws FileNotFoundException, ParseException, IOException, DateNotFoundException  { 
    	
		String cityname = "Ancona";
		String date="2022-01-13";

		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 78\nminimum humidity : 59\n Humidity Average: 68.75\n Humidity Variance: 5.8",HttpStatus.OK);
		assertEquals(controller.HumidityStatsOneDay(cityname, date),response);        
    }
	
	/**
	* Il test 'HumidityStatsFiveDayControl()' verifica la correttezza delle statistiche dell'umidità (fa riferimento al file 'Ancona.txt') 
	* eseguite in un periodo di 5 giorni.
	*/
	@Test
    	@DisplayName("Correct Humidity Stats Five Days")
    	void HumidityStatsFiveDayControl() throws FileNotFoundException, ParseException, IOException {
    	
		String cityname = "Ancona";

		ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 89\nminimum humidity : 43\n Humidity Average: 67.65\n Humidity Variance: 10.18",HttpStatus.OK);
		assertEquals(controller.HumidityStatsFiveDays(cityname),response);        
    }
	


}
