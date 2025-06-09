# Problem 6: Mini Interpreter

## Description

This is a small interpreter that can understand and run simple code lines like variable declarations and if statements.

It supports:
- Creating variables using `let`
- Checking conditions using `if` and printing messages

The interpreter is written in Java and uses basic string parsing to figure out what each line is doing.

## Features

- `let` statements like `let x = 10;` will save variables with values.
- `if` statements like `if (x > 5) { print("x is big"); }` will check the condition and run the print statement if it's true.
- Only basic operators are supported: `>`, `<`, `>=`, `<=`, `==`, `!=`
- Inside the if block, only `print()` is supported.

## How to Run

1. Compile the Java file:
```
javac MiniInterpreter.java
```

2. Run it:
```
java MiniInterpreter
```

You will see test output in the console.

## Example

```
let x = 10;
if (x > 5) { print("x is bigger than 5"); }
```

This will print:
```
x is bigger than 5
```

## Notes

- This is just a basic demo of how interpreters work.
- It doesn’t support complex expressions or multiple statements yet.
