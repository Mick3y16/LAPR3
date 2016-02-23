# UC2: Create Project#
## Brief Format ##
The user starts the creation of a new project. The system requests the path to the file which contains the data. The user inserts the path. The system loads project and informs the user of the success of the operation.

## SSD ##
![SSD.png](https://bitbucket.org/repo/pqBqye/images/647999346-SSD.png)

## Complete Format ##
## Main actor ##
User

## Stakeholders and their interests ##
User: Ease in creating a new project.

## Preconditions ##
-

## Postconditions ##
The project is created and ready to be used.

## Main Success Scenario (or Basic Flow ) ##

1. The user starts the creation of a new project.
2. The system asks the path to the file which contains the data.
3. The user inserts the path.
4. The system loads the project from the file, validates, saves it and informs the user of the success of the operation.


## Extensions ( or alternative flows ) ##

*a. The user requests the cancellation of the creation of a project.

* The use case ends 

4a. The system detects that the imported file's data structure is not valid.  

* The system warns about that fact and allows the change.

      1a. The user doesn’t change anything. The use case ends.

## Special requirements ##
-To be valid, a project must contain a road with at least one segment and a vehicle.

## Technology and Data Variation List ##
* e**X**tensible **M**arkup **L**anguage (**XML**)

## Frequency of Occurrence ##
-

## Open questions ##