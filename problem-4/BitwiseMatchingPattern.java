/**
 * BitwiseMatchingPattern.java
 * 
 * Given a number n, find the next bigger number that has the same number of 1s in binary.
 * If there is no bigger number with the same number of 1s, return -1.
 */
public class BitwiseMatchingPattern {

    /**
     * This method finds the next bigger number with the same count of 1 bits.
     * 
     * @param n the input number
     * @return next bigger number with same number of 1s, or -1 if not possible
     */
    public static int nextLargerWithSameSetBits(int n) {
        if (n == 0) return -1; // no bigger number if n is 0

        int c = n;
        int c0 = 0; // count of zeros on the right
        int c1 = 0; // count of ones right after those zeros

        // count how many zeros at the right side
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // count how many ones come after those zeros
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // if all bits are 1s followed by zeros or no zeros at all, no bigger number possible
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        // position of the rightmost zero that we can flip to 1
        int p = c0 + c1;

        // flip that zero to 1
        n |= (1 << p);

        // clear all bits to the right of position p
        n &= ~((1 << p) - 1);

        // put (c1-1) ones at the right side to make the number smallest possible
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    // helper method to show binary string of a number
    public static String toBinary(int n) {
        return Integer.toBinaryString(n);
    }

    // test the method with some examples
    public static void main(String[] args) {
        int[] testCases = {1, 2, 3, 5, 6, 7, 8, 12, 15, 21, 31, 124, 0};
        for (int n : testCases) {
            int next = nextLargerWithSameSetBits(n);
            System.out.printf("n = %d (%s) -> next = %d (%s)%n",
                    n, toBinary(n), next, next == -1 ? "N/A" : toBinary(next));
        }
    }
}
