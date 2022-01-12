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
        ...
        
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


