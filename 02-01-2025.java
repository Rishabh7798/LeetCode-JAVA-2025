//2559. Count Vowel Strings in Ranges

class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] count = new int[n+1];
        for (int i=0;i<n;i++) {
            if (isVowelString(words[i])) {
                count[i+1]++;
            }
        }
        for (int i=1;i<=n;i++) {
            count[i]+= count[i-1];
        }
        int[] res = new int[queries.length];
        int i = 0;
        for (int[] q:queries) {
            int l = q[0];
            int r = q[1];
            res[i++] = count[r+1]-count[l];
        }
        return res;
    }
    public boolean isVowelString(String str) {
        return isVowel(str.charAt(0)) && isVowel(str.charAt(str.length()-1));
    }
    public boolean isVowel(char ch) {
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
    }
}
