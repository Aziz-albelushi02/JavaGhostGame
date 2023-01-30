# JavaGhostGame

Abdulaziz T Albeloushi 
UB: 21025497

Task 1:
A tile variable was declared to connect a 2D array for a new tile type was coded for the height and width than a for loop starting from 0 was made to cover the whole level with the FLOOR1 tile type, then the tile was returned to display the FLOOR1 tile type on the level. Different tiles of walls,doors, banks has been made, first,second and third walls in the level generator using the for loop for walls and single tile for doors and banks

Task 2:  
Two integers were declared y and z, y was 23 and z was 16, a new player variable was declared which helps to link between the player object, a number of 100 was typed in the brackets to determine the max health and the y and z were to determine the player spawn point, after that the player variable was returned to show the player on in the level

Task 3:
A player position has been set using the set position function to help trigger the action of movement and has been linked to the player variable, the getters of X and Y helps the player to move left and right on the X co-ordinate and up and down on the Y co-ordinate, the movement would be only 1 square per coordinate, each void for movement has been coded and changed.

Task 4 :
An array of the ghost has been made to create a new ghost instance object only 4 arrays have been coded, each instance object has been linked to its own arrays and a max health of 100 has been given, just like the player object has been given a random X and Y coordinate, every ghost has its own constructors.
Task 5: 
A for loop ( each loop ) has been made for the ghosts to move around the level without stopping, 

Task 6 :
For the hit ghost the change health method and the IF statement has been used and coded that if the player hits the ghost the player health would decrease -10 and the ghost health would decrease -20, the system will type hit in the output if the player hits the ghost and decrease its health, if the ghost health becomes 0 the player then will hold the dead (captured) ghost and that will confirm it that the ghost has been clean defeated, for the clean defeat a for loop has been made to insure that if the ghost health is 0 the ghost object will be null.

Task 7: 
The LevelNumber method has been added and another code to print the level number, a level generator has been added to the code to generate a new level when the objectives are complete, the same code for the place player to display the player on the next level, ghosts adder to add New Ghosts on the level and finally the spawnLocations which helps to set the spawn location on the level.

Task 8: 
The getters for the X and Y helps the player to move around the level but some of the getters has been modified to not let the player go through the walls, breaches, banks using the != which means not equal, the same code has been copied and pasted for the X coordinate left and right and Y coordinate up and down and few changes have been made for the getters.

Task 9: 
The algorithm is kind of similar to (Task 8), an IF statement has been made that is the ghosts are not null so that the ghosts do not move through the walls, and doors, the getters of Y was used to move up and down from 0 to 17 and the X getters to left and right to move from 0 to 34.

Task 10:
an If statement has been coded to trigger the action of the boolean, a Boolean has been made to detect that if the ghost was near the player, the ghost will not be able to move through the walls and the floors using the != (not equal to ) method, using the math absolute value will help the ghost to stay near the player so the player could capture and defeat the ghost.
Task 11:
 The cleanDefeatedGhost and moveGhost method has been used to change the level and update it if the player managed to clear the level from the ghosts, a Boolean has been called in and looped using each loop for the ghosts variable to check that if the ghosts as null (no ghosts) in the level the game will move and proceed to the next level using the next level method, otherwise if the ghosts are not null the Boolean of next will called false and will not change
Task 12:
Using the if statement and the getters method the player is able to refill its energy if it was low by moving to the bank tile and going through it to refill its energy using the change energy method and refill it to Max energy the code has been copied for each movement from left, right,up and down only a few changes have been made for the getters, using the IF statement if the player energy is higher than zero the changeEnergy method is called and decreased -10 if it hits a ghost, the ghost health using the changing health method for ghost is decreased -20 if the ghost health is less than or equals 0 then the ghost is captured, the player is not able to decrease the ghost health if there was no energy using If method. 

Task 13: an if statement has been set true for the capture ghost attribute that if the player defeats the ghost the IF statement will approve for the attribute to make the player object carry the ghost, then the player will move using the keys to the bank and using the deposit ghost attribute the player will put the ghost in the bank

Task 14: a nested for loop has been made for the x and y coordinate respectfully and an IF statement has been coded to check three levels arrays using the x and y coordinate loop if three of the level did not have a (not equal to) bank, wall and breaches, a point variable is created to set a new point on the X and Y coordinate which will help to add spawn point.

Task 15: an IF statement has been coded that if the level is empty and the player object was displayed carrying a ghost a nested for loop has been made to scan the whole level array for breaches so if a breach is found a add ghost array which is linked to the breach would spawn in new ghosts max energy but different X and Y coordinate using the nested loops, and a level array using both integers from the nested loops is going to display the breaches which are sealed to floor1 


