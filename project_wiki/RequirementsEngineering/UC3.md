# UC3: Copy project#
## Brief Format ##
The user starts the copy of the open project. The system copies and requests the name and the descriptions for the copied project. The user enters a name and description for the project. the system informs the user of the success of the operation.

## SSD ##
![SSD.jpg](https://bitbucket.org/repo/pqBqye/images/3195592988-SSD.jpg)

## Complete Format ##
## Main actor ##
User

## Stakeholders and their interests ##
User: Copy a project and perform changes on it without changing/modifying the original.

## Preconditions ##
-

## Postconditions ##
The project is copied and ready to be worked on.

## Main Success Scenario (or Basic Flow ) ##

1. Initializes the copy of open project;
2. The system copies and requests the name and the descriptions for the copied project;
3. The user enters a name and description for the project.
4. The system requests confirmation of the copy process.
5. The user confirms.
6. The system validates and reports the success of the operation.

## Extensions ( or alternative flows ) ##

 a. The user cancels the copy of a project.

* The use case ends 

5a. The user does not confirm.

* The system informs the user of the fact.

* The system allows the user to change the description and name of the project.

6a. the system detects that the entered name already exists.

* The system warns the user of the fact and asks the user to re-enter

* The user does not modify the name.

* The use case ends 

## Special requirements ##
-

## Technology and Data Variation List ##
-

## Frequency of Occurrence ##
-

## Open Questions ##

-