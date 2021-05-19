# Phone cases

This is a proof of concept, learning experience and not intended for production
It is my first time setting up spring boot from scratch,
and the last time I did Java as my full time job it was Sun who did the Java certifications.

## Tech stuff
- A spring boot application using postgresSQL.
- Dependencies and building is done with Gradle.
- Lombok is used to generate scaffolds as getters and setters.
- Spring boot is used for dependency injection.
- JPA annotations define the database structure.

## Architecture
The system is build with TDD and hexagonal design (ports and adapters). 
A lot in Spring Boot is mostly about making sure that everything is connected properly.
Therefore, I started with an integration test. 

### Hexagonal design
When writing testable code, it is a challenge to interact with complex libraries.
Code tightly interacting with a web framework, however light weight, is hard to test. 
A way to solve this is to wrap the interaction with the services that the framework provides in interfaces. 
Those interfaces are then implemented with small classes that do not contain any conditionals.
The business logic can be decoupled from the framework and written test driven.
The only test doubles needed are for the small ports toward the database and the web.
Those are kept small and simple, and are therefore easy to fake. 
It results in a lot of files, but most of them will only be touched once, 
and the naming should be clear enough that looking at the list of files should tell what is in inside them.

#### Example: List all cases that fit a phone
1. The PhoneCasesController is configured to receive the get request. 
   It sends the request to an autowired object confirming to the FindCasesByPhoneNameFacade interface. 
   This only has one method findCaseByPhone that take a name of a phone model as a parameter and returns a list of PhoneCases.
   
2. FindCasesByPhoneNameService implements FindCasesByPhoneNameFacade. 
   It relies on two ports toward the database layer to perform its logic. 
   First it has to find the phone with the name provided using FindPhoneByName,
   and then ask the data layer for all phone cases that fit that phone, FindCasesByPhone.
   This class does not know anything about how the database is built. 
   It only has one interface that can provide a phone with a given name
   and another that can list all cases that fit that phone.
   
3. FindPhoneByNameAdapter is a straight forward wrapper around the PhoneRepository.
   FindCasesByNameAdapter knows a bit more about the database layout, 
   as it knows that there is a PhoneShape connected to each phone, 
   and that it in turn determines what phone cases fit and not. 
   It can still be done without breaking the Law of Demeter.
   
4. The result is then passed back through the chain.

### Data decoupling
The parameters taken for creating a Phone or a PhoneCase does not know about the underlying database structure. 
PhoneShapes allow for a PhoneCase to be tied to multiple Phones if they are said to have the same shape.
To avoid exposing this implementation detail to the world,
there are parameter objects that contain only the parts that the consumer of the API need to know about.

## TODO
- More linting rules
- Better consistency in naming. PhoneCase vs Case.
- Making sure that the edge-cases are in place in the services
- Make it proper REST with hyperlinks
- API-versioning
- Check that it works with an empty database
- Use some kind of web tool to check that the things that work in the integration test also works in a real world scenario.
- Add more information to the phones and to the phone cases
- Containerise it, so that no db is needed to be installed to test it.
- Consider splitting persistence objects and domain objects to separate classes.
