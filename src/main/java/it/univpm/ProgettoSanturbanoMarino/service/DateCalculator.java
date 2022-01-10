package it.univpm.ProgettoSanturbanoMarino.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe DateCalculator contiene il metodo per convertire l'epoca in LocalDateTime.
*
*/

public class DateCalculator {
	
	/**
	* Il metodo 'datecalculator()' converte l'epoca in LocalDateTime sfruttando il timezone.
	* @param epoch che rappresenta l'epoca
	* @param timezone che rappresenta lo shift in secondi dall'UTC
	* @return Date che rappresenta l'epoca espressa in ('yyyy-mm-gg hh:mm:ss')
	*/
	public static LocalDateTime datecalculator(long epoch,int timezone) {
		LocalDateTime Date = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC.ofTotalSeconds(timezone));
		return Date;
		
	}
}

