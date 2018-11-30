# EightPuzzle
Eight puzzle is a single player game. It is a sliding puzzle that consists of a frame of numbered square tiles in random order with one tile missing where you try to put them in order.
This is an Eight Puzzle Solver that can solve any solvable puzzle.
 You can choose to solve the puzzle using any of three algorithms:
 - BFS
 - DFS
 - A* (two heuristic available)
   - Euclidean Heuristic
   - Manhattan Heuristic
 
![UI_START](/screenshots/1.png?raw=true)
The solver guides you **step by step** to solve the puzzle and reports the followind data:
- Steps to solve the puzzle
- Number of states expanded to find the solution
- Search depth to find the solution
- Running time of the algorithm to find the solution
![Solving_1](/screenshots/2.png?raw=true)
![Solving_2](/screenshots/3.png?raw=true)

### Detects invalid input
![INVALID_INPUT](/screenshots/4.png?raw=true)

### Detects unsolvable puzzle
If the puzzle is not solvable (odd number of inversions) the solver will report it as follows.
![UNSOLVABLE_PUZZLE](/screenshots/5.png?raw=true)
