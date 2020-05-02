import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.Arrays;

/**
 * I believe this was part of the corpus of Steve Yegge's blog on interviews.
 * One of his comments was that this question tests for "common sense" in how
 * Roman numerals work. I guess I don't have as much common sense; I vaguely
 * knew how they worked (enough to decode years in the 90s from watching
 * sports), but it was actually interesting to learn the precise details. This
 * is one of those questions where the structure of the code is not obvious
 * initially, but the need for refactoring is quickly seen.
 * 
 * https://leetcode.com/problems/roman-to-integer/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * 
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * 
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 */
public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger ri = new RomanToInteger();

        test(ri, "III", 3);
        test(ri, "IV", 4);
        test(ri, "LVIII", 58);
        test(ri, "MCMXCIV", 1994);
    }

    // Poor man's version of unit testing
    private static void test(RomanToInteger ri, String input, int expected) {
        int output = ri.romanToInt(input);

        System.out.println("Input: " + input);

        if (Objects.equals(output, expected)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + output);
        }
    }

    private final static char NUMERAL_1 = 'I';
    private final static char NUMERAL_5 = 'V';
    private final static char NUMERAL_10 = 'X';
    private final static char NUMERAL_50 = 'L';
    private final static char NUMERAL_100 = 'C';
    private final static char NUMERAL_500 = 'D';
    private final static char NUMERAL_1000 = 'M';

    private final static Set<Character> VALID_CHARS = new HashSet<>(Arrays.asList(
        NUMERAL_1,
        NUMERAL_5,
        NUMERAL_10,
        NUMERAL_50,
        NUMERAL_100,
        NUMERAL_500,
        NUMERAL_1000
    ));

    /**
     * Convert the provided string into a Roman numeral.
     */
    public int romanToInt(String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException("Invalid characters in input");
        }

        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (i < length - 1) {
                char peek = s.charAt(i + 1);

                int special = specialCasePairs(c, peek);
                if (special > 0) {
                    result += special;
                    i++;
                    continue;
                }
            }

            if (c == NUMERAL_1) { result += 1; }
            if (c == NUMERAL_5) { result += 5; }
            if (c == NUMERAL_10) { result += 10; }
            if (c == NUMERAL_50) { result += 50; }
            if (c == NUMERAL_100) { result += 100; }
            if (c == NUMERAL_500) { result += 500; }
            if (c == NUMERAL_1000) { result += 1000; }
        }

        return result;
    }

    /**
     * Given a pair of characters, should they be treated as a special case?
     * If so, return the value. If not, return 0. 
     */
    private int specialCasePairs(char c, char peek) {
        if (c == NUMERAL_1 && peek == NUMERAL_5) { return 4; }
        if (c == NUMERAL_1 && peek == NUMERAL_10) { return 9; }
        if (c == NUMERAL_10 && peek == NUMERAL_50) { return 40; }
        if (c == NUMERAL_10 && peek == NUMERAL_100) { return 90; }
        if (c == NUMERAL_100 && peek == NUMERAL_500) { return 400; }
        if (c == NUMERAL_100 && peek == NUMERAL_1000) { return 900; }
        return 0;
    }

    /**
     * Do we have valid input? We don't know how to handle non-Roman numeral characters.
     */
    private boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!VALID_CHARS.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
