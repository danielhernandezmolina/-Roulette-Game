# -Roulette-Game

1- In this code, I've developed a roulette simulator using Java.  I've used selection statements (if-else, switch): to create menus of options and check if the user has won or lost. Additionally, money can be entered, and it will be added or subtracted depending on whether the user wins or loses.

2. Code Structure
I've organized the project into two main classes:
1. Main.java: This class contains the main method. Its sole function is to create a GameRoulette object and call the method to start the game. I've also added System.out.println("------
WELCOME TO THE WHEEL OF FORTUNE------"); so that when the user repeats the loop, they don't repeatedly encounter the start screen and instead jump directly to asking if they want to add funds.

2. GameRoulette.java: This is where all the logic resides. I've defined private attributes such as the balance (to store the player's money) and the Scanner (to read the keyboard). In addition to the methods:
`startGame()`,
`placeBet()`,
`spinRoulette()`, I've also added a small auxiliary method to check if the user's balance reaches 0 and ask if they want to reload to continue playing. I've called this method `checkBalance()`.

3. Step-by-Step Operation
1. Starting and Reloading the Balance
The first thing I programmed is the `startGame()` method, which requests the initial deposit using a do-while loop and, inside it, a try-catch block to force the user to enter a valid amount. Inside the loop:

• I use a try-catch block to catch errors if the user enters letters.

• I read the value with `balance = input.nextInt();` and store it in the variable `balance`.

• With an `if` statement, I check if the value is less than or equal to 0. If so, I display a message saying that it must be greater than 0, and the loop repeats.

2. The Core of the Game: Placing a Bet
Once we have a balance, we enter the placeAbet() method. This entire process is within a main do-while loop that keeps the game active.
Validating the Bet Amount: I use another loop to ask how much you want to bet. If you enter a negative amount or an amount greater than your balance, the program will ask you again..

3. Game Menu:
I offer a choice between two modes using a switch:
• Option 1 (Bet on a number): I ask for a number from 0 to 36. I have protected this input with a loop and a try-catch block to ensure that the number is valid and within the range. If the player guesses the number that comes up, I multiply their bet by 36 and add it to their balance.

• Option 2 (Even or Odd): Here, the validation only accepts 1 or 2. To determine if the player has won, I use the modulo operator (%). If the roulette number is divisible by 2 (therefore, the remainder of the operation is 1), it is EVEN; otherwise, it is ODD.

4. Spinning the Roulette Wheel
To simulate randomness, I have used the method provided in the assignment called
spinRoulette(). This uses Math.random() * 37 to return a random integer between 0 and 36 each time it's called.

5. Checking Balance
At the end of each game, I execute the helper method checkBalance().

• If the balance reaches 0 (or less), the program detects it.

• At that point, I ask the user if they want to reload. If they say yes, I request more funds by calling the startGame() method; if they say no, the game ends with a brief farewell message and displays the player's current balance.

• If they still have money left, I simply ask if they want to play another round.
