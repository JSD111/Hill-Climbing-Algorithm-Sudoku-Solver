## Overview
### The objective of this project is to find a solution to a given sudoku puzzle using one of the local search algorithms "hill climbing".

### Key Dependencies: Java

### Pseudocode

1. Start with random configuration by initializing each square 3*3 using distinct numbers.
2. Calculate the heuristic function for this problem by finding the total number of conflicts in each row and column. You need to call this function after you initialize the board.
3. Start to apply “hill climbing” algorithm by switching the numbers in each square, then you need to call the heuristic function to check if the value decreased or increased or no change. If it is the first case, keep the new configuration, if increased or no change stick with the old configuration.
4. Keep going by switching the numbers in each square and calculate the heuristic function till your heuristic function turns to zero. In case your program could not turn the heuristic to zero you can start with another random configuration and start over.
