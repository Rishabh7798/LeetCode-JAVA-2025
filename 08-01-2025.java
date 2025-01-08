// 3042. Count Prefix and Suffix Pairs I

class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int res = 0;

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPrefixAndSuffix(words[i], words[j])) {
                    res += 1;
                }
            }
        }
        return res;
    }

    boolean isPrefixAndSuffix(String s1, String s2) {
        return s2.startsWith(s1) && s2.endsWith(s1);
    }
}
