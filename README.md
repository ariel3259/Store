# Store

An api rest about store made with Spring boot  and MySQL

## Requirements:

 ### Envoriment: 
 
 - Java 11.
 
 - maven 3.8.4.
 
 ### Dependencies: 
    
   - spring-boot-starter-data-jpa.
   
   - spring-boot-starter-web.
   
   - mysql-connector-java.
   
   - lombok.
   
   - spring-boot-starter-test.

   - jbcrypt 0.4 .
   
   - fusionauth-jwt 5.0.0 .
   
   - junit 4.13.2

## Install dependencies

  - mvn spring-boot:run
 
## Routes:

 ### Users: 
    
   - POST http://localhost:8000/api/users/register : Register an user. Needs a name(string), a lastName(string), a dni(string), an email(string), an password(string) by body request.
 Returns a json object.
 
   - POST http://localhost:8000/api/users/auth : Authentificates an user. Needs an email(string) and a password(string) by body request. Returns a json object.

 ### Products:
  
  - GET http://localhost:8000/api/products : Get All products by user and state. Needs a token(authorization), subject(int), dni(string) and a state(boolean) by header request.
 Returns all products of user.
   
  - POST http://localhost:8000/api/products : Saves a product. Needs a token(authorization), a subject(int) and a dni(string) by header request, and name(string),
 description(string), price(float) and stock(int) by body request. Returns a json object.
 
  - PUT http://localhost:8000/api/products : Modifies a porduct. Needs a token(authorization) and a subject(int), and a name(string), a description(string), a price(float),
 a stock(int) and an id(int) by body request. Returns a json object.
  
  - DELETE http://localhost:8000/api/proucts : Deletes a product. Needs a token(authorization), a subject(int) and an id(int) by header request. Returns a json object.
  
  ### Customers:
   
  - GET http://localhost:8000/api/customers : Get all customers of the user. Needs token(authorization), a subject(int), and a dni(string) by header request.
 Returns all customers of the user.
 
  - POST http://localhost:8000/api/customers : Save a customer. Needs token(authorization), a subject(int), a dni(string) and a product_name(string) by header request, and 
  a customerName(string) and items(int). Returns a json object.
  
  - PUT http://localhost:8000/api/customers : Modifies state(customer properties). Needs an token(authorization), a subject(int) and an id(int) by header request. 
 Returns a json object.
 
 ## Copyright: ariel3259
    

