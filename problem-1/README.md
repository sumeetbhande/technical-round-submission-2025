# Problem 1 – Sudoku Validator With Custom Zones

## Problem Statement

You are given a 9x9 Sudoku board. Along with the standard validation (rows, columns, and 3x3 subgrids), you must also validate a set of custom zones. Each custom zone is a set of 9 unique cells on the board. The goal is to ensure that every row, column, subgrid, and custom zone contains all digits from 1 to 9 without repetition.

---

## Approach & Explanation

This problem is solved using Java. The logic is split into separate validation methods to keep the code clean, modular, and readable:

- **`validateRows()`** – checks that each row has unique digits from 1 to 9.
- **`validateColumns()`** – checks that each column has unique digits.
- **`validateSubBoxes()`** – checks each 3x3 subgrid.
- **`validateCustomZones()`** – checks each custom-defined zone (received as a 3D array of coordinates).
- **`isUnique()`** – helper function that checks if a group of 9 values are unique and valid (1–9).

A sample test runner (`main` method) is included to demonstrate the validation on both valid and invalid boards.

---

## How to Run

### Prerequisites
- Java installed and properly configured in your system path.

### Steps
1. Open terminal or command prompt.
2. Navigate to the `problem-1` folder.
3. Compile the program:
   ```bash
   javac SudokuValidator.java
