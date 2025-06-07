3170. Lexicographically Minimum String After Removing Stars

class Solution {
    public String clearStars(String s) {
        int n = s.length();
        byte[] sc = new byte[n + 1];
        s.getBytes(0, n, sc, 0);
        sc[n] = '*'; // Sentinel '*' to avoid bounds checking in the loop below

        // Find the earliest index from where to start processing,
        // i.e., the point after which enough '*' exist to potentially remove all prior characters.
        int start = 0;
        int asteriskCount = 0;
        for (int i = 0; i < n; i++) {
            if (sc[i] == '*') {
                asteriskCount++;
                // If number of '*' is equal to half of the characters so far,
                // all characters up to current index will eventually be removed.
                if (asteriskCount == ((i + 2) >> 1)) 
                    start = i + 1;
            }
        }

        // Edge cases: no asterisks or all characters will be deleted
        if (asteriskCount == 0) return s;
        if (start == n) return "";

        // Arrays to simulate linked list behavior for character positions:
        // `linkedLists` keeps track of previous indices of characters,
        // `heads` stores the most recent position of each character ('a'-'z')
        int[] linkedLists = new int[n];
        int[] heads = new int[128]; // ASCII size
        for (int i = 'a'; i <= 'z'; i++)  
            heads[i] = -1;

        // Process from `start` index:
        // For each '*', delete itself and remove the latest smallest lex character.
        // For each character, maintain a stack (linked list) for quick deletion.
        for (int idx = start; ; idx++) {
            int c = sc[idx];
            if (c == '*') {
                if (idx >= n) break;
                sc[idx] = 0; // Mark '*' for deletion
                for (int i = 'a'; i <= 'z'; i++) {
                    if (heads[i] >= 0) {
                        sc[heads[i]] = 0; // Remove latest lowest lexicographical char
                        heads[i] = linkedLists[heads[i]];
                        break;
                    }
                }
            } else {
                linkedLists[idx] = heads[c]; // Push current char index into its list
                heads[c] = idx;
            }
        }

        // Build final string: collect all non-deleted characters
        int outIdx = 0;
        for (int i = start; i < n; i++)
            if (sc[i] != 0)
                sc[outIdx++] = sc[i];

        return new String(sc, 0, outIdx);
    }
}
