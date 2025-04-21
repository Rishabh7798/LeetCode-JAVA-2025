2145. Count the Hidden Sequences

class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long start = 0, max = 0, min = 0;
        for(int diff : differences){
            start += diff;
            max = (max<start)?start:max;
            min = (min>start)?start:min;
        }
        return (int)Math.max(0,(upper-lower)-(max-min)+1);
    }

}
