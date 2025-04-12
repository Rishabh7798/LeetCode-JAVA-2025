3272. Find the Count of Good Integers

class Solution {
    private static final int[][] modPow10 = new int[][]{
        {0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
        {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
        {1, 3, 2, 6, 4, 5, 1, 3, 2, 6, 4}, 
        {1, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    }; 

    private static final int[] factorial = new int[]{
        1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800
    };
    public long countGoodIntegers(int n, int k) {
        if(n == 1) return 9/k;
        return backtrackDigitCombos(0, 0, n, k,  new int[(n+1)/2]);
    }

    private long backtrackDigitCombos(int idx, int lo, int n, int k, int[] digits) {
        if(idx == digits.length) {
            if(digits[idx-1] == 0) return 0;
            if(n % 2 == 1) { 
                if(digits[idx-2] == 0) return hasKPalPerm(digits, n, k, 0, 1 << (idx-2), 0) ? validPermCnt(digits, n, 0) : 0;
                long res = 0;
                for(int c = 0; c < digits.length; c++) { 
                    if(c > 0 && digits[c] == digits[c-1]) continue;
                    if(hasKPalPerm(digits, n, k, (digits[c]*modPow10[k][n/2]) % k, 1 << c, 0)) {
                        res += validPermCnt(digits, n, digits[c]);
                    }
                }
                return res;
            }
            
            return hasKPalPerm(digits, n, k, 0, 0, 0) ? validPermCnt(digits, n, -1) : 0;
        }
        long res = 0;
        for(int d = lo; d <= 9; d++) {
            digits[idx] = d;
            res += backtrackDigitCombos(idx+1, d, n, k, digits);
        }
        return res;
    }

    private boolean hasKPalPerm(int[] digits, int n, int k, int residue, int mask, int chosenCnt) {
        if(chosenCnt == n/2) {
            return residue == 0;
        }

        for(int i = digits.length-1; i >= 0; i--) {
            if(chosenCnt == 0 && digits[i] == 0) break;
            if((mask & (1 << i)) != 0) continue;
            if(hasKPalPerm(digits, n, k, (residue + digits[i]*(modPow10[k][n-chosenCnt-1] + modPow10[k][chosenCnt])) % k, mask | (1 << i), chosenCnt+1)) return true;
        }
        return false;
    }

    private int validPermCnt(int[] digits, int n, int center) {
        int nonZeroInd = 0;
        while(digits[nonZeroInd] == 0) nonZeroInd++;
        int zeroCnt = nonZeroInd * 2 - (center == 0 ? 1 : 0); 

        long perms = factorial[n]/factorial[zeroCnt];
        int streak = 1;
        for(int i = nonZeroInd+1; i < digits.length; i++) { 
            if(digits[i] == digits[i-1]) {
                streak++;
            } else {
                perms /= factorial[2*streak - (digits[i-1] == center ? 1 : 0)];
                streak = 1;
            }
        }
        perms /= factorial[2*streak - (digits[digits.length-1] == center ? 1 : 0)];
        return (int)(perms*(n-zeroCnt)/n); 
    }

    
}
