# Google Place Example
This project has been created for an workshop whose name is Codexist. User can get place by latitude,longitude and radius. 
If data exist in DB,  gets from DB but if data not exist, gets from google place api.


The project is based on a web service which uses the following technologies:

* Java 11
* Spring Boot
* PostgreSQL
* Maven

To solve demands of Workshop this;

1 REST API has been designed.

#REST APIs

## /place/nearby-search
method: GET

/place/nearby-search?longitude={longitude}&latitude={latitude}&radius={radius} (GET)

User can get place by latitude,longitude and radius.


# Build and Run It
After you download the project you can maven clean install and run workshop.java class

OR

you can also run it in a docker container with the commands below:
* docker build -it workshop-image .
* docker run -p 8070:8070 --name workshop workshop-image
