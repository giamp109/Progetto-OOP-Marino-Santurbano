package it.univpm.ProgettoSanturbanoMarino.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import it.univpm.ProgettoSanturbanoMarino.model.*;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe HumidityStats mette a disposizione i metodi per il calcolo delle statistiche sull'umidità.
* 
*/
public class HumidityStats {

static String result;
	
	/**
	*
	* il metodo 'HumidityMaxMin()' permette di trovare l'umidità massima e l'umidità minima tra tutti i valori analizzati.  
	* 
	* @param City currentcity, oggetto di tipo City di cui viene analizzato la ForecastList in modo da effettuaree le statistiche sull'umidità.
	* @return String result contenente il valore massimo e il valore minimo dell'umidità.
	*/
	public static String HumidityMaxMin(City currentcity) {
		int humiditymax = currentcity.getForecastlist().get(0).getHumidity(), humiditymin = currentcity.getForecastlist().get(0).getHumidity();
		for(int i = 1; i < currentcity.getForecastlist().size(); i++) {
			if (humiditymax < currentcity.getForecastlist().get(i).getHumidity())
				humiditymax = currentcity.getForecastlist().get(i).getHumidity();
			if (humiditymin > currentcity.getForecastlist().get(i).getHumidity())
				humiditymin = currentcity.getForecastlist().get(i).getHumidity();
		}
		
		result="maximum humidity: "+humiditymax+"\nminimum humidity : "+humiditymin;		
		return result;
	}
	
	/**
	*
	* il metodo 'HumidityAverage()' permette di calcolare la media dell'umidità tra tutti i valori analizzati.  
	* 
	* @param City currentcity, oggetto di tipo City di cui viene analizzato la ForecastList in modo da effettuaree le statistiche sull'umidità.
	* @return String result, contenente il valore della media dell'umidità.
	*/
	public static String HumidityAverage(City currentcity) {
		double average, sumhumidity = 0;
		for (int i = 0; i < currentcity.getForecastlist().size(); i++)
			sumhumidity += currentcity.getForecastlist().get(i).getHumidity();
		average = sumhumidity/currentcity.getForecastlist().size();
		
	        result = " Humidity Average: " + average;
		return result;	
	}
	
	/**
	*
	* il metodo 'HumidityVariance()' permette di calcolare la varianza dell'umidità tra tutti i valori analizzati.  
	* 
	* @param City currentcity, oggetto di tipo City di cui viene analizzato la ForecastList in modo da effettuaree le statistiche sull'umidità.
	* @return String result, contenente il valore della varianza dell'umidità.
	*/
	public static String HumidityVariance(City currentcity) {
		double average, sumhumidity = 0, variance = 0, sumvariance = 0;
		for (int i = 0; i < currentcity.getForecastlist().size(); i++) 
			sumhumidity += currentcity.getForecastlist().get(i).getHumidity();
		average = sumhumidity/currentcity.getForecastlist().size();
		
		for (int i = 0; i < currentcity.getForecastlist().size(); i++) 
			sumvariance += Math.pow(currentcity.getForecastlist().get(i).getHumidity() - average, 2);
		
		variance = sumvariance/currentcity.getForecastlist().size();
		variance = Math.sqrt(variance);
		double roundnumber = Math.round(variance*100.0)/100.0;
	        result = " Humidity Variance: " + roundnumber;
		return result;
	}	
}
	
