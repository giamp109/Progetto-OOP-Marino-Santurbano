package it.univpm.ProgettoSanturbanoMarino.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoSanturbanoMarino.model.*;

public class JSONFileParser {
	   
public static City FileParse(String cityname) throws FileNotFoundException, IOException, ParseException {
	   
	try {
	   String path = System.getProperty("user.dir")+"\\"+cityname+".txt";
	   JSONParser parser = new JSONParser();
	   Object obj = parser.parse(new FileReader(path));
	   JSONObject object = (JSONObject) obj;   
	   
	}catch(FileNotFoundException e) {
		   e.printStackTrace();
	   }
	   
	   
	return null;
	}
}
