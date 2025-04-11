2843. Count Symmetric Integers

class Solution {
    private static final short[] symCount = new short[10_001];
    
    
    public int countSymmetricIntegers(int low, int high) {
        if (symCount[11] == 0)  buildCounts();
        return symCount[high] - symCount[low - 1];
    }
    
    private void buildCounts() {
        for (int num = 11; num <= 99; num++)
            symCount[num] = (short)(num / 11);

        short prev = symCount[99];
        for (int num = 100; num <= 999; num++)
            symCount[num] = prev;
        
        prev = symCount[999];
        int idx = 1000;
        for (int high10 = 1; high10 <= 9; high10++) {
            for (int high1 = 0; high1 <= 9; high1++) {
                final int highSum = high10 + high1;
                for (int low10 = 0; low10 <= 9; low10++) 
                    for (int low1 = 0; low1 <= 9; low1++) 
                        symCount[idx++] = (short)((highSum == low10 + low1) ? ++prev : prev);
            }
        }
        symCount[10_000] = symCount[9999];
    }
}
