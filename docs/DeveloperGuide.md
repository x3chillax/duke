# Duke - Developer Guide

1. Setting Up

2. Design 

   1. Architecture
   2. UI
   3. Command Component
   4. Parser Component
   5. Storage Component
   6. Task Component
   7. Exception Component
   8. Recipebook Component
   9. RecipeCommand Component
   10. Order Component
   11. OrderCommand Component
   12. Fridge Component
   13. GenericList
   14. Ingredient
   15. ingredientCommand Component
   16. Statistics

3. Implementation

4. Documentation

5. Testing

6. Dev Ops

   Appendix A: Product Scope

   Appendix B: User Stories

   Appendix C: Use Cases

   Appendix D: Non Functional Requirements 

   Appendix E: Glossary 

   Appendix F: Instructions for Manual



### 1. Setting Up

- see the Guide [Here]() 

### 2. Design 

#### 2.1  Architecture

![architecture]( https://github.com/AY1920S1-CS2113-T14-2/main1/blob/master/docs/images/archh.png )

`main` has 1 class called `Duke`. It is responsible for,

- at app launch: Loads all the data in storage into the application, initialize the  components, reads the commands and executes them correctly.
- at shut down: shuts down all component and exits the application

The Application consist of 11 other components 

- `command`: executes the command that is read from the user
  - `dishesCommand`
  - `orderCommand`
  - `ingredientCommand`
- `exception`: handle error messages
- `dish`: contains the dishlist as well the the dish class 
- `fridge`: contains 
- `ingredient`: contains ingredient list 
- `list`: a class which contains a generic list. this list is used by various other classes
- `order`:  
- `parser`: determine the next course of action from the user command
- `storage`: Reads, writes data from and to the hard disk
- `task`: stores a list of deadline/event/todo that needs to be done
- `ui`: The UI of the application

![sequence]( https://github.com/AY1920S1-CS2113-T14-2/main1/blob/master/docs/images/UMLsequence.png )

The diagram above shows the program sequence when the user enters the command `todo buy eggs` or `list`. 

#### 2.2 UI


API: `Ui.java`

The Ui class consists of methods that outputs messages to the user as a response when the user enters a certain command.

The Ui component contains all the messages or replies whenever the User enters a command for example if the user enters:

`dishadd chicken rice /num 2`

`dishdelete 1`

The Ui will reply to the User with the following messages:

```
	 ____________________________________________________________
	 you have added the following dish: 
	 chicken rice 	amount: 2
	 ____________________________________________________________
```

```
	 ____________________________________________________________
	 The following dish have been removed:
	 chicken rice
	 ____________________________________________________________
```
the ui also consist of templates for the different sections of the program, such as a template for dish, orders and ingredient

<u>ingredient</u> 

```
         _________________________________________________________________________________________
         Continue by adding, removing or using an ingredient
         Template:
         _________________________________________________________________________________________
         add <Ingredient name> <amount> <expiry date: DD/MM/YYYY>
         remove <ingredient number>
         use <ingredient name> <amount> *always use most recently expiring ingredients first, to prevent food waste!*
```

<u>order</u>

```
         _________________________________________________________________________________________
         Continue by adding, removing, altering, listing order and initializing order list.
         Command Template:
         _________________________________________________________________________________________
         init
         add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT]
         alter ORDER_INDEX ORDER_DATE-(dd/mm/yyyy)
         remove ORDER_INDEX
         done ORDER_INDEX
         list [-l LIST_TYPE-(option: all (default) | undone | today | undoneToday)]
         list -n DISH_NAME    *** Find the dishes in today's undone orders ***
         list -d ORDER_DATE-(dd/mm/yyyy) [-l LIST_TYPE-(option: all (default) | undone)]
         _________________________________________________________________________________________
```

<u>Dish</u>

```
         _________________________________________________________________________________________
         Continue by adding, removing, listing, adding ingredient and initializing
         Template:       _________________________________________________________________________________________
         add <dish name>
         remove <dish index>
         list
         ingredient <description> <amount> <index>
         initialize (REMOVES all entries in the list)
         back, return to main menu
         template     _________________________________________________________________________________________
```

The Ui class consists of methods that outputs messages to the user as a response when the user enters a certain command

- reads and return s user input using `scanner.nextLine()`
- outputs messages to the user as a response such as `AddDishCommand ` `DeleteDishCommand`, etc

#### 2.3 Command Component

API: `Command.java`

In the project, it has three types of commands: Ingredient Command, Dishes Command, Order Command. The three types of commands are packaged separately.

The Command class is used as an abstract class for other classes, its method `execute` is also declared as an abstract method. that is used by the following classes 

- DishCommand
  - AddDishCommand
  - DeleteDishCommand
  - ListDishCommand
  - InitCommand
  - AddIngredient
- OrderCommand
  - AddOrderCommand
  - AlterOrderCommand
  - DeleteOrderCommand
  - DoneOrderCommand
  - ListOrderCommand
  - InitOrderListCommand
- IngredientCommand
  - AddCommand
  - DeleteCommand
  - ExitCommand
  - FindIngredientCommand
  - FindToday
  - ListCommand
  - RemoveAllExpired
  - UseCommand
  - ViewCommand

each of the above class has its own implementation of the `execute` method

#### 2.4 Parser Component

API: `Parser.java`

makes sense of the data that is read by the user from the Duke Class. 

this component gets the command from the user through the Duke Class. This component will then make sense of the command by splitting the command into different parts as well as determining the command type.

depending on the content of the splitted value and command type, Parser class will execute different commands.
#### 2.5 Storage Component

API: `Storage.java` 


This component  stores entries in a certain format, tasks, ingredients that are already in the Fridge, and anything else that needs to be saved on the hard disk.

It is modelled as an abstract class,  with `TaskStorage.java` and `FridgeStorage.java` both inheriting from it. It allows data (tasks in the list, ingredients in the fridge, recipes in the recipe Book...) to be saved and remembered by our program.  

An example for the format of saving for tasks is :

- milk|3|09/09/2019
- cheese|4|12/12/2019
- rice|50|12/12/2019
- potato|3|12/2/2020

where the first column is denotes the ingredient, followed by amount and expiry date.

The program can `load` or `generate` an entry from the storage and also `changeContent` and `addInFile`


![Storage](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/StorageUML1.png)


#### 2.6 Task Component

API: `Task.java`



#### 2.7 Exception Component

API: `DukeException.java`

#### 2.8 Dishes Component

The Recipebook contains 2 classes, Dishes Class and DishList Class

![dishes](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/dishes.PNG)

**<u>Dishes Class</u>**

This class holds the name of the dish as well the ingredients that are associated to that specific dish. 

| Attributes                       | Description                                     |
| -------------------------------- | ----------------------------------------------- |
| dishName: String                 | name of the dish                                |
| numberOfOrders: int              | the total number of orders for that dish        |
| rating: float                    | the overall rating for that dish                |
| ingredientList: `IngredientList` | a list of ingredients associated with that dish |

| Constructor    | Description                              |
| -------------- | ---------------------------------------- |
| Dishes(String) | assigns the name of the dish with String |

| Methods                      | Description                                                  |
| ---------------------------- | ------------------------------------------------------------ |
| clearOrders(): void          | clears the ingredient list                                   |
| getDishName(): String        | returns the name of the dish                                 |
| addIngredients(String): void | takes a string and adds into ingredientlist                  |
| toString(): String           | it returns a String of all the ingredients that the dish contains |
**<u>DishList Class</u>**

this class inherits the GenericList class  which takes in a List of Dish. this class holds all the dishes that is stored in the csv file thus this acts as a menu for the chef

| Constructor          | Description                                |
| -------------------- | ------------------------------------------ |
| DishList(List<Dish>) | assigns a list of dishes to dishList       |
| DishList()           | assigns an empty ArrayList<>() to dishList |



#### 2.9 dishesCommand Component

The dishesCommand component  enables the chef to modify the dishList which acts as a menu or recipebook. this component inherits from other classes. 

- addDishCommand and addIngredient inherits from AddCommand class which inherits from the Cmd class
- deleteDishCommand inherits from the DeleteCommand which inherits from the Cmd class
- ListDishCommand and InitCommand inherits from the Cmd class

this component allows the chef to add dishes to the current menu, remove it and also to see the menu in the form of a list. this component also allows the chef to initialize his menu which deletes all entries in the dishList. the chef is also able to add ingredients to a specific dish in the dishList. 

- **<u>AddDishCommand</u>**
  
  user enters the command `addish chicken rice /num 2` which denotes adding the dish called chicken rice into the dishList. the program will enter the AddDishCommand class and executes the method below.
  
  If the dishList is empty(size of dishList is 0), immediately add the dish into the dishList. however, if the dishList is not empty, the program will need to go through the entire dishList to check if the dish has already been added. this is done so that there are no duplicate dishes.
  
  once the dish is added to the dishList, the method will use the Ui class with method call ui.showAddedDishes() as a reply to the user that the dish has been successfully added into the list.
  
  
  
- **<u>AddIngredient</u>**

  user intends to add an ingredient to a certain dish in dishList. thus user enters `ingredient rice 100 1` which denotes adding an ingredient called rice with a amount of 100g to the index of the dish. in this case the index is 1. 

  if any of the description is empty, an exception will be thrown to inform the user. 

- **<u>DeleteDishCommand</u>**

  user intends to remove a dish in dishList. thus user enters `remove 1` which denotes removing a dish of index 1 from the dishList. 

  if there is no such dish with user keyed index, an exception will be thrown to the user.

- **<u>InitCommand</u>**

  user intends to clear the dishList. thus user enters `initialize` which will clear the dishList

- **<u>ListDishCommand</u>**

  user intends to list all the dishes in the dishList. for each dish in the list, there are also ingredients associated to it. hence, this command prints all the dishes as well as the ingredients associated to it.

![dishesCommand](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/dishesCommand.PNG)

**<u>future additions</u>**

#### 2.10 Order Component
API: `Order.java`, `OrderList.java`

The Order component contains 2 classes, Order Class and OrderList Class. The chef can add new orders and update his "todo list" today. When an order comes, the program calculates dishes amount for each type of dishes. It then access the recipebook which contains every dishes in the menu including the recipe (the amount of consisting ingredients) so as to get the total amount of the ingredients needed to finish this order. It checks with the storage of those ingredients in the fridge and returns the information that if ingredients are enough.

Besides, in current stage, we assume that the dishes in chef's todo list should be finished by the end of "today". At the beginning of every day, the todo list will be initialized. However, the order supports pre-order, allowing the order date is not today. That is, the initialization of chef's todo list might not be empty.


**<u>Order Class</u>**

| Attributes                                          | Description                                                  |
| --------------------------------------------------- | ------------------------------------------------------------ |
| content: Map<Dishes, Integer>                       | the content of the order, specifying ordered dishes and amount |
| isDone: boolean                                     | the status of the order: *true* if done, *false* otherwise   |
| date: Date                                          | the serving date of the order (not the date when the order was created) |



| Constructor   | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| Order()       | By default, the order is not done; the serving date is today. |
| Order(String) | Assigns the serving date of the order with String. Call if it is a pre-order. |



| Methods                                | Description                                                  |
| -------------------------------------- | ------------------------------------------------------------ |
| getDate(): Date                        | returns the serving  `date` of the order                     |
| setDate(String): void                  | takes in a `String` and alter the serving `date`  of the order |
| isToday(): boolean                     | returns a `boolean` indicating whether the serving date is today or not |
| isDone(): boolean                      | returns a `boolean` indicating whether the order is finished or not |
| markAsDone(): void                     | mark the order as finished                                   |
| getStatusIcon(): String                | takes in an `int` and sets the new overall rating of the dish |
| getOrderContent(): Map<Dishes, Integer> | returns the order content as `Map`                           |
| toString(): String                     | returns description of the order as `String`                 |
| printInFile(): String                  | returns description of the order that used to store in the txt file |
| hasDishes(Dishes): boolean             | returns a `boolean` indicating whether the order has the dishes or not |
| getDishesAmount(Dishes): int           | returns the amount of the query dishes in the order          |
| addDish(Dishes): void                  | add one more the dishes to the undone order                  |
| addDish(Dishes, int): void             | add the dishes to the undone order with adding amount        |

**<u>OrderList Class</u>**

| Atrributes             | Description |
| ---------------------- | ----------- |
| orderList: List<Order> |             |



| Constructor            | Description                                        |
| ---------------------- | -------------------------------------------------- |
| OrderList()            | initalize the empty orderLIst as a new ArrayList<> |
| OrderList(List<Order>) | Assign a list of orders to the orderList           |



| Methods                              | Description                                                  |
| ------------------------------------ | ------------------------------------------------------------ |
| size(): int                          | returns the number of orders in the orderList                |
| markOrderDone(int): void             | mark a order as completed                                    |
| getOrder(int): Order                 | return the order at the position indexed by number           |
| getAllUndoneOrders(): List<Order>    | return all undone orders in the orderList                    |
| getTodayOrders(): List<Order>        | return all today's orders in the orderList                   |
| getTodayUndoneOrders(): List<Order>  | return all today's orders which is undone in the orderList   |
| findOrderByDate(String): List<Order> | returns a list of orders on that date                        |
| findOrderByDishes(Dish): List<Order> | returns a list of orders that contains that dishes           |
| changeOrderDate(int, String): void   | alter the serving date of the order in the orderList         |
| getDishesTodayAmount(Dishes): int    | return required amount of the dishes that needed to be done before the end of today |
| addOrderDish(int, Dishes): void      | add dishes to the order in the orderList                     |
| addOrderDish(int, Dishes, int): void | add dishes with amount to the order in the orderList         |
| findDishesAmont(int, Dishes): int    | find dishes amount in the order among the orderList          |

#### 

#### 2.11 Order Command Component
API: `AddOrderCommand.java`, `AlterDateCommand.java`, `DeleteOrderCommand.java`, `DoneOrderCommand.java`, `ListOrderCommand.java`

The Order Command classes inherits from the `Command` class. They overwrite the abstract method `execute` of the `Command` class. The Order Command classes includes:

- AddOrderCommand: This command will add order to the orderlist and update the chef's todo list. The order can be loaded manually by command. The order can also be read from the file when initializing -- which is the case of the pre-order.
- AlterDateCommand: This command will change the serving date of the order. If it changed to the date of today, then chef's todo list should be updated. If it changed to the date before today, then the change is considered invalid.
- DeleteOrderCommand: This command is used for cancelled orders. It also synchronizes with chef's todo list.
- DoneOrderCommand: This command is used for changing the status of the order to be finished. The done dishes in the order can be removed from the chef's todo list. 
- ListOrderCommand: The command is to list all orders or is to list orders after filtering. The filter feature can be date, dishes, order status.

#### 2.12 Fridge Component
API: `Fridge.java`

The Fridge class allows access and modification of the `Ingredient`s used by the chef. By keeping track of the Ingredients' expiry date, it allows the user to know which products have expired, and remove them. It allows for less ingredient waste, as it can return the most recently expiring ingredients, so that they can be used first. 

![Fridge](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/fridgeUML1.png)

#### 2.13 GenericList
API: `GenericList.java`

This abstract class allows for creation of different types of lists, and basic list entry manipulations. It is extended by multiple classes, including `IngredientsList.java`, `TaksList.java`, `OrderList.java` and `DishList.java`. All of these classes inherit the basic methods from the Generic List and extend it with their specific methods, eg.  `allUndoneOrders()` from`OrderList.java`, or `changeAmount()` from `IngredientsList.java`. A UML Class Diagram is shown below.

![GenericList](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/GenericListUML1.png)

#### 2.14 Ingredient Component

The Recipebook contains 2 classes, Ingredient and IngredientsList. 

//!add diagram of the Ingredient component.

**<u>Ingredient Class</u>**

| Attributes           | Description                            |
| -------------------- | -------------------------------------- |
| name: String         | Name of the ingredient                 |
| amount: int          | Total amount of the ingredient         |
| expiryDate: Date     | Expiry date of the given ingredient    |
| dateAsString: String | A string to store the date as a string |

#### 2.10 Fridge Component
API: `Fridge.java`

The Fridge class allows access and modification of the `Ingredient`s used by the chef. By keeping track of the Ingredients' expiry date, it allows the user to know which products have expired, and remove them. It allows for less ingredient waste, as it can return the most recently expiring ingredients, so that they can be used first. 

![Fridge](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/fridgeUML.png)

#### 2.11 GenericList

This abstract class allows for creation of different types of lists, and basic list entry manipulations. It is extended by multiple classes, including `IngredientsList.java`, `TaksList.java`, `OrderList.java` and `DishList.java`. All of these classes inherit the basic methods from the Generic List and extend it with their specific methods, eg.  `allUndoneOrders()` from`OrderList.java`, or `changeAmount()` from `IngredientsList.java`. A UML Class Diagram is shown below.

![GenericList](https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/images/GenericListUML.png)

##### <u>Ingredient Class</u>



##### <u>Ingredient Class</u>

| Constructor                       | Description                                               |
| --------------------------------- | --------------------------------------------------------- |
| Ingredient(String, Integer, Date) | Gives the name , amount and expiry date of the ingredient |



| Methods                             | Description                                                  |
| ----------------------------------- | ------------------------------------------------------------ |
| Ingredient(String, Integer, String) | Converts the Date into String                                |
| getAmount(): int                    | Returns amount of ingredient                                 |
| getName(): String                   | Returns name of the Ingredient                               |
| changeDate(Date): void              | Changes the expiry date of the ingredient                    |
| setName(String): void               | Sets the name of the ingredient                              |
| changeAmount(Integer): void         | Changes the amount of the ingredient                         |
| getExpiryDate(): Date               | Returns the expiry date of the ingredient                    |
| equals(Object): Boolean             | Returns true or false when comparing 2 objects<br />True: Object names are identical<br />False: Otherwise |
| isExpired(): Date                   | Returns the expiry date                                      |
| equalsCompletely(Object): Boolean   | Returns true or false when comparing 2 objects<br />True: Objects have same name and expiry date<br />False: Otherwise |

**<u>IngredientsList Class</u>**

A child class of Ingredients and inherits(extends) the attributes and methods of the Ingredients class.

| Constructor                                       | Description                                               |
| ------------------------------------------------- | --------------------------------------------------------- |
| IngredientsList(List<Ingredient> ingredientsList) | Initializes the IngredientsList as a new List<Ingredient> |



| Methods                                                      | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| changeIngredientsDate(int, Date): void                       | Changes the date of the Ingredient using an Index number     |
| changeName(int, String): void                                | Changes the name of the ingredient using an Index number     |
| changeAmount(int, String): void                              | Changes the amount of the ingredient using an index number   |
| addEntry(Ingredient Object): void                            | Adds a new Ingredient of Ingredients attributes into the Ingredient List |
| hasEnough(Ingredient Object): Boolean                        | Returns true or false when comparing 2 Objects.<br />True: We have enough of the required Ingredient<br />False: Otherwise |
| getEntry(Ingredient Object): Ingredient Object               | Looks for the queried ingredient and returns it              |
| getNonExpiredEntry(Ingredient ingredient): Ingredient Object | Looks for the queried Ingredient in the list that is not expired and returns it |
| sortByExpiryDate(): Ingredient Object                        | Sorts the Ingredient lists accordingly by a descending amount |
| removeEntry(Ingredient Object):  Boolean                     | Looks for the queried Ingredient in the list and remove the amount that we want to use.<br />True:  Enough amount of the queried ingredient<br />False: Not enough amount  of the queriedingredient |

#### 2.15 ingredientCommand Component

API: `AddCommand.java`, `DeleteCommand.java`, `FindToday.java`, `ListCommand.java`, `RemoveAllExpired.java,FindIngredientCommand.java, UseCommand.java,`

The ingredientCommand classes inherits from the `Command` class. They overwrite the abstract method `execute` of the class `Cmd`. The ingredientCommand classes includes:

- AddCommand: This command adds an entry of an ingredient to the IngredientsList. The IngredientsList can also be read from the file when initializing.
- DeteleCommand: This command deletes an entry of an ingredient from the IngredientsList. 
- FindToday: This command is used to look for expired ingredients on the date itself.
- ListCommand: This command is used to show the chef's entire IngredientsList.
- RemoveAllExpired: The command is used to remove all expired ingredients from the IngredientsList
- FindIngredientCommand: This command is used to find all ingredients with the queried keyword entered by the chef.
- UseCommand: This command is used to delete the specified amount off an ingredient when it is used. Otherwise, if there is not enough of the required amount entered by the chef, the program will prompt it to the chef.

### 3. Implementation

### 4. Documentation

### 5. Testing

### 6. Dev Ops 



### Appendix A: Product Scope

Target user profile: Restaurant Chef

- needs to manage all the ingredients for his dishes  
- needs to keep track of orders 
- needs to manage ingredients in the fridge
- 
- prefers a desktop application, CLI
- prefers to keep everything neat in terms of viewing information
### Appendix B: User Stories

​	Priorities: High (must have), Medium (nice to have), Low (may not have)

| Priority | As a ...           | I want to ...                                                | So that ...                                          |
| -------- | ------------------ | ------------------------------------------------------------ | ---------------------------------------------------- |
| High     | restaurant manager | I want to ensure that only specific users are able to access the application | the data related to my restaurant is secure          |
| High     | restaurant manager | I want to keep track of food expiry dates                    | the restaurant will not serve expired food           |
| High     | restaurant manager | I want to note down my part timer's available dates          | I can ensure there is enough manpower in the kitchen |
| Medium   | restaurant manager | I want to keep track of my monthly expenses                  | I know whether I am making a profit or loss          |
| Medium   | restaurant manager | I want to keep track of my monthly hygienic checks           | my restaurant could keep it's working license        |
| Medium   | restaurant manager | I want to keep track of my company meetings                  | I am able to organize all my employees               |
| Low      | restaurant manager | note down customer feedbacks regarding dishes                | I can tell if my recipe is doing well or not         |
| Low      | restaurant manager | I want to keep track of holiday dates                        | I can prepare for special food menus                 |
| high     | restaurant Chef    | a list to keep all my recipes                                | i can refer to them if needed                        |
| high     | restaurant Chef    | keep track of all the ingredients in the kitchen             | there are ample supply and no expired ingredient     |
| high     | restaurant Chef    | to see dishes that i am able to make                         |                                                      |
| high     | restaurant Chef    |                                                              |                                                      |
| high     | restaurant Chef    |                                                              |                                                      |

### Appendix C: Use Case

**Use case: Exit the program**

**MSS**

​	1. User requests to exit the program

​	2. Program exits

​		Use case ends.

**Extensions**

- 2a. Incorrect syntax

  - 2a1. Program prompts for correct syntax

    Use case resumes at step 2.



**Use case:  Show all the tasks**

​	1. User requests to view all the existing todo list

​	2. Program loads up the lists of existing tasks

​		Use case ends.

**Extensions**

- 2a. todoList is empty

​		Use case ends.

- 2b. Incorrect syntax

  - 2c1. Program prompts for correct syntax

    Use case resumes at step 2.



**Use case: Mark a order as done**

​	1. User requests to mark order as done

​	2. Program marks the order as done

​		Use case ends.

**Extensions**

- 2a. Given index is invalid

  - 2a1. Program shows an error message

    Use case resumes at step 2.

- 2b. Incorrect syntax

  - 2b1. Program prompts for correct syntax

    Use case resumes at step 2.



**Use case: Create a new order**

​	1. User requests to add a new order

​	2. Program adds new order to order List

​		Use case ends.

**Extensions**

- 2a. Incorrect syntax

  - 2a1. Program prompts for correct syntax

    Use case resumes at step 2.



**Use case: Create a new dish**

1. User requests to add a new dish

2. Program adds new deadline dish to dish List

   Use case ends.

**Extensions**

- 2a. Incorrect syntax

  - Program prompts for correct syntax

    Use case resumes at step 2.



**Use case: Create a new ingredient**

​	1. User request to add a new ingredient

​	2. Program adds a new event ingredient to ingredient List

​		Use case ends.

**Extensions**

- ​	2a. Incorrect syntax

  - Program prompts for correct syntax

    Use case resumes at step 2.



**Use case: Find a ingredient by searching for keyword**

1. User requests to find tasks with the given keyword

2. Program loads up and shows ingredient with the given keyword

**Extensions**

- 2a. ingredient List is empty

  - Program prompts for correct syntax

    Use case ends.

- 2b. Incorrect syntax

  - Program prompts for correct syntax

    Use case resumes at step 2.

### Appendix D: Non Functional Requirement

1. should work on any windows OS as long it has `java 11` or newer installed 
2. The application needs to be secure. only specific users are able to access this application. for example, the restaurant manager as well as the chef
3. should be reliable in displaying accurate and correct data 
4. should be easy to use for users with basic knowledge of command line interface
5. should be able to handle large amounts of data without displaying any slowdown in application performance 



### Appendix E: Glossary 

1. 

### Appendix F: Instruction for Manual Testing 

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest Duke.jar [here](https://github.com/AY1920S1-CS2113-T14-2/main/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke application.
4. Use the command prompt and navigate to the path where the application is downloaded `cd ../FILEPATH`
5. run the command `java -jar v1.4` , application will then be executed 

initially you are greeted with the main page, if there are expired ingredients in the fridge, the program will alert the user to clear the items. user can either enter `yes` or `no`. if user enters yes, all expired ingredients will be cleared. if user enters no, the ingredients will remain.

in the main page, there are several actions for the user:

| Index | Keyword | Usage   | Description                 |
| ----- | ------- | ------- | --------------------------- |
| 1     | options | options | show options                |
| 2     | q       | q       | exit program                |
| 3     | t       | t       | view todo list              |
| 4     | a       | a       | remove expired ingredients  |
| 5     | b       | b       | go into ingredient template |
| 6     | c       | c       | go into order template      |
| 7     | d       | d       | go into dish template       |

#### E2. Launch and shutdown

1. Initial Launch
   1. Download Jar file and copy into empty folder
   2. open command prompt and navigate to that `FILEPATH` and run `java -jar v1.4`. 
   3. resize window if size is not optimum
   4. enter `q` to close the program or close the window

#### E1. Adding an ingredient

1. Adding an ingredient to the List

#### E4. Finding an ingredient

#### E5. Remove an ingredient

#### E6. Adding an order

#### E7. Marking order as done

#### E8. Altering order

#### E9. Removing order

#### E10. Adding a dish

1. adding a dish to the dishList

   1. prerequisite: list all dishes using `list` 

   2. Test case 1: `add chicken rice`

      Expected: search through the dishList, if there is no match, adds a dish called chicken rice into the dishList. output message to user that the dish has been added

   3. Test case 2: `add chicken rice`

      Expected: search through the dishList, if there is a match, dish will not be added into the dishList. output message to user that the dish is already in the list

   4. Test case 3: `add`

      Expected: output message to user that the description cannot be empty

#### E11. Removing a dish

1. removing a dish from the dishList

   1. prerequisite: list all dishes  using `list`, eg the size 

   2. Test case 1: `remove 1`

      Expected: deletes the first dish in the list, 

#### E12. Adding an ingredient to a dish

#### E13.