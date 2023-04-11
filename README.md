Rewards Program API

This is a Spring Boot application that provides a RESTful API for calculating reward points earned by customers based on their transactions.

 
Table of Contents
1. Requirements
2. Getting Started
3. Usage
4. Generate random transaction data
5. Get reward points earned by customer
6. Get total reward points earned by customer

Contributing
 

Requirements
1. Java 8 or higher
2. Maven

Getting Started
Clone the repository:

git clone https://github.com/your-username/rewards-program.git

Navigate to the project directory:
cd rewards-program

Build the application:
mvn clean install

Run the application:
mvn spring-boot:run

Access the API documentation:
http://localhost:8080/swagger-ui.html

Usage
Generate random transaction data

To generate random transaction data for multiple customers over a three-month period, run the following command:

curl -X POST http://localhost:8080/transactions/generate

Get reward points earned by customer
To get the reward points earned by a customer for a specific month, make a GET request to the following endpoint:

sql
GET /transactions/rewards?customerId={customerId}&month={month}
Replace {customerId} with the ID of the customer and {month} with the month (1-3).

Example:
sql
GET /transactions/rewards?customerId=123&month=1

Get total reward points earned by customer
To get the total reward points earned by a customer for all three months, make a GET request to the following endpoint:

 

GET /transactions/rewards/total?customerId={customerId}
Replace {customerId} with the ID of the customer.
Example:

GET /transactions/rewards/total?customerId=123

Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

sample json request and responses
GET /transactions/rewards?customerId=123&month=1
Query Parameters:

customerId: ID of the customer for whom to retrieve the reward points (e.g., 123)
month: Month (1-3) for which to retrieve the reward points (e.g., 1 for January)
Response
Status Code: 200 OK

json
{ "customerId": 123, "month": 1, "rewardPoints": 90 }
Request
json
GET /transactions/rewards/total?customerId=123
Query Parameters:

customerId: ID of the customer for whom to retrieve the total reward points (e.g., 123)
Response
Status Code: 200 OK

json

{ "customerId": 123, "totalRewardPoints": 250 }
Note: The above sample JSON request and response are just examples and the actual API implementation may differ based on your specific Spring Boot application setup and data model.



