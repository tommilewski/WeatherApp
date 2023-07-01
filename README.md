# WeatherApp
This application is designed to help you find the best location for surfing. It provides weather and location data to determine the most suitable surfing spots based on specific criteria.

The WeatherApp application is written in Java 17 and uses Maven for dependency management and building the project.

## Running the Application
To run the application, follow these steps:
- Clone the WeatherApp repository.
- Open the project in your favorite Integrated Development Environment (IDE).
- Build the project using Maven or run the command `mvn clean install` in the project's root directory.
- Run the application by executing the class with the main method or using the command `java -jar <jar-file-name>`.

## Running the Tests

To run the tests, follow these steps:
- Make sure you have Java 17 and Maven installed.
- Clone the WeatherApp repository if you haven't done so already.
- Open a terminal or command prompt and navigate to the project's root directory.
- Run the command `mvn test` or `mvn verify` to execute unit and integration tests.
- Using the Application

## To use the WeatherApp application, follow these instructions:
- Clone the WeatherApp repository if you haven't done so already.
- Start the application following the instructions mentioned above.
- Use a tool like Postman or any other HTTP request tool.
- Send a GET request to http://localhost:8080/api/v1/location/best.
- Pass the date as JSON in the request body.

### Example HTTP request to the WeatherApp application:

```
GET /api/v1/location/best
Content-Type: application/json

{
  "date": "2023-07-01"
}
```
