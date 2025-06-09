import java.util.*;

// Find shortest path from top-left to bottom-right with one teleport allowed
public class KnightsAndPortals {
    
    // move up, down, left, right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    // keep track of position and if teleport was used
    static class State {
        int row, col, distance;
        boolean usedTeleport;
        
        State(int row, int col, int distance, boolean usedTeleport) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.usedTeleport = usedTeleport;
        }
    }
    
    public static int findShortestPath(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // start or end blocked
        if (grid[0][0] == '#' || grid[rows-1][cols-1] == '#') {
            return -1;
        }
        
        // already at end
        if (rows == 1 && cols == 1) {
            return 0;
        }
        
        // use priority queue to get shortest path first
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
        Set<String> visited = new HashSet<>();
        
        pq.offer(new State(0, 0, 0, false));
        
        while (!pq.isEmpty()) {
            State current = pq.poll();
            
            String key = current.row + "," + current.col + "," + current.usedTeleport;
            
            if (visited.contains(key)) {
                continue;
            }
            visited.add(key);
            
            // reached destination
            if (current.row == rows - 1 && current.col == cols - 1) {
                return current.distance;
            }
            
            // try normal moves to adjacent cells
            for (int[] dir : DIRECTIONS) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                
                if (isValid(grid, newRow, newCol)) {
                    State next = new State(newRow, newCol, current.distance + 1, current.usedTeleport);
                    String nextKey = next.row + "," + next.col + "," + next.usedTeleport;
                    
                    if (!visited.contains(nextKey)) {
                        pq.offer(next);
                    }
                }
            }
            
            // try teleport if not used yet
            if (!current.usedTeleport) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (grid[i][j] == '.' && (i != current.row || j != current.col)) {
                            State teleport = new State(i, j, current.distance + 1, true);
                            String teleportKey = teleport.row + "," + teleport.col + "," + teleport.usedTeleport;
                            
                            if (!visited.contains(teleportKey)) {
                                pq.offer(teleport);
                            }
                        }
                    }
                }
            }
        }
        
        return -1; // no path found
    }
    
    // check if cell is inside grid and not blocked
    private static boolean isValid(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && 
               col >= 0 && col < grid[0].length && 
               grid[row][col] == '.';
    }
    
    public static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    
    public static void runTests() {
        System.out.println("=== Test Cases ===\n");
        
        // simple grid
        System.out.println("Test 1: Simple 3x3 grid");
        char[][] grid1 = {
            {'.', '.', '.'},
            {'.', '#', '.'},
            {'.', '.', '.'}
        };
        System.out.println("Grid:");
        printGrid(grid1);
        int result1 = findShortestPath(grid1);
        System.out.println("Result: " + result1);
        System.out.println("Can teleport directly to end\n");
        
        // big obstacle in middle
        System.out.println("Test 2: Big obstacle");
        char[][] grid2 = {
            {'.', '#', '#', '#', '.'},
            {'.', '#', '#', '#', '.'},
            {'.', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '.'}
        };
        System.out.println("Grid:");
        printGrid(grid2);
        int result2 = findShortestPath(grid2);
        System.out.println("Result: " + result2);
        System.out.println("Teleport saves many steps\n");
        
        // need teleport to connect areas
        System.out.println("Test 3: Disconnected areas");
        char[][] grid3 = {
            {'.', '#'},
            {'#', '.'}
        };
        System.out.println("Grid:");
        printGrid(grid3);
        int result3 = findShortestPath(grid3);
        System.out.println("Result: " + result3);
        System.out.println("Must teleport to reach end\n");
        
        // just one cell
        System.out.println("Test 4: Single cell");
        char[][] grid4 = {
            {'.'}
        };
        System.out.println("Grid:");
        printGrid(grid4);
        int result4 = findShortestPath(grid4);
        System.out.println("Result: " + result4);
        System.out.println("Already at end\n");
        
        // complex maze
        System.out.println("Test 5: Maze");
        char[][] grid5 = {
            {'.', '.', '#', '#', '#'},
            {'#', '.', '#', '.', '.'},
            {'#', '.', '#', '.', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '#', '#', '.', '.'}
        };
        System.out.println("Grid:");
        printGrid(grid5);
        int result5 = findShortestPath(grid5);
        System.out.println("Result: " + result5);
        System.out.println("Best path with teleport\n");
    }
    
    public static void main(String[] args) {
        runTests();
        
        System.out.println("=== Try your own grid ===");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter rows and cols: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();
        
        char[][] grid = new char[rows][cols];
        System.out.println("Enter grid (. for empty, # for wall):");
        
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < cols && j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        System.out.println("\nYour grid:");
        printGrid(grid);
        
        int result = findShortestPath(grid);
        System.out.println("Shortest path: " + result);
        
        if (result == -1) {
            System.out.println("No path possible");
        } else {
            System.out.println("Found path in " + result + " steps");
        }
        
        scanner.close();
    }
}