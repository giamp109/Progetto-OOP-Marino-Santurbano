package it.univpm.ProgettoSanturbanoMarino.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoSanturbanoMarino.model.*;
import it.univpm.ProgettoSanturbanoMarino.exceptions.*;

public class JSONFileCurrentParser {
	
  public static City FileCurrentParse(String cityname) throws FileNotFoundException, IOException, ParseException{

        String id,name,country,main,description;
        LocalDate date;
        LocalTime time;
        double temp,tempmin,tempmax,feelslike;
        int humidity,timezone;
        long dt;
        Time temporary;
        LocalDateTime app;

        String path = System.getProperty("user.dir")+"\\"+cityname+"current.txt";
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));

        JSONObject currentfile = (JSONObject) object; 
        JSONArray weatherarray=(JSONArray)currentfile.get("weather");

        id=(String)(currentfile.get("id"));
        name=(String)(currentfile.get("name"));
        country=(String)(currentfile.get("country"));
        timezone=Integer.parseInt(currentfile.get("timezone").toString());

        City newcity= new City(id,name,country);

        for (int i=0;i<weatherarray.size();i++) {
              JSONObject weatherinfo=(JSONObject) weatherarray.get(i);

              humidity = Integer.parseInt(weatherinfo.get("humidity").toString());

              temp=Double.parseDouble(weatherinfo.get("temp").toString());
              tempmin=Double.parseDouble(weatherinfo.get("temp_min").toString());
              tempmax=Double.parseDouble(weatherinfo.get("temp_max").toString());
              feelslike=Double.parseDouble(weatherinfo.get("feels_like").toString());

              dt=Long.parseLong(weatherinfo.get("dt").toString());

              app=DateCalculator.datecalculator(dt, timezone);

              date=app.toLocalDate();
              time=app.toLocalTime();

              main=(String)weatherinfo.get("main");
              description=(String)weatherinfo.get("description");

              temporary= new Time(humidity,main,description,temp,tempmin,tempmax,feelslike,date,time);
              newcity.getForecastlist().add(temporary);
        }

        return newcity;
  }
}

