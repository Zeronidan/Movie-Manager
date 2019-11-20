# Movie Manager

A movie manager rental program written in Java in my ICS45J class and UCI.  Collaborated with Diana Sandil.

Technologies Used:
- Java
- JUnit

The user can add movies, discontinue movies, rent movies, return rented movies, and print the current movie
inventory.  The purpose of this project is to implement custom exceptions and efficiently test them using
JUnit.


- Lab3.java creates the Movie Manager object and runs the main program
- MovieManager.java contains all the methods to run the main program and handles all the custom exceptions
- MovieManagerUI.java is a class that contains static helper methods the Movie Manager calls (ex: getting the users command,
printing the menu, getting the renter's ID, etc)
- MovieManagerTest.java contains the JUnit test functions
- Movies.java is the class that describes movies
- Renter.java is the class that describes Renters
- All the other files are custom exceptions created for this program
