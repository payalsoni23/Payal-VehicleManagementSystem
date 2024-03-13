# Vehicle Management System

This is a simple REST API implementation to create, fetch and update vehicle data using REST endpoints.

#### url: http://localhost:8080

#### swagger-url: http://localhost:8080/swagger-ui/index.html

## VEHICLE SERVICE

This is the interface that contains all the service methods used by the controller for managing vehicle data.

### Implemented by: VehicleServiceImpl class

### Methods:

#### 1. Vehicle addVehicle(Vehicle vehicle);

- method for adding new vehicle details
- vehicle - contains the request object of type Vehicle to be added/created.
- returns - newly created Vehicle object.
- throws - InvalidVehicleException if the Vehicle with VRN provided already exists.

#### 2. Vehicle updateVehicle(String vrn, Vehicle vehicle);

- method for updating vehicle details
- vrn - VRN of vehicle for which details are to be updated
- vehicle - object of type Vehicle which contains updated details
- returns - updated vehicle object
- throws - InvalidVehicleException if the VRN provided is not found in the DB.

#### 3. List<Vehicle> getVehicles(List<String> vrnList);

- method to return list of vehicle objects corresponding to the list if vrn provided.
- vrnList - list of VRNs for which vehicle objects need to be listed.
- returns - list of vehicle objects corresponding to the VRNs provided. Returns empty list if no valid VRN is provided.
  i.e. either no VRN is provided or there is no vehicle object corresponding to provided VRNs.

## REST END POINTS

### POST MAPPING: /addVehicle

- adds new vehicle data to the database
- VRN is considered to be unique for each vehicle
- if duplicate vehicle record is provided, nothing gets added to DB.

Sample request structure:

```
{
    "vrn": "SZVT2010",
    "make": "Suzuki",
    "model": "Vitara",
    "vehicleYear": 2010,
    "fuelType": "petrol"
}
```

Sample response structure:

```
{
    "vrn": "SZVT2010",
    "make": "Suzuki",
    "model": "Vitara",
    "vehicleYear": 2010,
    "fuelType": "petrol"
}
```

curl:

```
curl --location 'localhost:8080/vehicleManagementSystem/addVehicle' \
--header 'Content-Type: application/json' \
--data '{
    "vrn": "SZVT2010",
    "make": "Suzuki",
    "model": "Vitara",
    "vehicleYear": 2010,
    "fuelType": "petrol"
}'
```

### PUT MAPPING: /updateVehicle

- updates vehicle record for given VRN along with new vehicle details to be saved.
- if VRN does not exist, no vehicle record is added/updated in DB.

Sample request structure:
query param: vrn - VRN of vehicle to be updated.
request body:

```
{
    "vrn": "HY3I2015",
    "make": "Hyundai",
    "model": "lancer",
    "vehicleYear": 2024,
    "fuelType": "hybrid"
}
```

Sample response structure:

```
{
    "vrn": "HY3I2015",
    "make": "Hyundai",
    "model": "lancer",
    "vehicleYear": 2024,
    "fuelType": "hybrid"
}
```

curl:

```
curl --location --request PUT 'localhost:8080/vehicleManagementSystem/updateVehicle?vrn=HY3I2015' \
--header 'Content-Type: application/json' \
--data '{
    "vrn": "HY3I2015",
    "make": "Hyundai",
    "model": "lancer",
    "vehicleYear": 2024,
    "fuelType": "hybrid"
}'
```

### GET MAPPING: /find

- returns a list of vehicles corresponding to the VRN list provided.
- VRNs with no corresponding records found will be logged.
- empty list is returned in case no valid VRN is provided.

Sample request structure:
query params: vrnList - list of VRNs of vehicles to be fetched.
example: VAND2019,NOLO2022,HY3I2015

Sample response structure:

```
[
    {
        "vrn": "HY3I2015",
        "make": "Hyundai",
        "model": "i30",
        "vehicleYear": 2015,
        "fuelType": "petrol"
    },
    {
        "vrn": "NOLO2022",
        "make": "Volkswagen",
        "model": "polo",
        "vehicleYear": 2022,
        "fuelType": "diesel"
    },
    {
        "vrn": "VAND2019",
        "make": "Vauxhall",
        "model": "crossland",
        "vehicleYear": 2019,
        "fuelType": "petrol"
    }
]
```

curl:

```
curl --location 'localhost:8080/vehicleManagementSystem/find?vrnList=VAND2019%2CNOLO2022%2CHY3I2015'
```

## Database

### url:http://localhost:8080/h2-console

This application uses H2 in-memory database. However, it can easily be switched to use some other database by setting
below properties in application.properties files.

- spring.datasource.driverClassName=org.h2.Driver
- spring.datasource.username=sa
- spring.datasource.password=password
- spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

Initial data is being loaded from data.sql file which includes INSERT statements for inserting data into vehicles table.
If data is to be loaded from a file rather then SQL queries, below property can be set in properties file with the file
path.

- spring.datasource.url=jdbc:h2:file:<file path>