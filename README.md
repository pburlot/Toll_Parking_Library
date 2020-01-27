# Toll_Parking_Library
Microservice managing parking tolls.

# How to use

### Required
In order to build and execute the program:
* Java JDK (> 1.8)
* Maven (version used 3.6.3)

### Used
Frameworks/Libraries used:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Swagger](https://swagger.io/)
* [JUnit](https://junit.org/junit5/)

## Build
```
mvn clean install
```
## Execute
```
java -jar target\tollparking-0.0.1-SNAPSHOT.jar
```

## REST API documentation
API documentation generated by Swagger:

* [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs) Documentation of the microservice in JSON format.
* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) Documentation of the microservice in HTML format.

## Usage

### /AddParking POST
Create a parking

##### Model
```
{
  "id": 0,
  "name": "string",
  "nbCarParkingSlotsPerType": [
    {
      "nbSlot": 0,
      "slotType": "string"
    }
  ],
  "policy": {
    "fixedAmount": 0,
    "hourlyAmount": 0
  }
}
```
  
`slotType` can be: `Standard` , `Electric_20kw` or `Electric_50kw`


##### Using Swagger HTML UI
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) -> method /AddParking

##### cURL
```
curl -X POST "http://localhost:8080/AddParking" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"id\": 0,  \"name\": \"Parking1\",  \"nbCarParkingSlotsPerType\": [    {      \"nbSlot\": 50,      \"slotType\": \"Standard\"    },    {      \"nbSlot\": 15,      \"slotType\": \"Electric_20kw\"    },    {      \"nbSlot\": 5,      \"slotType\": \"Electric_50kw\"    }  ],  \"policy\": {    \"fixedAmount\": 1.95,    \"hourlyAmount\": 2.5  }}"
```


### /Parkings GET
Display the list of parkings. For each parking, the parking id, the parking name, the parking policy and the number of slots per type of car is displayed

##### On a browser
[http://localhost:8080/Parkings](http://localhost:8080/Parkings)

##### cURL
```
curl http://localhost:8080/Parkings
```


### /Parkings/{id} GET
Display the detail of a parking. the parking id, the parking name, the parking policy and the details of each slots are displayed

`id` is one of the id of a parking previously added

##### On a browser
[http://localhost:8080/Parkings/0](http://localhost:8080/Parkings/0)

##### cURL
```
curl http://localhost:8080/Parkings/0
```


### /Parkings/{id}/ParkCar POST
Park a car in a specific parking

##### Model
```
{
  "typeOfCar": "string"
}
```
  
`typeOfCar` can be: `Standard` , `Electric_20kw` or `Electric_50kw`


##### Using Swagger HTML UI
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) -> method /Parkings/{id}/ParkCar

##### cURL
```
curl -X POST "http://localhost:8080/Parkings/0/ParkCar" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"typeOfCar\": \"Standard\"}"
```
