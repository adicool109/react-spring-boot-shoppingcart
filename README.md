# Spring Boot + React Shopping Cart

## About

This is a demo project for practicing Spring + React. The idea was to build some basic shopping cart web app.

It was made using **Spring Boot**, **React**, **Spring Data JPA**, **Spring Data REST**. 
Database is in memory **H2**.

## Configuration

### Configuration Files

Folder **src/resources/** contains config files for **shopping-cart** Spring Boot application.

* **src/resources/application.properties** - main configuration file. Here it is possible to change the port number.

* **frontend** contains the react js files

### Maven Wrapper

#### Using Executable Jar

You can run the JAR file:
```bash
$ java -jar target/shoppingcartapp-v1.jar
```

### Maven

#### Using the Maven Plugin

The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. 
Applications run in an exploded form, as they do in your IDE. 
The following example shows a typical Maven command to run a Spring Boot application:
 
```bash
$ mvn spring-boot:run
``` 

#### Using Executable Jar

To create an executable jar run:

```bash
$ mvn clean package
``` 

To run that application, use the java -jar command, as follows:

```bash
$ java -jar target/shoppingcartapp-v1.jar
```

To exit the application, press **ctrl-c**.

### H2 Database web interface

Go to the web browser and visit `http://localhost:8080/h2-console`

In field **JDBC URL** put 
```
jdbc:h2:mem:shoppingcartdb
```

In `/src/main/resources/application.properties` file it is possible to change both
web interface url path, as well as the datasource url.

### Bugs: to be fixed in the next release

* Login/authentication works however the redirect is not working, hence login is currently disabled
* Cart Link doesnot show the number of products added to the cart i.e. Cart(1)

### Later enhancements:

* Images of the actual product to be hosted in firebase

### Screenshots

![ui](/screenshots/mainpage.jpg?raw=true "Main")

![ui](/screenshots/search.jpg?raw=true "Search")

![ui](/screenshots/cart.jpg?raw=true "Cart")
