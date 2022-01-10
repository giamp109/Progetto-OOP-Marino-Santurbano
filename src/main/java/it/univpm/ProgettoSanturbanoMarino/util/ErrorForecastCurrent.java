package it.univpm.ProgettoSanturbanoMarino.util;

import it.univpm.ProgettoSanturbanoMarino.exceptions.DateNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.exceptions.TimeNotFoundException;
import it.univpm.ProgettoSanturbanoMarino.model.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
*
* @author Davide Santurbano
* @author Giampaolo Marino
*
* La classe ErrorForecastCurrent mette a disposizione un metodo per il calcolo dell'errore dei valori delle previsioni meteo 
* tra l'API 5 day / 3 hour Forecast e l'API Current weather data.
*/
public class ErrorForecastCurrent {

	/**
	*
	* Il 'metodo ErrorCalculator()' calcola l'errore tra le due API utilizzate nel programma ovvero 5 day / 3 hour forecast e la Current weather data. 
	* L'errore viene calcolato sulla temperatura, temperatura massima, temperatura minima, temperatura percepita e sull'umidità, in base alla data e l'orario.
	*
	* @param currentcity rappresenta l'oggetto di tipo città creato leggendo il file 'citynamecurrent.txt'. 
	* @param forecastcity rappresenta l'oggetto di tipo città creato leggendo il file 'cityname.txt'.
	* @param String date rappresenta la data in cui si vuole calcolare tale errore.
	* @param String time rappresenta l'orario in cui si vuole calcolare tale errore.
	* @return String con i valori degli errori calcolati.
	*/
	public static String ErrorCalculator(City currentcity,City forecastcity,String date,String time) {
		
		LocalDate supportdate= LocalDate.parse(date);
		LocalTime supportime= LocalTime.parse(time);

		for (int i=0;i<forecastcity.getForecastlist().size();i++) 
			for (int j=0;j<currentcity.getForecastlist().size();j++) {
				
				if(forecastcity.getForecastlist().get(i).getDate().equals(supportdate) && currentcity.getForecastlist().get(j).getDate().equals(supportdate)  )  
				   if( forecastcity.getForecastlist().get(i).getTime().getHour()== supportime.getHour() && currentcity.getForecastlist().get(j).getTime().getHour() == supportime.getHour())
				   {
					    double errortemp=forecastcity.getForecastlist().get(i).getTemperature()-currentcity.getForecastlist().get(j).getTemperature();
						double errortempmax=forecastcity.getForecastlist().get(i).getTemperatureMax()-currentcity.getForecastlist().get(j).getTemperatureMax();
						double errortempmin=forecastcity.getForecastlist().get(i).getTemperatureMin()-currentcity.getForecastlist().get(j).getTemperatureMin();
						double errorfeelslike=forecastcity.getForecastlist().get(i).getFeelsLike()-currentcity.getForecastlist().get(j).getFeelsLike();
						int errorhumidity=forecastcity.getForecastlist().get(i).getHumidity()-currentcity.getForecastlist().get(j).getHumidity();  					   
						return "error between forecast and current temperature: "+Math.round(errortemp*100.0)/100.0+"\nerror between forecast and current maximum temperature: "+Math.round(errortempmax*100.0)/100.0+"\nerror between forecast and current minimum temperature :"+Math.round(errortempmin*100.0)/100.0+"\nerror between forecast and current felt like temperature: "+Math.round(errorfeelslike*100.0)/100.0+"\nerror between forecast and current humidity: "+Math.round(errorhumidity*100.0)/100.0 ;
				   }
					
					
			
			}

		return "error between forecast and current temperature: null"+"\nerror between forecast and current maximum temperature: null"+"\nerror between forecast and current minimum temperature : null"+"\nerror between forecast and current felt like temperature: null"+"\nerror between forecast and current humidity: null";

	}

}
