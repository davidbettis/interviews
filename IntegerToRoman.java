import java.util.Objects;

/**
 * The inverse of RomanToInteger
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
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be
 * within the range from 1 to 3999.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        IntegerToRoman ir = new IntegerToRoman();

        test(ir, 3, "III");
        test(ir, 5, "V");
        test(ir, 9, "IX");
        test(ir, 13, "XIII");
        test(ir, 24, "XXIV");
        test(ir, 1995, "MCMXCV");
        test(ir, 3999, "MMMCMXCIX");
    }

    // Poor man's version of unit testing
    private static void test(IntegerToRoman ir, int input, String expected) {
        String output = ir.intToRoman(input);

        System.out.println("Input: " + input);

        if (Objects.equals(output, expected)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + output);
        }
    }

    public String intToRoman(int num) {
        if (num <= 0 || num > 3999) {
            throw new RuntimeException("Invalid num, must be in the range [1,3999]");
        }

        int ones = num % 10;
        num /= 10;
        int tens = num % 10;
        num /= 10;
        int hundreds = num % 10;
        num /= 10;
        int thousands = num % 10;
        num /= 10;

        StringBuilder sb = new StringBuilder();
        if (thousands > 0) {
            sb.append(parseNumeral(thousands, "M", "", ""));
        }
        if (hundreds > 0) {
            sb.append(parseNumeral(hundreds, "C", "D", "M"));
        }
        if (tens > 0) {
            sb.append(parseNumeral(tens, "X", "L", "C"));
        }
        if (ones > 0) {
            sb.append(parseNumeral(ones, "I", "V", "X"));
        }
        return sb.toString();
    }

    /**
     * The pattern for parsing Roman numerals.
     */
    private String parseNumeral(int num, String first, String second, String third) {
        if (num == 1) { return first; }
        if (num == 2) { return first + first; }
        if (num == 3) { return first + first + first; }
        if (num == 4) { return first + second; }
        if (num == 5) { return second; }
        if (num == 6) { return second + first; }
        if (num == 7) { return second + first + first; }
        if (num == 8) { return second + first + first + first; }
        if (num == 9) { return first + third; }
        return "";
    }
}
