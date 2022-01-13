package it.univpm.ProgettoSanturbanoMarino.service;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.time.LocalDateTime;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe DateCalculatorTest è utilizzata per effettuare i test sui metodi.
*/

class DateCalculatorTest {

private DateCalculator calculator;
	
	/**
	*
	* Vengono inizializzati i componenti utili ai fini dei test.
	*/
	@BeforeEach
	void setUp() throws Exception {
		 calculator= new DateCalculator();
		}
	
		
	/**
	*
	* il test 'EpochConverter()' permette di testare la giusta esecuzione della conversione da un numero, che indica l'epoca, in una data nel formato (yyyy-mm-dd hh-mm-ss)
	*/
	@Test
    	@DisplayName("conversion performed")
    	void EpochConverter() throws IOException, ParseException, CityNotFoundException {
		
		int timezone= 3600;
		long epoch=1642074049;

		String datetime="2022-01-13T12:40:49";
		LocalDateTime datehour = LocalDateTime.parse(datetime);

		assertEquals(DateCalculator.datecalculator(epoch,timezone),datehour);        
    }
}
