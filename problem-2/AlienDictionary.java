import java.util.*;

class AlienDictionary {

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjMap = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize the graph with all unique characters as nodes and indegree 0
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
                adjMap.put(c, new ArrayList<>());
            }
        }

        // Build the graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLen = Math.min(word1.length(), word2.length());

            // Check for invalid order (e.g., "abc" after "ab")
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }

            // Find the first different character and create an edge
            for (int j = 0; j < minLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (!adjMap.get(c1).contains(c2)) {
                        adjMap.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // Topological Sort
        Queue<Character> queue = new LinkedList<>();
        for (Character c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();
        int count = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            result.append(c);
            count++;

            for (Character neighbor : adjMap.get(c)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if all characters are included in the result
        if (count != inDegree.size()) {
            return "";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();

        // Test case 1
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println("Test Case 1: " + solution.alienOrder(words1)); // Expected output: bdac

        // Test case 2
        String[] words2 = {"caa", "aaa", "aab"};
        System.out.println("Test Case 2: " + solution.alienOrder(words2)); // Expected output: cab

        // Test case 3
        String[] words3 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Test Case 3: " + solution.alienOrder(words3)); // Expected output: wertf

        // Test case 4: Invalid order
        String[] words4 = {"abc", "ab"};
        System.out.println("Test Case 4: " + solution.alienOrder(words4)); // Expected output: ""

        // Test case 5: Single word
        String[] words5 = {"z", "x"};
        System.out.println("Test Case 5: " + solution.alienOrder(words5)); // Expected output: zx
    }
}