# Problem 2: Alien Dictionary

## Problem Statement

Given a sorted list of words from an alien language, determine the character order used in that language.

### Example

Input:  
`["wrt", "wrf", "er", "ett", "rftt"]`  
Output:  
`"wertf"`

---

## Instructions

- You may use any programming language you're comfortable with.
- Code should be clean, modular, and properly commented.
- Include test cases and sample input/output for each problem.
- Plagiarized content will lead to disqualification.
- Include a README if there are special setup instructions.

---

## Solution Approach

This problem is solved using **Topological Sorting**. Here’s how the solution works:

1. **Graph Initialization**:  
   Create an adjacency list for all unique characters in the list of words and initialize their in-degrees to 0.

2. **Graph Construction**:  
   Compare each pair of adjacent words. For the first mismatched character between two words, create a directed edge from the first word’s character to the second’s. Increment the in-degree of the destination character.

3. **Cycle Detection**:  
   If a prefix word appears after a longer word with the same prefix (e.g., "abc" after "ab"), return an empty string (invalid case).

4. **Topological Sort (BFS)**:  
   Add all characters with zero in-degree to a queue. Repeatedly process the queue, appending characters to the result and reducing the in-degree of their neighbors. If any neighbor's in-degree reaches zero, add it to the queue.

5. **Validation**:  
   If the result does not include all characters, it indicates a cycle in the graph — return an empty string.

---

## How to Run the Code

### Requirements:
- Java 8 or above

### Steps:
1. Save the code in a file named `AlienDictionary.java`.
2. Compile the code using:
   ```bash
   javac AlienDictionary.java
