# Problem 3: Knights and Portals

## Problem Statement

Given a grid represented by a 2D character array, find the shortest path from the **top-left cell `(0,0)`** to the **bottom-right cell `(m-1,n-1)`**. The grid contains:

- `'.'` — an empty cell you can walk on,
- `'#'` — a blocked cell you cannot enter.

You can move **up, down, left, or right** one step at a time. Additionally, you are allowed to **teleport exactly once** between any two empty cells during your path. The goal is to minimize the total steps taken.

Return the length of the shortest path or `-1` if no such path exists.

---

## Solution Approach

This solution uses a **modified Dijkstra's algorithm** (via a priority queue) with a state that tracks:

- Current position `(row, col)`
- Distance traveled so far
- Whether the teleport has already been used (`boolean usedTeleport`)

### Key details:

- From each cell, explore adjacent valid cells (`'.'`).
- If teleport is unused, consider teleporting to any other empty cell in the grid.
- Use a `visited` set to avoid revisiting the same state (same cell with same teleport usage status).
- The priority queue ensures exploration of shortest distance states first.
- Return the distance when the bottom-right cell is reached.

---

## How to Run

### Requirements

- Java 8 or higher

### Steps

1. Save the code to a file named `KnightsAndPortals.java`.
2. Compile:
   ```bash
   javac KnightsAndPortals.java
