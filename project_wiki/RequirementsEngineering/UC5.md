# UC5: Create Simulation #
## Brief Format ##
The user starts the creation of a new simulation. The system requests the path to the file which contains the data. The user inserts the path. The system loads and saves the simulation and informs the user of the success of the operation.

## SSD ##
![SSD.jpg](https://bitbucket.org/repo/pqBqye/images/2300589439-SSD.jpg)

## Complete Format ##
## Main actor ##
User

## Stakeholders and their interests ##
User: Easy and quick creation of a new simulation, including the addition of car traffic in different intersection points.

## Preconditions ##
The existence of an open project

## Postconditions ##
* The simulation is created with the respective project and vehicle traffic.

## Main Success Scenario (or Basic Flow ) ##
1.	The user starts the creation of a new simulation.
2.	The system asks the path to the file which contains the data.
3.      The user inserts the path.
4.     The system validates and saves the inputted data and informs the user of the success of the operation.

## Extensions ( or alternative flows ) ##

*a. The user cancels the creation of the simulation. 

* The use case ends.

4a. The system detects that the simulation's name already exists.

* The system warns the user to the fact.

* The systems allow the edition of the simulation's name.

4 a. The system detects absence of data.

* The system warns the user to the fact.

* The system allows the reintroduction of file path (step 3).


## Special requirements ##
-

## Technology and Data Variation List ##
-

## Frequency of Occurrence ##
-

## Open Questions ##