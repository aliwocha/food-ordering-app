# **Food Ordering App**

The app is a simulation of ordering food at the website of a restaurant. Structure of the app including data model and repositories was prepared originally by the creator of the course. My task was to develop the app so that it has following funcionalities:
* very simple but pleasant graphic layout
* menu of avaialble dishes at home page
* pages of all products, with description, photo and price
* adding products to the cart
* order page summarizing order details with "Order" button
* managing all orders from the order panel, with an option to change the status and fulfill the order

## **Technology used:**
* Java 11
* SpringBoot
* Hibernate
* H2
* HTML
* Thymeleaf
* Bootstrap

## **How to run:**
1. Clone the repository onto your own computer.

2. Go to the main folder of the project and run this command:

* for the Unix system:
```
./mvnw spring-boot:run
```
* for the Windows CMD:
```
mvnw.cmd spring-boot:run
```

3. Go to the following page in your browser to test the app: [http://localhost:8080/](http://localhost:8080/)

4. Go to the following page in your browser to see database tables: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

5. Use default values to log in to H2 database:

   JDBC URL: *"jdbc:h2:mem:testdb"*

   username: *"sa"*

   password: *"[blank]"*
