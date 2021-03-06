# MyWeather
***AUTORI <br>
Davide Santurbano <br>
Giampaolo Marino***

## DESCRIZIONE GENERALE
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

## INTRODUZIONE ROTTE
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

## SPIEGAZIONE ROTTE
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

	   ]


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

		      ]
        }

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

		    ]
    }

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

		    ]
    }

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

		    ]
    }

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

		    ]
    }

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
La rotta *'/ErrorCurrentForecast'* permette di calcolare l'errore rispetto alla temperatura, temperatura minima, temperatura massima, temperatura percepita e l'umidità tra l'API *'forecast 5 day / 3 hour'* e l'API *'current weather data'* in un determinato giorno e orario. Tale rotta impiega la classe *'util.ErrorForecastCurrent'* che mette a disposizione il metodo *'ErrorCalculator()'* che prende come parametri due `CityObject`: 

 - Il `CityObject` contenente le informazioni ottenute dall'API `'weather data current'.` Questo `CityObject` è ottenuto dal parsing del file *'CityNamecurrent.txt'* tramite il metodo *'FileCurrentParse()'* della classe *'service.JSONFileCurrentParser'*.
 - Il `CityObject` contenente le informazioni ottenute dall'API *'forecast 5 day / 3 hour'.* Questo `CityObject` è ottenuto dal parsing del file *'CityName.txt'* tramite il metodo *'FileParse()'* della classe *'service.JSONFileParser'*.

I valori che otteniamo possono essere sia negativi che positivi:

 - Nel caso i valori ottenuti siano positivi, significa che tali valori del  *'forecast 5 day / 3 hour'* sono maggiori rispetto ai valori del  *'current weather data'*. 
 - Nel caso i valori ottenuti siano negativi, significa che tali valori del  *'forecast 5 day / 3 hour'* sono minori rispetto ai valori del  *'current weather data'*.
 
#### Chiamata PostMan

    error between forecast and current temperature: 0.86
    error between forecast and current maximum temperature: -2.0
    error between forecast and current minimum temperature :3.2
    error between forecast and current felt like temperature: 0.0
    error between forecast and current humidity: -15.0

## ORGANIZZAZIONE DEL PROGETTO
Il progetto è stato implementato seguendo il pattern architetturale MVC (Model-View-Controller) fortemente diffuso nell'ambito delle applicazioni web e della OOP. Questa architettura si basa su tre componenti che sono appunto: 
 - *Model*: si occupa della gestione dei metodi per accedere ai dati ed alle informazioni necessarie per l'applicazione;
 - *View*: si occupa di visualizzare i dati contenuti nel model e dell'interazione con l'utente;
 - *Controller*: si occupa di ricevere i comandi dall'utente ed ha il compito di attuarli modificando lo stato degli altri due componenti.

Questa architettura si appoggia inoltre su framework differenti relativamente al linguaggio necessario per la stesura di un determinato applicativo come ad esempio *Symfony* o *Laravel* per PHP, *Django* o *TurboGears* per Python e, nel nostro caso *Spring* per Java.
Quest'ultimo è un framework open source appunto utilizzato in Java che supporta anche altri progetti come ad esempio Spring Boot e Spring Tools che sono stati impiegati all'interno del progetto. Sping Boot ha 

#
### *PACKAGES*
Nel nostro caso, il progetto è stato implementato impiegando i seguenti packages e seguendo appunto l'architettura MVC; tutti i pacchetti sotto descritti sono contenuti nella destinazione '*Progetto-OOP-Marino-Santurbano.src.mai.java.it.univpm*':
#### *Controller*
Questo pacchetto contiene la classe *WeatherController* contenente a sua volta tutte le rotte necessarie ad attuare tutto ciò che l'utente richiede attraverso di esse.
La seguente è la rotta */getCurrentWeather* descritta in precedenza.

    	@GetMapping("/getCurrentWeather")
	public ResponseEntity<Object> getForecastCurrent(@RequestParam (value = "CityName", defaultValue = "Ancona") String CityName) throws ParseException, FileNotFoundException, IOException{		
		try {
			return new ResponseEntity<>(JSONFileCurrentParser.FileCurrentParse(CityName), HttpStatus.OK);
		}catch (FileNotFoundException e) {
			return new ResponseEntity<>("Invalid City Name", HttpStatus.OK);
		}
	}
#
#### *Database*
Questo pacchetto contiene le classi '*WeatherDatabase*' e '*WeatherDatabaseCurrent*' che hanno il compito, attraverso due metodi, di instaurare la connessione con le APIs impiegate.
La prima fa riferimento all'API *forecast 5 day/3 hours* per la successiva rilevazione di informazioni riguardanti le previsioni meteo per i 5 giorni successivi alla chiamata con una frequenza di 3 ore mentre la seconda fa riferimento all'API *weather current data* per la successiva rilevazione di informazioni riguardanti le previsioni meteo dell'orario corrente alla chiamata.

    URLConnection OpenConnection = new URL(url+City + "&units=metric&appid=" + APIkey).openConnection();
Riga di codice con la quale viene instaurata la connessione servendoci dei packages '*java.net.URL*' e '*java.net.URLConnection*'.
#
#### *Exceptions*
Questo pacchetto contiene le classi '*CityNotFoundException*', '*DateNotFoundException*' e '*TimeNotFoundException*' che hanno il compito, attraverso dei metodi, di fornire un messaggio di errore nei rispettivi casi di: inserimento errato di nome di città, inserimento di data non valida e inserimento di orario non valido.
#
#### *Filter*
Questo pacchetto contiene la classe '*filter*' che ha il compito di eseguire i filtraggi sulle operazioni che l'applicazione web è in grado di compiere. I tipi di filtraggio sono 3:

 - *timeslot*: questo filtro permette di visualizzare le informazioni filtrate realtivamente ad un determinato orario;
 - *onedayslot*: questo filtro permette di visualizzare le informazioni filtrate relativamente ad un determinato giorno;
 - *fivedayslot*: questo filtro permette di visualizzare le informazioni filtrate per i 5 giorni seguenti alla chiamata con la frequenza di 3 ore.
#
#### *Model*
Questo pacchetto contiene le classi '*City*', '*Forecast*', '*Temperature*' e '*Time*' che hanno il compito di definire le informazioni ottenute a seguito delle chiamate alle APIs.  
La classe '*City*' contiene le informazioni che definiscono le città interessate dall'utente e le classifica con *id, cityname* e *citycountry*.
La classe '*Forecast*' contiene le informazioni che definiscono le previsioni meteo delle città interessate dall'utente e le classifica con *humidity, main* e *description*. Questi ultimi due campi fanno riferimento al contenuto degli stessi presenti nei JSONObject ottenuti dalla chiamata.
La classe '*Temperature*' contiene le informazioni relative alla temperatura delle città interessate e le classifica con *tempereture, temperaturemin, temperaturemax* e *feelslike*. Questa classe è una derivata della classe '*Forecast*' perchè è un'estensione delle previsioni meteo ottenute a seguito della chiamata.
La classe '*Time*' contiene le informazioni relative a orario e data delle città interessate e le classifica con *time* e *date*. Questa classe è una derivata della classe '*Temperature*' perchè permette in questo modo di tenere conto di orario e data della rispettiva previsione meteo.
#
#### *Service*
Questo pacchetto contiene le classi '*DateCalculator*', '*JSONFile*', '*JSONFileCurrent*', '*JSONFileCurrentParser*', '*JSONFileParser*' e '*JSONParse*'.
La classe '*DateCalculator*' dispone di un metodo che si occupa di trasformare l'epoca letta dal JSONObject al tipo LocalDateTime.
La classe '*JSONFile*' dispone di un metodo che si occupa della creazione di un JSONObject a partire da un oggetto di tipo '*City*' ottenuto dopo l'operazione di parsing.
La classe '*JSONFileCurrent*' dispone di un metodo che si occupa della creazione di un JSONObject attraverso le relative richieste ricavato dal metodo `getForecastCurrent()` appartenente alla classe '*WeatherDatabaseCurrent*'.
La classe '*JSONFileCurrentParser*' dispone di un metodo che si occupa di effettuare il parsing delle informazioni che vengono lette dal file 'citynamecurrent.txt' contenente tutte le informazioni provenienti dalla chiamata all'API '*current weather data*' e si occupa in seguito della creazione di un oggetto di tipo City.
La classe '*JSONFileParser*' dispone di un metodo che si occupa di effettuare il parsing delle informazioni che vengono lette dal file 'cityname.txt' contenente tutte le informazioni provenienti dalla chiamata all'API '*forecast 5 day/ 3 hours*' e si occupa in seguito della creazione di un oggetto di tipo City.
la classe '*JSONParse*' dispone di un metodo che si occupa di effettuare il parsing direttamente dalla chiamata dell'API '*forecast 5 day/ 3 hours*' e della creazione di un oggetto di tipo City.
#
#### *Util*
Questo pacchetto contiene le classi '*ErrorForecastCurrent*' e '*HumidityStats*' che dispongono di metodi che si occupano rispettivamente di:
 - calcolare l'errore tra le informazioni fornite dalle due API (*forecast 5 day / 3 hour* e *current weather data*) rispetto a temperatura, temperatura massima, temperatura minima, temperatura percepita e umidità relativamente ad una determinata data ed un determinato orario;
 - calcolare le statistiche relative ai valori di umidità rilevati (permette di calcolare il massimo, il minimo, la media e la varianza).
## *NOTE AGGIUNTIVE*
#### *File*
I file presenti nel GitHub denominati 'Ancona.txt' e 'Anconacurrent.txt' sono un esempio dei file descritti in precedenza con il nome di 'CityName.txt' e 'CityNameCurrent.txt' e contengono rispettivamente:
 - '*Ancona.txt*' contiene le informazioni riguardanti le previsioni meteo fornite dalla chiamata all'API 'forecast 5 day/ 3 hour' effettuata il giorno 2022-01-09 alle ore 19:14. Nel file, la prima fascia oraria che si può visualizzare, è quella delle 21 perchè OpenWeather fornisce il meteo (servendosi di questa API) per ogni 3 ore a partire dalle 00:00; di conseguenza, la prima fascia oraria risulta la sopracitata avendo effettuato la chiamata alle 19:14. Ad esempio, per ottenere le previsioni meteo per la fascia oraria precedente (in questo caso si fa riferimento a quella delle 18:00), avremmo dovuto effettuare la chiamata prima di quel momento.
 - '*Anconacurrent.txt*' contiene le informazioni riguardanti le previsioni meteo fornite dalla chiamata all'API 'current weather data' effettuata il giorno 2022-10-09 alle ore 19:14 (convertito dal formato epoch fornito da OpenWeather) e delle successive 21 ore. Questo perchè, a differenza del file descritto in precedenza, l'API corrente fornisce le previsioni meteo del momento in cui viene effettuata la chiamata e, attraverso un timer settato ad 1 ora, sono state raccolte nel file le previsioni successive alla prima fino alle 16:18 (convertito dal formato epoch).
#
#### *Test*
Al fine di testare il programma sono stati effettuati dei test attraverso il framework *JUnit* sulle classi '*WeatherController*' e '*DateCalulator*'. Lo scopo è quello di testare l'esatto funzionamento delle singole unità (metodi e classi) con il fine di ridurre ed eliminare possibili errori dell'applicazione. Nella classe '*WeatherControllerTest*' sono stati testati i seguenti metodi: '*saveFile()*', '*HumidityStatsTime*', '*HumidityStatsOneDay*' e '*HumidityStatsFiveDay*' con dei metodi relativi ai precedenti che richiamano e ne verificano il corretto funzionamento attraverso la resituzione di una stringa. Di seguito un esempio:

    @Test
    @DisplayName("Correct Humidity Stats Five Days")
    void HumidityStatsFiveDayControl() throws FileNotFoundException, ParseException, IOException {
	    String cityname = "Ancona";
	    ResponseEntity<String> response= new ResponseEntity<>("maximum humidity: 89\nminimum humidity : 43\nHumidity Average: 67.65\nHumidity Variance:10.18", HttpStatus.OK);
	    assertEquals(controller.HumidityStatsFiveDays(cityname),response);
    }




