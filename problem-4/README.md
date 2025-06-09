# Problem 4: Bitwise Matching Pattern

## Problem Statement

Given an integer `n`, return the **next larger number** that has the **same number of 1s in its binary representation**.  
If such a number is not possible, return `-1`.

---

## Approach

This program finds the next bigger number with the same number of set bits (`1`s) in binary format by using bit manipulation.

### Steps:
1. Count the number of trailing zeros (`c0`) and the number of ones (`c1`) just after them.
2. If the number is like `11110000...` or has no zero to flip, return `-1` as there's no bigger number with the same number of ones.
3. Find the rightmost non-trailing zero and flip it to `1`.
4. Clear everything to the right of that bit.
5. Insert `(c1 - 1)` ones at the far right to make the number as small as possible but still larger than the original.

---

## How to Run

### Requirements
- Java installed (JDK 8 or above)

### Steps

1. Save the code in a file called **`BitwiseMatchingPattern.java`**.
2. Open terminal or command prompt.
3. Compile the code:
   ```bash
   javac BitwiseMatchingPattern.java
   ```
4. Run the program:
   ```bash
   java BitwiseMatchingPattern
   ```

You’ll see the original number, its binary format, and the next larger number with the same number of `1`s (or `-1` if not possible).

---

## Sample Output

```
n = 1 (1) -> next = 2 (10)
n = 2 (10) -> next = 4 (100)
n = 3 (11) -> next = 5 (101)
n = 5 (101) -> next = 6 (110)
n = 6 (110) -> next = 9 (1001)
n = 7 (111) -> next = 11 (1011)
n = 8 (1000) -> next = 16 (10000)
n = 12 (1100) -> next = 17 (10001)
n = 15 (1111) -> next = 23 (10111)
n = 21 (10101) -> next = 22 (10110)
n = 31 (11111) -> next = 47 (101111)
n = 124 (1111100) -> next = 131 (10000011)
n = 0 (0) -> next = -1 (N/A)
```

---

## Notes

- This uses only bitwise operations, so it runs very fast.
- It only works for positive integers.
- Handles edge cases like when no higher number exists.
