# Vehicle Management System

This is a simple REST API implementation to create, fetch and update vehicle data using REST endpoints.

## REST END POINTS

### POST MAPPING: /addVehicle

- adds new vehicle data to the database
- VRN is considered to be unique for each vehicle
- if duplicate vehicle record is provided, nothing gets added to DB.

Sample request structure:
{
"vrn":"SZVT2010",
"make":"Suzuki",
"model":"Vitara",
"vehicleYear": 2010,
"fuelType":"petrol"
}

Sample response structure:
{
"vrn": "SZVT2010",
"make": "Suzuki",
"model": "Vitara",
"vehicleYear": 2010,
"fuelType": "petrol"
}

curl:
curl --location 'localhost:8080/vehicleManagementSystem/addVehicle' \
--header 'Content-Type: application/json' \
--data '{
"vrn":"SZVT2010",
"make":"Suzuki",
"model":"Vitara",
"vehicleYear": 2010,
"fuelType":"petrol"
}'

### PUT MAPPING: /updateVehicle

- updates vehicle record for given VRN along with new vehicle details to be saved.
- if VRN does not exist, no vehicle record is added/updated in DB.

Sample request structure:
query param: vrn - VRN of vehicle to be updated.
request body:
{
"vrn": "HY3I2015",
"make": "Hyundai",
"model": "lancer",
"vehicleYear": 2024,
"fuelType": "hybrid"
}

Sample response structure:
{
"vrn": "HY3I2015",
"make": "Hyundai",
"model": "lancer",
"vehicleYear": 2024,
"fuelType": "hybrid"
}

curl:
curl --location --request PUT 'localhost:8080/vehicleManagementSystem/updateVehicle?vrn=HY3I2015' \
--header 'Content-Type: application/json' \
--data '{
"vrn": "HY3I2015",
"make": "Hyundai",
"model": "lancer",
"vehicleYear": 2024,
"fuelType": "hybrid"
}'

### GET MAPPING: /find

- returns a list of vehicles corresponding to the VRN list provided.
- VRNs with no corresponding records found will be logged.
- empty list is returned in case no valid VRN is provided.

Sample request structure:
query params: vrnList - list of VRNs of vehicles to be fetched.
example: VAND2019,NOLO2022,HY3I2015

Sample response structure:

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

curl:
curl --location 'localhost:8080/vehicleManagementSystem/find?vrnList=VAND2019%2CNOLO2022%2CHY3I2015'
