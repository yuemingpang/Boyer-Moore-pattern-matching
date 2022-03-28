import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        List<Integer> list = new ArrayList<Integer>();
        int m = pattern.length();
        int n = text.length();
        if (m>n) {return list;}
        
        Map<Character, Integer> last = buildLastTable(pattern);
        int i = 0;
        while (i<=n-m) {
            int j = m-1;
            while (j>= 0 && comparator.compare(text.charAt(i+j), pattern.charAt(j)) == 0) {
                j -= 1;
            }
            if (j == -1) {
                list.add(i);
                i++;
            }
            else {
                int shift = last.getOrDefault(text.charAt(i+j), -1);
                if (shift < j) {i += j-shift;}
                else {i++;}
            }
        }
        return list;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * If the pattern is empty, return an empty map.
     * assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        int m = pattern.length();
        Map<Character, Integer> last = new HashMap<Character, Integer>();
        for (int i = 0; i< m; i++) {
            last.put(pattern.charAt(i), i);
        }
        return last;
    }
}