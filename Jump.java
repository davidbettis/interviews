/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.  Each element in the array represents your maximum
 * jump length at that position.  Determine if you are able to reach the last
 * index.
 *
 * Pretty fun question! Use recursion to explore the possible states. Likely
 * can be optimized.
 * 
 * https://leetcode.com/problems/jump-game/
 */
public class Jump {

    public static void main(String[] args) {
        Jump j = new Jump();
        test(j, new int[]{ 2,3,1,1,4 }, true);
        test(j, new int[]{ 3,2,1,0,4 }, false);
    }

    private static void test(Jump j, int[] nums, boolean expected) {
        System.out.print("Input: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();

        boolean result = j.canJump(nums);
        if (result == expected) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED - expected " + expected + ", but got " + result);
        }
    }

    public Jump() {}


    /**
     */
    public boolean canJump(int[] nums) {
        boolean[] seen = new boolean[nums.length];
        return helper(nums, seen, 0);
    }

    private boolean helper(int[] nums, boolean[] seen, int pos) {
        int n = nums.length;

        // Make sure we're in a valid position and there are no loops.
        if (pos < 0 || pos >= n || seen[pos]) {
            return false;
        }

        // Success criteria for the game
        if (pos == n - 1) {
            return true;
        }

        seen[pos] = true;

        boolean ret = false;
        for (int i = nums[pos]; i >= 1; --i) {
            ret = ret || helper(nums, seen, pos+i);
        }

        return ret;
    }

}
