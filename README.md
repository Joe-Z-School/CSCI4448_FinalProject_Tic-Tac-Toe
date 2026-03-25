# CSCI4448_FinalProject_Tic-Tac-Toe
Final project for CSCI 4448 - Object-Oriented Analysis and Design with a Tic-Tac-Toe game that only ends when a winning placement is made.


## Mid-Project Review:

#### The four patterns I will be using for the application are:
* Factory Pattern:
    - The factory pattern will be used in the creation of the different types of players present in the game. A PlayerFactory class will be utilized to create and pass player objects to the abstract Player class.
* Observer Pattern:
    - The observer pattern will be utilized to update the visual board upon each placement of a symbol by a player.
* Strategy Pattern:
    - The strategy pattern will be used for the different types of symbol placement available such as if the symbol will be placed by a human player, or if the symbols will be placed using an algorithm by the computer player.
* 4th Pattern:
    - TBA


## The project should:

    - You are graded on the code that you write
    - Must use and identify four(4) design patterns
    - You should use dependency injection where possible
    - Code to abstractions
    - Treat things polymorphically
        * Generally means, no big if-then-else or switch statements
    - Must have either a UI or some form of persisted state (ORM, file, database)

## Design Patterns Available:
* Factory
* Builder
* Observer
* Facade
* Adapter
* Template
* Singleton
* Strategy
* Command


## Game Description
A Tic-Tac-Toe game but not in a normal sense. For my
Tic-Tac-Toe game which can be played either solo against the PC, or as a 2
player game where you can play another individual. As for what is different, I
would like to make it so there are at maximum 3 of any “symbol” on the board at
one given time. Instead of each player placing either the X or the O until one wins
or until the board fills for a tie, as a player achieves 3 symbols on the board, once
they place the 4th symbol, their previously placed first symbol is removed and
that spot is now free. As the game progresses, previously placed symbols are
removed until eventually a player connects 3 in a row for a win. In a sense, the
game logic would be similar to a linked list with a maximum of 3 entities. As a 4th
item enters the list, the “head” of the list is removed. This results in never being a
tie situation.

