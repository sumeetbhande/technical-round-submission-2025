# Problem 5: Matrix Islands with Diagonals

### Problem Statement
We are given a 2D matrix made up of 0s and 1s. An **island** is a group of connected 1s. A cell is considered connected to its neighbors if they are next to each other **horizontally, vertically, or diagonally**.  
The task is to count how many separate islands are there in the matrix.

### Example

For the matrix:

```
1 1 0 0 0  
0 1 0 0 1  
1 0 0 1 1  
0 0 0 0 0  
1 0 1 0 1  
```

There are **5 islands**.

### How It Works
The code uses Depth-First Search (DFS) to explore the matrix. It goes cell by cell, and when it finds a `1` that has not been visited yet, it starts exploring all 8 directions (up, down, left, right, and 4 diagonals).  
Each group of connected 1s is counted as one island.

### Files
- `MatrixIslands.java` – Contains the implementation of the island counting logic.

### How to Run
1. Make sure you have Java installed.
2. Save the code in a file called `MatrixIslands.java`.
3. Open a terminal or command prompt.
4. Compile the file:
   ```
   javac MatrixIslands.java
   ```
5. Run the program:
   ```
   java MatrixIslands
   ```

### Sample Output
```
Number of islands in matrix1: 5  
Number of islands in matrix2: 1  
Number of islands in matrix3: 0  
```

### Notes
- This solution considers diagonal connections too.
- The matrix can be of any size.
