3442. Maximum Difference Between Even and Odd Frequency I

class Solution {
    public int maxDifference(String s) {
        int []arr = new int[26];
        for(char ch:s.toCharArray())arr[ch-'a']++;
        int oddmax=0, evenmax=s.length();

        for(int i:arr)if(i%2==1)oddmax=Math.max(i, oddmax);
        else evenmax=Math.min(i==0?s.length():i, evenmax);

        return oddmax-evenmax;
    }
}
