# **Math Assistant Application**

## Intro
This project was created as a Technical Test for Java Basic Course 
organized by GeeksForLess Software company. Key idea is to create a 
tool that serves as a math assistant and performs such operations as 
validation of equations and their roots, saving them to a database, 
retrieving them from a database, searching for an equation by its root, etc.

## Technologies and tools used 

During development of the project were used such technologies and tools as:
* Java 17,
* Spring Boot,
* Spring Data JPA,
* MySQL,
* JUnit,
* Liquibase,
* Spring Boot Testing,
* Mockito,
* Postman.

## Controller functionalities

### _For Equation class_:
#### [POST]: save an equation
Allows to validate and then save a new equation
to the database.

#### [GET]: get all equations
Shows all equations saved in the database.

#### [GET]: get all equations by the root
Shows all equations from the database that have the indicated root saved as a valid 
solution.

#### [GET]: get all equations by the root uniqueness
Shows all equations saved in the database that have only one possible solution 
saved in the database (as opposed to multiple possible solutions).

### _For Root class_:

#### [POST]: save a root
Allows to check that a root is a valid solution for a given equation (with a 
10 ^ (-9) precision level) and then save this root to the database.

## Possible improvements
- add a check if an equation or a root is already saved in the database  
- improve methods that could return null sets by using Optional objects
- add validation for invalid data cases
