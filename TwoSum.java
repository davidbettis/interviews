import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * "Given an array of integers, return indices of the two
 * numbers such that they add up to a specific target."
 *
 * https://leetcode.com/problems/two-sum/
 *
 * When I worked at Amazon, this was the de-facto interview question I used for
 * yearrrs. At some point, it became the "typical Amazon interview" question,
 * and I stopped. A variant of this question is "Given an array of unsigned
 * integers, what are the first two numbers that add to 10?" The "first" is
 * ambiguous, but say it's to minimize the indices. In that case, a hash table
 * can be replaced with a simple array (since 10 is the max). You can stop the
 * first time you see a valid pair.
 */
public class TwoSum {

    public static void main(String[] args) {

        TwoSum ts = new TwoSum();
    
        test(ts, new int[]{ 1, 3, 5, 7, 9 }, 10, new int[]{ 1, 3 });
        test(ts, new int[]{ 5, 5 }, 10, new int[]{ 0, 1 });
        test(ts, new int[]{ 0, 1, 2, 3, 4, 5 }, 100, null);

        boolean caughtException = false;
        try {
            test(ts, null, 100, null);
        } catch (IllegalArgumentException e) {
            caughtException = true;
            System.out.println("PASSED");
        }
        if (!caughtException) {
            System.out.println("FAILED - exception expected");
        }
    }

    // Poor man's version of unit testing
    private static void test(TwoSum ts, int[] arr, int target, int[] expected) {
        int[] result = ts.twoSum(arr, target);

        System.out.print("Input: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(", ");
        }
        System.out.print(" target=" + target);
        System.out.println();

        if (Arrays.equals(expected, result)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
            System.out.print("Expected: ");
            printArray(expected);
            System.out.println();
            System.out.print("Got: ");
            printArray(result);
            System.out.println();
        }
    }

    private static void printArray(int[] arr) {
        if (arr != null) {
            System.out.print("[");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                System.out.print(", "); // yep, extra , - that's OK :)
            }
            System.out.print("]");
        } else {
            System.out.print("null");
        }
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("nums must be non-null");
        }
        Map<Integer, Integer> table = new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            int delta = target - nums[i];
            if (table.containsKey(delta)) {
                int index = table.get(delta);
                return new int[]{ index, i };
            }
            if (!table.containsKey(nums[i])) {
                table.put(nums[i], i);
            }
        }
        return null;
    }
}
