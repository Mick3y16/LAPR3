# FURPS+ Model #

## Functionality ##
-

## Usability ##
-

## Reliability ##
-

## Performance ##
-

## Supportability  ##

* The user interface language must be English.

## Others ##

** Design Constraints:** 

* Agile development practices.

* Practice an iterative and incremental software development process learnt in ESOFT and LAPR2, including the use of FURPS+ for requirements identification, GRASP design principles and unit testing.

* Application's domain and controller objects shall not inject SQL commands directly into the database.

* Database operations must be done by stored procedures and functions, and by Data Access Layer (DAL) objects (a clear application of the GRASP design principles).

* The atomicity of all database write operations should be assured, especially bulk save operations.

* Since a simulation may produce a large set of results, the storage of them in the database must be carefully implemented using bulk save strategies in order not to overload the server.

* The wiki must be in English.

** Implementation Constraints:** 

* A standalone Java application will be developed using object-oriented modelling and programming techniques.

* Results can be exported as HTML, CSV, and Gnuplot files.

* All data is imported using XML files.

* The application will have to handle multiple distance and velocity units (Km/h and m/s).

* The application must use the Oracle SGBD for data persistence.

* The fixed time step simulation is a core functionality of the application.

* The Simulation Log and the Simulation Data may be generated in HTML or CSV.

* Java Swing application.

* A layered approach must be used for the architecture (UI, Controller, Model, Data Layer).

* The strategy design pattern may be very useful, as it promotes the use of composition over inheritance.
