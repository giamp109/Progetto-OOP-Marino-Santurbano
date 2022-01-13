package it.univpm.ProgettoSanturbanoMarino.service;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.time.LocalDateTime;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import it.univpm.ProgettoSanturbanoMarino.exceptions.CityNotFoundException;



class DateCalculatorTest {

private DateCalculator calculator;
	
	@BeforeEach
	void setUp() throws Exception {
		 calculator= new DateCalculator();
		}
	
	@Test
    @DisplayName("File saved correctly")
    void saveFile() throws IOException, ParseException, CityNotFoundException {
		
        int timezone= 3600;
        long epoch=1642074049;

        String datetime="2022-01-13T12:40:49";
		LocalDateTime datehour = LocalDateTime.parse(datetime);

        assertEquals(DateCalculator.datecalculator(epoch,timezone),datehour);        
    }
}
