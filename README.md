# MyWeather
***AUTORI <br>
Davide Santurbano <br>
Giampaolo Marino***

## Descrizione generale
Il progetto MyWeather è un web service ovvero un sistema software progettato per supportare l'interoperabilità tra diversi elaboratori su una medesima rete comunicando tramite il protocollo HTTP. MyWeather è in grado di mettersi a servizio mette di un client, come ad esempio PostMan, consentendo agli utenti che vi si collegano di utilizzare le funzioni messe a disposizione.
MyWeather mette a disposizione un servizio di previsioni meteo che:

 - Permette di visualizzare le previsioni meteo della città desiderata attraverso l'utilizzo di due APIs di OpenWeather. Le previsioni meteo che è possibile visualizzare sono fornite per 5 giorni ogni 3 ore o in alternativa nell'orario corrente.
 - Permette di visualizzare le statistiche riguardanti i valori di umidità di una determinata città filtrati in base alla periodicità: fascia oraria, giorni, settimanale.
 -  Permette di visualizzare l'errore tra "*forecast 5 day / 3 hour*" e "*current weather data*" rispetto ai valori di temperatura, temperatura minima, temperatura massima, temperatura percepita e umidità di una determinata città.
 - Permette di salvare localmente due file rispetto ad una determinata città: uno contenente le previsioni meteo correnti (aggiornato automaticamente ogni ora) ed un altro contenente le previsioni meteo dei successivi 5 giorni dal momento della chiamata.
 
## APIs
MyWeather impiega due APIs di [OpenWeatherMap](https://openweathermap.org/api): 
 - [*forecast 5 day / 3 hour*](https://openweathermap.org/forecast5)
 - [*current weather data*](https://openweathermap.org/current)
 
Di seguito vengono mostrati due esempi di chiamata delle API tramite PostMan.
#
### *Esempio forecast 5 day / 3 hour*
La chiamata effettuata tramite PostMan è la seguente:

    api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}

 - *city name* rappresenta la città di cui si vogliono conoscere le previsioni meteo;
 - *API key* rappresenta la chiave di accesso al servizio.

Attraverso una richiesta di tipo GET effettuata tramite PostMan si ottiene il seguente JSONFile.
```json
    {
    "cod": "200",
    "message": 0,
    "cnt": 40,
    "list": [
        {
            "dt": 1642010400,
            "main": {
                "temp": 279.13,
                "feels_like": 275.8,
                "temp_min": 276.71,
                "temp_max": 279.13,
                "pressure": 1030,
                "sea_level": 1030,
                "grnd_level": 1013,
                "humidity": 60,
                "temp_kf": 2.42
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds",
                    "icon": "04n"
                }
            ],
            "clouds": {
                "all": 75
            },
            "wind": {
                "speed": 4.82,
                "deg": 0,
                "gust": 8.27
            },
            "visibility": 10000,
            "pop": 0,
            "sys": {
                "pod": "n"
            },
            "dt_txt": "2022-01-12 18:00:00"
        }
```	
        ...
```json       
        "city": {
	        "id": 3183087,
	        "name": "Provincia di Ancona",
	        "coord": {
		        "lat": 43.55,
		        "lon": 13.1667
	        },
	        "country": "IT",
	        "population": 478319,
	        "timezone": 3600,
	        "sunrise": 1641969526,
	        "sunset": 1642002716
        }
```
I valori presi in considerazione da MyWeather sono:
 - `city : id` // corrisponde all'ID della città selezionata
 - `city : name` // corrisponde al nome della città selezionata
 - `city : country` // corrisponde allo Stato della città selezionata
 - `list [main : temp]` // corrisponde alla temperatura in un determinato orario
 - `list [main : temp_min]` // corrisponde alla temperatura minima in un determinato orario
 - `list [main : temp_max]` // corrisponde alla temperatura massima in un determinato orario
 - `list [main : feels_like]` // corrisponde alla temperatura percepita in un determinato orario
 - `list [main : humidity]` // corrisponde all'umidità percepita in un determinato orario
 - `list [weather : main]` // corrisponde alla condizione meteo 
 - `list [weather : description]` // corrisponde alla descrizione della condizione meteo
 - `list [dt_txt]` // corrisponde alla data e all'orario (nel formato : "yyyy-mm-dd   hh:mm:ss")
#
### *Esempio current weather data*
La chiamata effettuata tramite PostMan è la seguente:

    api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
 - *city name* rappresenta la città di cui si vogliono conoscere le previsioni meteo;
 - *API key* rappresenta la chiave di accesso al servizio.
 
Attraverso una richiesta di tipo GET effettuata tramite PostMan si ottiene il seguente JSONFile.
```json
    {
        "coord": {
            "lon": 13.1667,
            "lat": 43.55
        },
        "weather": [
            {
                "id": 801,
                "main": "Clouds",
                "description": "few clouds",
                "icon": "02n"
            }
        ],
        "base": "stations",
        "main": {
            "temp": 278.65,
            "feels_like": 274.61,
            "temp_min": 276.25,
            "temp_max": 280.56,
            "pressure": 1031,
            "humidity": 56
        },
        "visibility": 10000,
        "wind": {
            "speed": 6.17,
            "deg": 350
        },
        "clouds": {
            "all": 20
        },
        "dt": 1642010917,
        "sys": {
            "type": 2,
            "id": 2022004,
            "country": "IT",
            "sunrise": 1641969526,
            "sunset": 1642002716
        },
        "timezone": 3600,
        "id": 3183087,
        "name": "Provincia di Ancona",
        "cod": 200
    }
```
I valori presi in considerazione da MyWeather sono:
 - `id` // corrisponde all'ID della città selezionata
 - `name` // corrisponde al nome della città selezionata
 - `sys : country` // corrisponde allo Stato della città selezionata
 - `main : temp` // corrisponde alla temperatura in un determinato orario
 - `main : temp_min` // corrisponde alla temperatura minima in un determinato orario
 - `main : temp_max` // corrisponde alla temperatura massima in un determinato orario
 - `main : feels_like` // corrisponde alla temperatura percepita in un determinato orario
 - `main : humidity` // corrisponde all'umidità percepita in un determinato orario
 - `weather : main` // corrisponde alla condizione meteo 
 - `weather : description` // corrisponde alla descrizione della condizione meteo
 - `dt` // corrisponde alla data e all'orario (nel formato "epoch")
 - `timezone` // corrisponde allo shift in secondi rispetto all'UTC

## Introduzione rotte
|*ROTTA*| *METODO* | *@param* |*DESCRIZIONE*
|--|--|--|--|
| `/saveJSON` | GET |CityName| Restituisce le directory dei file salvati 'CityName.txt' e 'CityNameCurrent.txt'
|`/WeatherCurrent`|GET|CityName| Restituisce le previsioni meteo sotto forma di CityObject nel momento della chiamata nell'orario corrente
|`/WeatherForecast` |GET| CityName | Restituisce le previsioni meteo sotto forma di CityObject per i successivi 5 giorni dalla chiamata ogni 3 ore
|`/getCurrentWeather`| GET | CityName | Restituisce le previsioni meteo sotto forma di CityObject leggendole dal file denominato 'CityNameCurrent.txt'
|`/getForecastWeather` | GET | CityName | Restituisce le previsioni meteo sotto forma di CityObject leggendole dal file denominato 'CityName.txt'
|`/filterTime` | GET | CityName, Time | Filtra le previsioni meteo lette dal file 'CityName.txt' in base ad una fascia oraria stabilita restituendo un CityObject
|`/filterOneDay` | GET | CityName, Date | Filtra le previsioni meteo lette dal file 'CityName.txt' in base ad una data restituendo un CityObject
|`/filterFiveDays`| GET | CityName | Filtra le previsioni meteo lette dal file 'CityName.txt' con periodicità 5 giorni restituendo un CityObject
|`/HumidityStatsTime`| GET | CityName, Time | Restituisce statistiche relative l'umidità di una determinata città in una specifica fascia oraria
|`/HumidityStatsOneDay` | GET | CityName, Date | Restituisce statistiche relative l'umidità di una determinata città in una data specifica
|`/HumidityStatsFiveDays` | GET | CityName | Restituisce statistiche relative l'umidità di una determinata città per i cinque giorni successivi alla chiamata
|`/ErrorCurrentForecast` | GET | CityName, Date, Time | Restituisce l'errore matematico calcolato tra le previsioni meteo del *"current weather data"* e quelle del *"forecast 5 day / 3 hours"*

## Spiegazioni rotte
### *Esempio chiamata `/saveJSON`*

    localhost:8080/saveJSON?CityName={CityName}
La rotta */saveJSON*  permette di salvare due file: 

 - 'CityName.txt' : contenete le informazioni meteo ottenute dall'API *forecast 5 day / 3 hours*
 - 'CityNameCurrent.txt' : contenente le informazioni meteo ottenute dall'API *current weather data*

Il file CityNameCurrent.txt aggiorna le informazioni meteo ogni ora grazie all'impiego di un timer.
La rotta restituisce il `path`, ovvero le directory in cui vengono salvati i due file.

#### Chiamata PostMan

    JSONfile API forecast 5days/3hours saved in: D:\programmazione ad ogetti-UNIVPM\lavori\Progetto-Santurbano-Marino\Milano.txt  
    JSONfile API current saved in: D:\programmazione ad ogetti-UNIVPM\lavori\Progetto-Santurbano-Marino\Milanocurrent.txt

#
### *Esempio chiamata `/WeatherCurrent`*

    localhost:8080/WeatherCurrent?CityName={CityName}
La rotta */WeatherCurrent* permette di visualizzare le informazioni meteo riguardo ad una specifica città, prendendo tali informazioni dall'API *weather data current*. Tale rotta impiega la classe *database.WeatherDatabaseCurrent* che conteien il metodo *'getForecastCurrent()'* per ottenere il file JSON dall'API e la classe *service.JSONParseCurrent* che contiene il metodo *'ParseCity()'* per effettuare il parsing delle informazioni ottenute dall'API  restituendo un `CityObject` contenente le previsioni meteo. 

#### Chiamata PostMan
```json
    {
	    "id": "3173435",
	    "cityname": "Milan",
	    "citycountry": "IT",
	    "forecastlist": [
		    {
		    "humidity": 55,
		    "main": "Clear",
		    "description": "clear sky",
		    "temperature": 9.01,
		    "date": "2022-01-15",
		    "time": "11:54:30",
		    "temperatureMax": 16.61,
		    "temperatureMin": 4.82,
		    "feelsLike": 9.01
		    }
	    ]
    }
```
#
### *Esempio chiamata `/WeatherForecast`*

    localhost:8080/WeatherForecast?CityName={CityName}
La rotta */WeatherForecast* permette di visualizzare le informazioni meteo riguardo ad una specifica città, prendendo tali informazioni dall'API *forecast 5 day / 3 hour*. Tale rotta impiega la classe *database.WeatherDatabase* che contiene il metodo *'getForecast()'* per ottenere il file JSON dall'API e la classe *service.JSONParse* che contiene il metodo *'ParseCity()'* per effettuare il parsing delle informazioni ottenute dall'API  restituendo un `CityObject` contenente le previsioni meteo. 

#### Chiamata PostMan
```json
    "id": "3173435",
    "cityname": "Milan",
    "citycountry": "IT",
    "forecastlist": [
	    {
		    "humidity": 53,
		    "main": "Clear",
		    "description": "clear sky",
		    "temperature": 9.35,
		    "date": "2022-01-15",
		    "time": "12:00:00",
		    "temperatureMax": 9.35,
		    "temperatureMin": 9.11,
		    "feelsLike": 9.35
	    }
```
	    ...
```json
	   ]
```

#
### *Esempio chiamata `/getCurrentWeather`*

    localhost:8080/getCurrentWeather?CityName={CityName}
La rotta */getCurrentWeather* permette di leggere le informazioni dal file *'CityNamecurrent.txt'* e restituire le previsioni meteo sottoforma di `CityObject`. Tale rotta impiega la classe *service.JSONFileCurrentParser* che contiene il metodo *'FileCurrentParse()'* che apre in lettura il file '*CityNamecurrent.txt*', converte il suo contenuto in `JSONObject` e effettua il parsing delle informazioni creando un `CityObject`.  Si differenzia dalla rotta CurrentWeather per il fatto che la precedente permette di visualizzare le previsioni meteo solo nell'orario della chiamata, mentre la getCurrentWeather permette di visualizzare le previsioni meteo aggiornate ogni ora.

#### Chiamata PostMan
```json
       {
        "id": "3183087",
        "cityname": "Provincia di Ancona",
        "citycountry": "IT",
	        "forecastlist": [
		        {
			        "humidity": 74,
			        "main": "Rain",
			        "description": "light rain",
			        "temperature": 0.38,
			        "date": "2022-01-09",
			        "time": "19:14:49",
			        "temperatureMax": 2.69,
			        "temperatureMin": -0.33,
			        "feelsLike": -3.9
		        }
```
			   ...
```json
		      ]
        }
```
#
### *Esempio chiamata `/getForecastWeather`*

    localhost:8080/getForecastWeather?CityName={CityName}
 La rotta */getForecastWeather* permette di leggere le informazioni dal file *'CityName.txt'* e restituire le previsioni meteo sottoforma di `CityObject`. Tale rotta impiega la classe *service.JSONFileParser* che contiene il metodo *'FileParse()'* che apre in lettura il file '*CityName.txt*', converte il suo contenuto in JSONObject e effettua il parsing delle informazioni creando un `CityObject`.  
 
#### Chiamata PostMan
```json
    {
	    "id": "3183087",
	    "cityname": "Provincia di Ancona",
	    "citycountry": "IT",
		    "forecastlist": [
			    {
				    "humidity": 81,
				    "main": "Rain",
				    "description": "light rain",
				    "temperature": 1.05,
				    "date": "2022-01-09",
				    "time": "21:00:00",
				    "temperatureMax": 2.4,
				    "temperatureMin": 1.05,
				    "feelsLike": -2.73
			    }
```
			    ...
```json
		    ]
    }
```
 #
### *Esempio chiamata `/filterTime`*

    localhost:8080/filterTime?CityName={CityName}&Time={Time}
La rotta *'/filterTime'* permette di filtrare le previsioni meteo contenute nel file *'CityName.txt'* per fascia oraria. Tale rotta impiega la classe *'filter.Filter'* che contiene il metodo *'timeslot()'* che effettua il parsing delle informazioni dal file *'CityName.txt'* e filtra il `CityObject` ottenuto in base alla fascia oraria.
 
#### Chiamata PostMan
```json
    {
	    "id": "3183087",
	    "cityname": "Provincia di Ancona",
	    "citycountry": "IT",
		    "forecastlist": [
			    {
				    "humidity": 71,
				    "main": "Clouds",
				    "description": "overcast clouds",
				    "temperature": 6.14,
				    "date": "2022-01-10",
				    "time": "12:00:00",
				    "temperatureMax": 6.14,
				    "temperatureMin": 6.14,
				    "feelsLike": 1.08
			    },
			    {
				    "humidity": 63,
				    "main": "Clouds",
				    "description": "scattered clouds",
				    "temperature": 6.32,
				    "date": "2022-01-11",
				    "time": "12:00:00",
				    "temperatureMax": 6.32,
				    "temperatureMin": 6.32,
				    "feelsLike": 2.09
			    }
```
			    ...
```json
		    ]
    }
```
 #
### *Esempio chiamata `/filterOneDay`*

    localhost:8080/filterOneDay?CityName={CityName}&Date={Date}
La rotta *'/filterOneDay'* permette di filtrare le previsioni meteo contenute nel file *'CityName.txt'* in base ad un determinato giorno. Tale rotta impiega la classe *'filter.Filter'* che contiene il metodo *'onedayslot()'* che effettua il parsing delle informazioni dal file *'CityName.txt'* e filtra il `CityObject` ottenuto in base ad una determinata data.
 
#### Chiamata PostMan
```json
    {
	    "id": "3183087",
	    "cityname": "Provincia di Ancona",
	    "citycountry": "IT",
		    "forecastlist": [
			    {
				    "humidity": 86,
				    "main": "Rain",
				    "description": "light rain",
				    "temperature": 2.09,
				    "date": "2022-01-10",
				    "time": "00:00:00",
				    "temperatureMax": 2.95,
				    "temperatureMin": 2.09,
				    "feelsLike": -1.29
			    },
			    {
				    "humidity": 89,
				    "main": "Rain",
				    "description": "light rain",
				    "temperature": 3.11,
				    "date": "2022-01-10",
				    "time": "03:00:00",
				    "temperatureMax": 3.11,
				    "temperatureMin": 3.11,
				    "feelsLike": -0.64
			    },
```
			    ...
```json
		    ]
    }
```
 #
### *Esempio chiamata `/filterFiveDays`*

    localhost:8080/filterFiveDays?CityName={CityName}
    
La rotta *'/filterFiveDays'* permette di filtrare le previsioni meteo contenute nel file *'CityName.txt'* in base alla periodicità: 5 giorni. Tale rotta impiega la classe *'filter.Filter'* che contiene il metodo *'fivedayslot()'* che effettua il parsing delle informazioni dal file *'CityName.txt'* e filtra il `CityObject` ottenuto in base alla periodicità 5 giorni.
 
#### Chiamata PostMan
```json
    {
	    "id": "3183087",
	    "cityname": "Provincia di Ancona",
	    "citycountry": "IT",
		    "forecastlist": [
			    {
				    "humidity": 81,
				    "main": "Rain",
				    "description": "light rain",
				    "temperature": 1.05,
				    "date": "2022-01-09",
				    "time": "21:00:00",
				    "temperatureMax": 2.4,
				    "temperatureMin": 1.05,
				    "feelsLike": -2.73
			    },
			    {
				    "humidity": 86,
				    "main": "Rain",
				    "description": "light rain",
				    "temperature": 2.09,
				    "date": "2022-01-10",
				    "time": "00:00:00",
				    "temperatureMax": 2.95,
				    "temperatureMin": 2.09,
				    "feelsLike": -1.29
			    },
```
			    ...
```json
		    ]
    }
```
 #
### *Esempio chiamata `/HumidityStatsTime`*

    localhost:8080/HumidityStatsTime?CityName={CityName}&Time={Time}
La rotta *'HumidityStatsTime'* permette di ottenere le statistiche sull'umidità, ovvero umidità minima, umidità massima, media e varianza, sulle previsioni meteo filtrate per fascia oraria. La rotta impiega la classe *'util.HumidityStats'* che contiene i metodi *'HumidityMaxMin()', 'HumidityAverage()', 'HumidityVariance()'* che prendono, in questo caso, come parametro il `CityObject` filtrato per fascia oraria dal metodo *'timeslot()'* della classe *'filter.Filter'* e restituiscono sotto forma di `String` i valori delle statistiche sopra elencate.
 
#### Chiamata PostMan

    maximum humidity: 71
    minimum humidity : 44
    Humidity Average: 59.8
    Humidity Variance: 8.84

 #
### *Esempio chiamata `/HumidityStatsOneDay`*

    localhost:8080/HumidityStatsOneDay?CityName={CityName}&Date={Date}
    
La rotta *'HumidityStatsOneDay'* permette di ottenere le statistiche sull'umidità, ovvero umidità minima, umidità massima, media e varianza, sulle previsioni meteo filtrate in base ad una determinata data. La rotta impiega la classe *'util.HumidityStats'* che contiene i metodi *'HumidityMaxMin()', 'HumidityAverage()', 'HumidityVariance()'* che prendono, in questo caso, come parametro il `CityObject` filtrato in base ad una determinata data dal metodo *'onedayslot()'* della classe *'filter.Filter'* e restituiscono sotto forma di `String` i valori delle statistiche sopra elencate.
 
#### Chiamata PostMan

    maximum humidity: 80
    minimum humidity : 62
    Humidity Average: 69.63
    Humidity Variance: 6.87

 #
### *Esempio chiamata `/HumidityStatsFiveDays`*

    localhost:8080/HumidityStatsFiveDays?CityName={CityName}
La rotta *'HumidityStatsOneDay'* permette di ottenere le statistiche sull'umidità, ovvero umidità minima, umidità massima, media e varianza, sulle previsioni meteo filtrate con periodicità 5 giorni. La rotta impiega la classe *'util.HumidityStats'* che contiene i metodi *'HumidityMaxMin()', 'HumidityAverage()', 'HumidityVariance()'* che prendono, in questo caso, come parametro il `CityObject` filtrato con periodicità 5 giorni dal metodo *'fivedaysslot()'* della classe *'filter.Filter'* e restituiscono sotto forma di `String` i valori delle statistiche sopra elencate.
 
#### Chiamata PostMan

    maximum humidity: 89
    minimum humidity : 43
    Humidity Average: 67.65
    Humidity Variance: 10.18

 #
### *Esempio chiamata `/ErrorCurrentForecast`*

    localhost:8080/ErrorCurrentForecast?CityName={CityName}





