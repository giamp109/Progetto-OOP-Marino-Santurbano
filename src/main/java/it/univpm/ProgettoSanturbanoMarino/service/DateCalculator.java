package it.univpm.ProgettoSanturbanoMarino.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateCalculator {

	public static LocalDateTime datecalculator(long epoch,int timezone) {
		LocalDateTime Date = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC.ofTotalSeconds(timezone));
		return Date;
		
	}
}

