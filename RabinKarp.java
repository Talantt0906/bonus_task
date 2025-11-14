import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Rabin-Karp algorithm for string matching using a polynomial rolling hash.
 */
public class RabinKarp {

    /**
     * A large prime number to be used as the modulus for all hash calculations.
     * Using a large prime helps in distributing hash values more uniformly
     * and significantly reduces the probability of hash collisions.
     * 1,000,000,007 is a commonly used large prime.
     */
    private final long PRIME = 1000000007;

    /**
     * The base (or radix) to be used in the polynomial hash function.
     * This value should be greater than the size of the character set.
     * For example, if the text only contains lowercase English letters (26),
     * a base of 29 (a prime > 26) would be good.
     * We use 257 (a prime > 256) to safely accommodate all 8-bit ASCII characters.
     */
    private final int BASE = 257;

    /**
     * Searches for all occurrences of a pattern within a text using the Rabin-Karp algorithm.
     *
     * @param text    The text string in which to search.
     * @param pattern The pattern string to search for.
     * @return A List of Integers, where each integer is the starting index of
     * an occurrence of the pattern in the text.
     */
    public List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        
        int n = text.length();
        int m = pattern.length();

        // Edge case: If the pattern is longer than the text, no match is possible.
        // Also handles empty strings.
        if (m > n || m == 0) {
            return occurrences;
        }

        // --- Step 1: Pre-computation ---

        // 'H' will store the value of (BASE^(m-1)) % PRIME.
        // This is the "power" of the most significant character in the m-length window.
        // It's needed to efficiently remove the leading character's contribution
        // when rolling the hash.
        long H = 1;
        for (int i = 0; i < m - 1; i++) {
            H = (H * BASE) % PRIME;
        }

        // --- Step 2: Calculate initial hashes ---
        
        // Calculate the hash value for the pattern (patternHash)
        // and the hash value for the first m-length window of the text (textHash).
        long patternHash = 0;
        long textHash = 0;
        
        for (int i = 0; i < m; i++) {
            // Hash formula: (oldHash * BASE + newChar) % PRIME
            patternHash = (patternHash * BASE + pattern.charAt(i)) % PRIME;
            textHash = (textHash * BASE + text.charAt(i)) % PRIME;
        }

        // --- Step 3: Slide the window across the text ---
        
        // Iterate from i = 0 up to (n - m), which is the last possible starting
        // index for the pattern to fit in the text.
        for (int i = 0; i <= n - m; i++) {

            // --- Step 3a: Check for a match ---
            
            // If the hash values are equal, we *might* have a match.
            // This could be a "spurious hit" (a hash collision).
            if (patternHash == textHash) {
                // To confirm, we must do a character-by-character comparison.
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                // If all characters matched, it's a genuine occurrence.
                if (match) {
                    occurrences.add(i);
                }
            }

            // --- Step 3b: Calculate the hash for the next window (Roll the hash) ---
            
            // We only need to compute the next hash if we are not at the end of the text.
            if (i < n - m) {
                // 1. Remove the leading character's contribution.
                //    (text.charAt(i) * H) is the hash contribution of the first character.
                long leadingCharHash = (text.charAt(i) * H) % PRIME;
                textHash = (textHash - leadingCharHash);
                
                // We add PRIME to ensure the result is non-negative after subtraction.
                // (a - b) % p = (a - b + p) % p
                textHash = (textHash + PRIME) % PRIME;

                // 2. "Shift" the remaining hash one position to the left by multiplying by BASE.
                textHash = (textHash * BASE) % PRIME;

                // 3. Add the new trailing character's contribution.
                //    The new character is at index (i + m).
                textHash = (textHash + text.charAt(i + m)) % PRIME;
            }
        }

        return occurrences;
    }

    /**
     * Main method to test the RabinKarp implementation.
     * This satisfies the requirement to test with three different string lengths.
     */
    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp();

        System.out.println("--- Rabin-Karp Algorithm Test Cases ---");

        // --- Test 1: Short String ---
        // Multiple occurrences
        String text1 = "abracadabra";
        String pattern1 = "abra";
        System.out.println("\nTest 1: Short String");
        System.out.println("Text:    \"" + text1 + "\"");
        System.out.println("Pattern: \"" + pattern1 + "\"");
        System.out.println("Found at indices: " + rk.search(text1, pattern1)); // Expected: [0, 7]

        // --- Test 2: Medium String ---
        // Single occurrence
        String text2 = "thequickbrownfoxjumpsoverthelazydog";
        String pattern2 = "fox";
        System.out.println("\nTest 2: Medium String");
        System.out.println("Text:    \"" + text2 + "\"");
        System.out.println("Pattern: \"" + pattern2 + "\"");
        System.out.println("Found at indices: " + rk.search(text2, pattern2)); // Expected: [16]

        // --- Test 3: Longer String ---
        // Multiple occurrences and overlapping pattern
        String text3 = "GCATCGCAGAGAGTATACAGTACG";
        String pattern3 = "GCAG";
        System.out.println("\nTest 3: Longer String");
        System.out.println("Text:    \"" + text3 + "\"");
        System.out.println("Pattern: \"" + pattern3 + "\"");
        System.out.println("Found at indices: " + rk.search(text3, pattern3)); // Expected: [5, 20]

        // --- Bonus Test 4: No Match ---
        String text4 = "hello world";
        String pattern4 = "goodbye";
        System.out.println("\nTest 4: No Match");
        System.out.println("Text:    \"" + text4 + "\"");
        System.out.println("Pattern: \"" + pattern4 + "\"");
        System.out.println("Found at indices: " + rk.search(text4, pattern4)); // Expected: []
    }
}