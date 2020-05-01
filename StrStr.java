import java.util.Objects;

/**
 * Implement strstr(3). Returns the index of needle in haystack; -1 if not found.
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr ss = new StrStr();
        test(ss, "hello", "ll", 2);
        test(ss, "aaaaaa", "bba", -1);
        test(ss, "abcabcd", "abcd", 3);
    }
    
    // Poor man's version of unit testing
    private static void test(StrStr ss, String haystack, String needle, int expected) {
        int result = ss.strStr(haystack, needle);

        System.out.println("Input: haystack=" + haystack + ", needle=" + needle);
        if (Objects.equals(expected, result)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
            System.out.print("Expected: " + expected);
            System.out.print("Got: " + result);
        }
    }

    public StrStr() {}

    public int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() <= 0) {
            throw new IllegalArgumentException("haystack is empty, please provide a non-empty haystack");
        }
        if (needle.length() <= 0) {
            throw new IllegalArgumentException("needle is empty, please provide a non-empty needle");
        }

        char firstNeedle = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            char c = haystack.charAt(i);

            if (c == firstNeedle) {
                int numMatched = 0;
                for (int j=0, k=i; j < needle.length() && k < haystack.length(); j++, k++) {
                    if (haystack.charAt(k) == needle.charAt(j)) {
                        ++numMatched;
                    }
                }
                if (numMatched == needle.length()) {
                    return i;
                }
            }
        }

        return -1;
    }

}
