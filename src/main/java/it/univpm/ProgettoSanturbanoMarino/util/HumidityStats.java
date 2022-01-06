package it.univpm.ProgettoSanturbanoMarino.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


import it.univpm.ProgettoSanturbanoMarino.model.*;

public class HumidityStats {

	public static String HumidityMaxMin(City currentcity) {
		int humiditymax=currentcity.getForecastlist().get(0).getHumidity(), humiditymin=currentcity.getForecastlist().get(0).getHumidity();
		for(int i=1;i<currentcity.getForecastlist().size();i++) {
			if (humiditymax<currentcity.getForecastlist().get(i).getHumidity())humiditymax=currentcity.getForecastlist().get(i).getHumidity();
			if (humiditymin>currentcity.getForecastlist().get(i).getHumidity())humiditymin=currentcity.getForecastlist().get(i).getHumidity();
		}
		
	    String result="maximum humidity: "+humiditymax+" minimum humidity : "+humiditymin;		
		return result;
		}
	
	public static String HumidityAverage(City currentcity) {
		double average,sumhumidity=0;
		for (int i=0;i<currentcity.getForecastlist().size();i++) sumhumidity+= currentcity.getForecastlist().get(i).getHumidity();
		average=sumhumidity/currentcity.getForecastlist().size();
		
		String result=" Humidity Average: "+average;
		return result;
		
	}
	
	public static String HumidityVariance(City currentcity) {
		double average,sumhumidity=0, variance=0, sumvariance=0;
		for (int i=0;i<currentcity.getForecastlist().size();i++) sumhumidity+= currentcity.getForecastlist().get(i).getHumidity();
		average=sumhumidity/currentcity.getForecastlist().size();
		
		for (int i=0;i<currentcity.getForecastlist().size();i++) {
			sumvariance+=Math.pow(currentcity.getForecastlist().get(i).getHumidity()-average, 2);
		}
		variance=sumvariance/currentcity.getForecastlist().size();
		variance=Math.sqrt(variance);
		double roundnumber = Math.round(variance*100.0)/100.0;
		String result=" Humidity Variance: "+roundnumber;
		return result;
	}
	
}
	
