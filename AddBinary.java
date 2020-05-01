import java.util.List;
import java.util.ArrayList;

/**
 * Add the binary representation of two strings.
 */
public class AddBinary {
    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        System.out.println(ab.addBinary("11", "1"));
        System.out.println(ab.addBinary("1010", "1011"));
    }

    public AddBinary() {
    }

    public String addBinary(String a, String b) {
        if (a == null || a.length() <= 0) {
            throw new IllegalArgumentException("a must be non-empty");
        }
        if (b == null || b.length() <= 0) {
            throw new IllegalArgumentException("b must be non-empty");
        }

        List<String> result = new ArrayList<>();

        boolean carry = false;
        int p = a.length() - 1;
        int q = b.length() - 1;

        // If either number has characters remaining, we continue to loop. We
        // must be careful to advance the loop's state depending on which one
        // is true.
        while (p >= 0 || q >= 0) {
            int sum = 0;
            if (p >= 0) {
                if (a.charAt(p) == '1') {
                    sum++;
                }
                p--;
            }
            if (q >= 0) {
                if (b.charAt(q) == '1') {
                    sum++;
                }
                q--;
            }
            if (carry) {
                sum++;
                carry = false;
            }

            switch(sum) {
                case 0:
                    result.add("0");
                    break;
                case 1:
                    result.add("1");
                    break;
                case 2:
                    result.add("0");
                    carry = true;
                    break;
                case 3:
                    result.add("1");
                    carry = true;
                    break;
            }
        }
        if (carry) {
            result.add("1");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; --i) {
            sb.append(result.get(i));
        }
        return sb.toString();
    }
}
