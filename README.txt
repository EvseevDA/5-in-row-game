General description.
This project is a game "Five in a row". The game "five in a row" is a natural development of the usual "tic-tac-toe". Two people still play, on checkered paper, taking turns placing X's and O's in the cells of the playing field. There are only two differences:

The playing field is now “infinite”, that is, limited only by the size of a sheet of paper (and not 3x3, as in regular “tic-tac-toe”);
To win, you need to line up a row of five tic-tac-toe horizontally, vertically or diagonally (not three, as in regular tic-tac-toe). This is a 5-in-a-row game.

Description of the implemented version of the game in the project.
Field size.
From the description of the game you can find out that the field should be infinite. Unfortunately, computer RAM is not infinite, so you have to set limits on the field size. After conducting several tests, it turned out that the optimal field size is 10x10, which is also not bad, because the game on such a field can last quite a long time, especially relative to the classic tic-tac-toe.

Game modes.
In this version of the project it is possible to play only against a bot, but in future versions it is planned to add a player versus player mode.

Algorithms used in implementation.
Regarding the bot, its moves are calculated using the Alpha-Beta algorithm. This algorithm is based on the Minimax algorithm and is in fact its optimization. A more detailed description of Minimax can be seen at the link: https://habr.com/ru/articles/329058/. After this introduction, it is easy to understand the basic concept of the alpha-beta algorithm: Alpha-beta pruning is a search algorithm that seeks to reduce the number of nodes evaluated in the search tree by the minimax algorithm. Designed for antagonistic games and used for machine games (in computer chess, computer Go and others). Those. As mentioned above, alpha-beta is a minimax optimization. Optimization occurs due to the fact that some branches of the search tree are pruned based on the branches already considered.

User interface.
The user interface in this version of the game is console. Since this solution is not always convenient, steps have been taken to make the interaction between the player and the game itself easier:
1) The rows and columns of the field are numbered.
2) For each user input, a hint is displayed (for example, when you need to make a move, the hint will look like this: "Enter the coordinates (row number first, then column number): ").
3) In any situation where a selection menu is provided, the items from the menu are numbered. For example, the inscription that appears on the screen at the end of the game:
"1. Play again
2. Exit


Enter your choose: ".
4) Incorrect user input has been processed with the option to re-enter. For example, the inscription displayed when entering coordinates outside the field:
"Enter the coordinates (row number first, then column number): 5 11
Coordinates out of range [1, 10].


Enter the coordinates (row number first, then column number): ".

Conclusion.
This version of the project implements the minimum necessary to be able to play “5 in a row”, but I don’t plan to stop there, and the following tasks include:
1) Add a player versus player game mode.
2) Add a graphical user interface.
