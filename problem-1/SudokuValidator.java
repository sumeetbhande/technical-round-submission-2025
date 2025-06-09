
public class SudokuValidator {

     /**
		 * This method checks if a given Sudoku board is valid.
		 * It verifies rows, columns, all 3x3 sub-boxes, and custom zones to make sure
		 * every group contains numbers from 1 to 9 without any repetition.
		 *
		 * @param board 9x9 Sudoku board filled with numbers from 1 to 9
		 * @param customZones 3D array where each custom zone has 9 cells, 
		 *                    and each cell is given as {row, col}
		 * @return true if the board is valid according to all rules, false if any rule is broken
	     */
        
    public boolean isValidSudoku(int[][] board, int[][][] customZones) {
        return validateRows(board) &&
               validateColumns(board) &&
               validateSubBoxes(board) &&
               validateCustomZones(board, customZones);
    }

    // Validate all rows contain unique digits 1-9
    private boolean validateRows(int[][] board) {
        for (int row = 0; row < 9; row++) {
            int[] cells = new int[9];
            for (int col = 0; col < 9; col++) {
                cells[col] = board[row][col];
            }
            if (!isUnique(cells)) return false;
        }
        return true;
    }

    // Validate all columns contain unique digits 1-9
    private boolean validateColumns(int[][] board) {
        for (int col = 0; col < 9; col++) {
            int[] cells = new int[9];
            for (int row = 0; row < 9; row++) {
                cells[row] = board[row][col];
            }
            if (!isUnique(cells)) return false;
        }
        return true;
    }

    // Validate each 3x3 sub-box contains unique digits 1-9
    private boolean validateSubBoxes(int[][] board) {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                int[] cells = new int[9];
                int index = 0;
                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        cells[index++] = board[row][col];
                    }
                }
                if (!isUnique(cells)) return false;
            }
        }
        return true;
    }

    // Validate each custom zone contains unique digits 1-9
    private boolean validateCustomZones(int[][] board, int[][][] customZones) {
        for (int[][] zone : customZones) {
            int[] cells = new int[9];
            for (int i = 0; i < 9; i++) {
                int row = zone[i][0];
                int col = zone[i][1];
                cells[i] = board[row][col];
            }
            if (!isUnique(cells)) return false;
        }
        return true;
    }

    // Helper method to check if the array contains digits 1-9 without repetition
    private boolean isUnique(int[] cells) {
        boolean[] seen = new boolean[10]; // Index 1-9 used
        for (int num : cells) {
            if (num < 1 || num > 9) return false; // Invalid digit
            if (seen[num]) return false; // Duplicate found
            seen[num] = true;
        }
        return true;
    }

    // Sample test runner
    public static void main(String[] args) {
        SudokuValidator validator = new SudokuValidator();

        int[][] sampleBoard = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };

        // Define custom zones as arrays of cell coordinates {row, col}
        int[][][] sampleCustomZones = {
            { {0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2} },
            { {3,3},{3,4},{3,5},{4,3},{4,4},{4,5},{5,3},{5,4},{5,5} },
            { {6,6},{6,7},{6,8},{7,6},{7,7},{7,8},{8,6},{8,7},{8,8} }
        };

        boolean isValid = validator.isValidSudoku(sampleBoard, sampleCustomZones);
        System.out.println("Sample board is valid: " + isValid); // Expected: true

        // Example invalid board (duplicate '5' in first row)
        int[][] invalidBoard = {
            {5,3,5,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };

        boolean isInvalid = validator.isValidSudoku(invalidBoard, sampleCustomZones);
        System.out.println("Invalid board is valid: " + isInvalid); // Expected: false
    }
}
