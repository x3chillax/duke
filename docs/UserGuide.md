# Chef Duke - User Guide

1. Introduction

2. Quick Start

3. Features

   3.1 Main Menu
   
   3.2 Dish
   
   ​	3.2.1 Adding Dish
   
   ​	3.2.2 Adding Ingredient to Dish
   
   ​	3.2.3 List all Dishes
   
   ​	3.2.4 Initializing the Dish
   
   ​	3.2.5 Removing a Dish
   
   3.3 Ingredient
   
   ​	3.3.1 Adding Ingredient
   
   ​	3.3.2 Removing Ingredient
   
   ​	3.3.3 Finding Ingredients
   
   ​	3.3.4 Listing all Expired Ingredient on Date itself
   
   ​	3.3.5 Removing all Expired Ingredient 
   
   ​	3.3.6 Using an Ingredient
   
   3.4 Fridge
   
   ​	3.4.1 Add an Ingredient to Fridge
   
   ​	3.4.2 Removing an Ingredient from Fridge
   
   ​	3.4.3 Use Ingredient from Fridge 
   
   ​	3.4.4 Remove all Expired Ingredient from Fridge
   
   3.5 Order
   
   ​	3.5.1 Adding Order
   
   ​	3.5.2 Altering Order
   
   ​	3.5.3 Deleting Order
   
   ​	3.5.4 Done Order
   
   ​	3.5.5 Initializing Order
   
4. Command Summary
5. FAQ


## 1. Introduction

Duke is targeted towards restaurant chefs who wants to be able to consolidate most of the things happening in their kitchen such as recipes, ingredients, expiry dates etc. By using this product, you are able to order all the ingredients needed for your kitchen. Additionally, this application takes in customers order/preorder of the restaurants dishes. Proceed to the Developer Guide [here]( https://github.com/AY1920S1-CS2113-T14-2/main/blob/master/docs/DeveloperGuide.md ) to learn more about this application. 
## 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest Duke.jar [here](https://github.com/AY1920S1-CS2113-T14-2/main/releases).

3. Copy the file to the folder you want to use as the home folder for your Duke application.

4. Use the command prompt and navigate to the path where the application is downloaded `cd ../FILEPATH`

5. run the command `java -jar v1.3` , application will then be executed 

   ![UI]( https://github.com/AY1920S1-CS2113-T14-2/main1/blob/master/docs/images/Ui.png )

6. Type some commands and press ENTER to execute

7. Some example commands

   1. given various options in menu, user can type these commands `options` ,`q`, `t`, `a`, `b`, `c`, `d`.
   2. user enters `d` then `add chicken rice`, dish is then added to the list
   3. user enters `d` then `list`, outputs the the dishes and the ingredients associated to the dish in table form

8. Refer to Section 4 for the full list of commands



## 3. Features

**Command Format**

- Command parameter in `UPPER_CASE` is needed to be specified by the user. E.g., `add DESC` can be specified as `add noodle`.
- Command parameter followed by `-(...)` is to inform the user of the specifying format. E.g., `ORDER_DATE-(dd/mm/yyyy)` indicates only the format such as `31/12/2019` is accepted. 
- Some command parameter is followed by `-(option: a (default) | b | c)`, indicating it has `a`, `b`, `c` three options, with the default set to `a`. E.g., for `-l LIST_TYPE-(option: all (default) | undone)`, the user can enter `-l` without further specification as it is equivalent to `-l all`, or specify as `-l undone`.
- Command parameter can be optional. If it is wrapped by `[...]`, the specification can be dropped and the value is set as `NULL` or default. Otherwise, the parameter must be specified. E.g.,  `add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT]` supports command  `add chicken rice*1, cake*2, laksa`, where the order date is set to `date of today` if not specified, and the dish amount is set to `1` if not specified.

### 3.1 Main Menu

The user is greeted depending on the time of day, `good morning` , `good evening` etc. Upon start up, if there are any expired ingredients in the fridge, the application will prompt the user to clear the expired ingredients. In the Main menu, the user is given various options to enter:

- `a`, remove the **expired** ingredients 
- `b` , proceed to **fridge** commands
- `c`, proceeds to **order** commands
- `d`, proceeds to **dish** commands
- `t`, view today's **todo** list
- `q` , **exits** the application



### 3.2 Recipe Book Management

the user needs to enter command `d` from the Main menu to enter the Dish template.

add ui here

#### 3.2.1 Adding Dish

user needs to enter `d` in main menu first.

To add a dish to the DishList, the user needs to execute command below:

Format: `add DISH_NAME`

If the dish already exist in the list, message is output:

```
         dish already exist in list
```

Examples: 

- `add chicken rice`
- `add tom yum noodles`

```
         _________________________________________________________________________________________
         you have added the following dish:
         chicken rice
         _________________________________________________________________________________________
```

#### 3.2.2 Adding Ingredient to Dish

user needs to enter `d` in main menu first.

Executing this command associates an `Ingredient` to an existing `Dish`. The user needs to execute the following command with the below format:

Format: `ingredient INGREDIENT_NAME INGREDIENT_AMOUNT DISH_INDEX`

Examples:

- `ingredient rice 50 1`, ingredient rice (50g) is now associated to 1st dish
- `ingredient noodle 50 2` ingredient noodle (50g) is now associated to 2nd dish

```
         _________________________________________________________________________________________
         ingredient: rice
         added to: chicken rice
         _________________________________________________________________________________________
```


#### 3.2.3 List all Dishes
The user needs to enter the command below:

Format: `list`

If there are no dishes in list, the program will output the following message:

```
        OOPS!!! No Dishes yet!.
        You can type:
        'template' to see the format of the commands,
        'back' to see all your options,
        'q' to exit
```

If user enters a valid command and the dish list is not empty, the result will be as follows.

```
   ____________________________
   | Dish          | ingredient|
   |===========================|
1. | chicken rice  |           |
2. | chicken noodle|           |
3. | pizza         |           |
4. | aglio olio    |           |
```

```
   _______________________________
   | Dish          | ingredient   |
   |==============================|
1. | chicken rice  | rice,salt,   |
2. | chicken noodle| noodle,flour,|
```

#### 3.2.4 Initializing the Dish List
user needs to enter the command below:

Format: `initialize`

The user is then asked to confirm as this command deletes all the entries in the dish.

If user enters `y`,

```
         are you sure you want to clear list? (yes or no)
y
         LIST IS CLEARED
```

If user enters `n`,

```
         are you sure you want to clear list? (yes or no)
n
         LIST IS NOT CLEARED
```

#### 3.2.5 Removing Dish
The user needs to enter the command below:

Format: `remove DISH_INDEX`

Examples:

- `remove 1`
- `remove 2`

If user enters an invalid index, an error message will appear.

```
        OOPS!!! dish does not exist.
        You can type:
        'template' to see the format of the commands,
        'back' to see all your options,
        'q' to exit
         _________________________________________________________________________________________
```

Otherwise, it will print out the information of the removed dishes.

```
         _________________________________________________________________________________________
         The following dish have been removed:
         chicken rice
         _________________________________________________________________________________________
```

### 3.3 Ingredient Management by Fridge Storage

To begin with, the user needs to enter command `'a'- remove all expired` or `'b' - add/remove/use an ingredient` in the main menu. In mode `a`, all expired ingredients would be removed after execution of the command `a`. While the execution of command  `b`, directs the user into Ingredient management menu. The below commands, except for removing all expired, are all executed in the Ingredient management menu.

#### 3.3.1 Adding Ingredient

To add an ingredient to the IngredientsList, user needs to execute command below:

To add an ingredient to the IngredientsList, the user needs to execute command below:

Format: `add `  `INGREDIENT_NAME` `INGREDIENT_AMOUNT` `INGREDIENT_EXPIRY_DATE-(dd/mm/yyyy)`

If the ingredient already exist in the list, but **do not have the same expiry date**, another entry of the ingredient will be created in the ingredient list. **However**, if the ingredient also **have the same expiry date as the existing ingredient in the ingredient list**, the amount of the existing ingredient amount will be added on and no new entry will be created in the ingredient list.

Examples: 

- `add ` `salt` `50` `31/10/2019` 
- `add` `chilli` `70` `29/04/2019`  

```
         _________________________________________________________________________________________
         you have added the following ingredient:
         salt 50 31/10/2019
         _________________________________________________________________________________________
```

#### 3.3.2 Removing Ingredient

To remove an ingredient from the IngredientsList, user needs to execute command below:

To remove an ingredient from the IngredientsList, the user needs to execute command below:

Format: `remove ` `ingredient index`

If the ingredient index does not exist in the list, message is output:

```
	 ☹ OOPS!!! Enter a valid ingredient index number after delete, between 1 and 14.
	 You can type: 
	'template' to see the format of the commands, 
	'back' to see all your options, 
	'q' to exit
```

This example was done when the size of the ingredient list is 14.

Examples: 

- `remove ` `7`
- `remove` `3 `

```
         _________________________________________________________________________________________
         Noted. I've removed this ingredient:
	 salt, amount is: 50 expiring on 31st of Octoboer 2019
	 Now you have 14 ingredients in the list.
         _________________________________________________________________________________________
```

#### 3.3.3 Finding Ingredient

To find an ingredient from the IngredientsList, user needs to execute command below:

To find an ingredient from the IngredientsList, user needs to execute command below:

Format: `find` `INGREDIENT_NAME`

If the ingredient index does not exist in the list, message is output:

```
	 No such ingredient found!
```

Examples: 

- `find ` `salt`
- `find` ` chilli `

```
These are the ingredients you searched for!
	 5. salt 50 31/10/2019.
	 9. salt 60 21/07/2021
```


#### 3.3.4 Using an ingredient 

To use an ingredient from the ingredient list, the user needs to execute command below:

Format: `use`  `INGREDIENT_NAME` `INGREDIENT_AMOUNT` 

If there are not enough required ingredients, message is output:

```
	 There is not a sufficient amount of rice that is not expired, maybe you could buy some first? 
```

Examples: 

- `use ` `salt` `50` 
- `use` ` chilli` `40`  

```
	 Great you used salt amount: 50
```

Also, once amount reaches 0, the ingredient will be deleted off from the IngredientsList. 

#### 3.3.5 Listing all expired ingredients on the date itself

To list all expired ingredient from the IngredientsList on the date itself, user needs to execute command below:

To list all expired ingredient from the IngredientsList on the date itself, user needs to execute command below:

Format: `listtoday`

if there are no expired ingredients for the date itself, message is output:

```
	 No expired ingredients for today!
```

Otherwise,

```
	 Here are the expired ingredients for today
	 6. salt, amount is: 50 expired on 31st of October 2019.
	 7. chilli, amount is: 60 expired on 31st of October 2019.
```

#### 3.3.6 Removing all expired ingredients 

User needs to enter `a` in the menu.

*Note that*: this removing all expired command is executed by the user entering `a` in the **main menu**.

If there are no expired ingredients for the date itself, message is output:

```
	 ☹ OOPS!!! Seems like you don't have any expired ingredients in the fridge!. 
```

Otherwise,

```
	Removed:  ingredients: 
	salt, amount is: 50 expired on 31st of October 2019
```

### 3.4 Order Management

To begin with, the user needs to enter command `c` in main menu, so as to step into Order management menu. The below commands are all executed in the Order management menu. 

#### 3.4.1 Adding Order Today or Pre-Order: `add`

To add a new order to the order list, the user needs to execute the command following the below format.

Format: `add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT], ...`

<u>Requirement:</u>

- The serving date of the order `ORDER_DATE` should be valid and **cannot be before today**. 
  - If the date is today, the user can simply enter command `add -n ...`. 
  - Otherwise, the order is treated as *pre-order*. `ORDER_DATE` must be specified.
- The ordered dishes **cannot be empty**.

Examples: 

- `add -d 31/12/2019 -n beef noodle*3`: there can be only one ordered dishes.
- `add -d 12/11/2019 -n chicken rice*1, cake*2, laksa`: the amount of laksa is 1, as default.
- `add -n pasta, mushroom soup` : the serving date of this order is date of today, as default.

Sample output message:

```
_________________________________________________________________________________________
	 Got it. I've added this order: 
	 [✘] Order /on 31/12/2019 
	    (1) beef noodle 3
	 Now you have 1 orders in the order list.
_________________________________________________________________________________________
```

```
_________________________________________________________________________________________
	 Got it. I've added this order: 
	 [✘] Order today 
	    (1) pasta 1
	    (2) mushroom soup 1
	 Now you have 2 orders in the order list.
_________________________________________________________________________________________
```

#### 3.4.2 Altering Order Serving Date

In pre-order management, it is very likely that the serving date alters. To update the serving date information of an order in the order list, the user needs to execute the command following the below format.

Format: `alter ORDER_INDEX ORDER_DATE-(dd/mm/yyyy)`

<u>Requirement:</u>

- Altering a done order is not expected. It will do nothing and reminds you of `Order done already. Date alteration is not expected.`
- The range of `ORDER_INDEX` is 1 to the size of the order list.
- The newly set date should be in valid format and **cannot be before today**. 
  - If the date is today, the user can simply enter `alter ORDER_INDEX`. 
  - Otherwise, the order is treated as *pre-order*. `ORDER_DATE` must be specified.

Examples: 

- `alter 2`: changes the serving date of 2nd order in the order list to today.
- `alter 1 03/01/2020`: changes the serving date of 2nd order in the order list to `03/01/2020`.

If the order list is empty, the output message would be:

```
	 ☹ OOPS!!! No order in the list! No order can be altered!
```

Otherwise, the sample output message would be like:

```
_________________________________________________________________________________________
	 Nice! I've changed the order at 07/11/2019:
	 [✘] Order today 
	    (1) fish 1
	    (2) chili crab 1
	    (3) rice 2
_________________________________________________________________________________________
```

```
_________________________________________________________________________________________
	 Nice! I've changed the order at 03/12/2019:
	 [✘] Order /on 03/12/2019 
	    (1) fish 1
	    (2) chili crab 1
	    (3) rice 2
_________________________________________________________________________________________
```

#### 3.4.3 Removing Order

To remove an existing order from the order list, the user needs to execute the command following the below format.

Format: `remove ORDER_INDEX`

<u>Requirement:</u>

- The range of `ORDER_INDEX` is 1 to the size of the order list.

Examples: 

- `remove 3`: remove 3rd order in the order list.

If the order list is empty, the output message would be:

```
	 ☹ OOPS!!! No order in the list! No order can be removed!
```

Otherwise, the sample output message would be like:

```
_________________________________________________________________________________________
	 Noted. I've removed this order:
	 [✘] Order /on 03/12/2019 
	    (1) fish 1
	    (2) chili crab 1
	    (3) rice 2
	 Now you have 3 orders in the order list.
_________________________________________________________________________________________
```

#### 3.4.4 Marking Order as Done

To mark an existing undone order as done, the user needs to execute the command following the below format.

Format: `done ORDER_INDEX`

<u>Requirement:</u>

- The range of `ORDER_INDEX` is 1 to the size of the order list.
- The indexed order should be in status of `undone` before marking it as `done`. Otherwise, it will do nothing and reminds you `Order ORDER_INDEX has already been done!`

Examples: 

- `done 3`: mark 3rd order in the (whole) order list as done. The whole order list includes orders with the status of done. But the program will check if the targeted order is done during the execution.

If the order list is empty, the output message would be:

```
	 ☹ OOPS!!! No order in the list! No order can be done!
```

Otherwise, the sample output message would be like:

```
_________________________________________________________________________________________
	 Nice! I've marked this order as done:
	 [✓] Order /on 12/09/2020 
	    (1) beef noodle 1
	    (2) chili crab 1
	    (3) rice 3
_________________________________________________________________________________________
```



#### 3.4.5 Initializing Order List

To clear all the orders in the order list, the user needs to execute the command following the below format.

Format: `init`

The program will then asks the user to confirm the initialization: `Are you sure you want to clear all orders in the order list? [y/n] `

If the user enters `y` or `Y`, the output message will be:

```
_________________________________________________________________________________________     ORDER LIST CLEARED​     Continue by adding order. Template:     add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT]_________________________________________________________________________________________
```

If the user enters `n`  or `N`, the output message will be:

```
_________________________________________________________________________________________
	 ORDER LIST NOT CLEARED

	 Continue by adding, removing, altering, listing order.
	 Type 'template' to see the format of the commands
_________________________________________________________________________________________
```

If the user enters neither `y` or `n`, then the order list maintains. Note that the user has to enter `init` again and then enters confirm the initialization. An `y` or `n`  command not after the confirmation question is regarded as invalid.

#### 3.4.5 Listing Order by Different Filtering Keywords

To list orders in the order list, the user needs to execute the command following the below format.

Format 1: `list [-l LIST_TYPE-(option: all (default) | undone | today | undoneToday)]`

- Example: `list -l undone`, `list -l today`, `list -l undoneToday`, `list` (i.e., `list -l all`)

Format 2: `list -n DISH_NAME`

- Example: `list -n chicken rice`

Format 3: `list -d ORDER_DATE-(dd/mm/yyyy) [-l LIST_TYPE-(option: all (default) | undone)]`

- Example: `list -d 31/12/2019 -l undone`, ``list -d 31/12/2019` (i.e., `list -d 31/12/2019 -l all`)
- *If you want to find today's orders, we recommend you to follow format 1.*

If there is no order in the order list, the output message is: 

```
	 OOPS!!! No orders in the order list!. 
```

If there is no order satisfying the requirement, the output message is

```
	 OOPS!!! No orders found.
```

Otherwise, the sample output message is like:

```
_________________________________________________________________________________________
	 Here are the orders in the order list:
	 1.[✘] Order today 
	    (1) laksa 1
	 2.[✘] Order today 
	    (1) chicken rice 1
	 3.[✘] Order /on 12/12/2019 
	    (1) beef noodle 2
_________________________________________________________________________________________
```

### 3.5 Chef's ToDo List

Chef needs to check his/her remaining tasks of the day. The ToDo list keeps in accordance with the update of any order in the order list. To view the ToDo list, the user needs to enter `t` in the main menu.

The sample output message as follows: 

```
_________________________________________________________________________________________
	 Today Task list (Thu Nov 07 03:15:25 SGT 2019)
	 1. chicken rice (amount: 3) 
   2. cake (amount: 2) 
   3. beef noodles (amount: 4)

_________________________________________________________________________________________
```



### 3.6 Fridge commands

#### 3.6.1 Add an ingredient to the Fridge
#### 3.6.2 Remove an ingredient from the fridge
#### 3.6.3 Use an ingredient from the fridge
#### 3.6.4 Remove all expired ingredients from the fridge

### 3.7 stats: gives the statistics of the Dish



## 4. Command Summary

Main Menu

Index | Keyword  | Usage | Description 
----- | -------- | ---------- | ---------- 
1 | options | options | show options 
2 | q       | q | exit program 
 3 | t | t | view todo list 
 4 | a | a | remove expired ingredients 
 5 | b | b | go into ingredient template 
 6 | c | c | go into order template 
 7 | d | d | go into dish template 

Ingredient template

| Index | Keyword   | Usage                      | Description                    |
| ----- | --------- | -------------------------- | ------------------------------ |
| 1     | add       | add <desc> <amount> <Date> | add an Ingredient to the list  |
| 2     | remove    | remove <index>             | remove an ingredient from list |
| 3     | find      | find <desc>                | find an ingredient in list     |
| 4     | listtoday | listtoday                  | list all expired ingredient    |
| 5     | a         | a                          | removes expired ingredient     |
| 6     | use       | use <desc> <amount>        | use an ingredient              |

Order Template

| Index | Keyword | Usage                            | Description |
| ----- | ------- | -------------------------------- | ----------- |
| 1     | add     | add -d <date> -n <desc>*<amount> |             |
| 2     | alter   | alter <index> <date>             |             |
| 3     | remove  | remove <index>                   |             |
| 4     | done    | done <index>                     |             |
| 5     | init    | init                             |             |
| 6     | list    | list -l <option>                 |             |
| 7     | list    | list -n <desc>                   |             |
| 8     | list    | list -d <date> -l <option>       |             |

for <option>, (option: all (default) | undone | today | undoneToday)

Dish Template

| Index | Keyword    | Usage                              | Description               |
| ----- | ---------- | ---------------------------------- | ------------------------- |
| 1     | add        | add <desc>                         | adds a dish to the list   |
| 2     | remove     | remove <index>                     | removes a dish from list  |
| 3     | list       | list                               | list all dishes           |
| 4     | initialize | initialize                         | clears the dish list      |
| 5     | ingredient | ingredient <desc> <amount> <index> | add an ingredient to dish |

common commands in template

| Index | Keyword  | Usage    | Description                   |
| ----- | -------- | -------- | ----------------------------- |
| 1     | template | template | shows the template of current |
| 2     | back     | back     | goes to main menu             |
| 3     | q        | q        | exits program                 |



# 5. FAQ

Q: how do I transfer data to another computer 

A: install the application on the other computer and an empty recipe.txt will be created under the data folder. Replace this file with the same txt file found in your previous computer. therefore your data will be transferred. 
