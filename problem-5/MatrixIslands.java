public class MatrixIslands {

    // These arrays help us check all 8 directions around a cell
    private static final int[] ROW_DIRS = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] COL_DIRS = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * This method counts how many islands are in the matrix.
     * Islands are groups of 1s connected horizontally, vertically, or diagonally.
     *
     * @param matrix the 2D array with 0s and 1s
     * @return number of islands found
     */
    public static int countIslands(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols]; // keep track of cells we already checked
        int islandCount = 0;

        // go through every cell in the matrix
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // if this cell is land and not visited yet, it's a new island
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    dfs(matrix, visited, row, col, rows, cols); // mark all connected land
                    islandCount++; // increase island count by one
                }
            }
        }
        return islandCount;
    }

    /**
     * This method visits all connected land cells starting from (row, col).
     * It uses DFS to explore all 8 directions.
     *
     * @param matrix the input matrix
     * @param visited which cells we already visited
     * @param row current row
     * @param col current column
     * @param rows total rows in matrix
     * @param cols total columns in matrix
     */
    private static void dfs(int[][] matrix, boolean[][] visited, int row, int col, int rows, int cols) {
        visited[row][col] = true; // mark current cell as visited

        // check all 8 neighbors
        for (int i = 0; i < 8; i++) {
            int newRow = row + ROW_DIRS[i];
            int newCol = col + COL_DIRS[i];

            // if neighbor is inside matrix, is land, and not visited yet, keep exploring
            if (isValid(newRow, newCol, rows, cols) &&
                matrix[newRow][newCol] == 1 &&
                !visited[newRow][newCol]) {
                dfs(matrix, visited, newRow, newCol, rows, cols);
            }
        }
    }

    /**
     * Check if the given row and column are inside the matrix boundaries.
     *
     * @param row row index
     * @param col column index
     * @param rows total rows
     * @param cols total columns
     * @return true if inside matrix, false otherwise
     */
    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Let's test the code with some examples
    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
        };
        System.out.println("Number of islands in matrix1: " + countIslands(matrix1)); // should print 5

        int[][] matrix2 = {
            {1, 1, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        System.out.println("Number of islands in matrix2: " + countIslands(matrix2)); // should print 1

        int[][] matrix3 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Number of islands in matrix3: " + countIslands(matrix3)); // should print 0
    }
}
