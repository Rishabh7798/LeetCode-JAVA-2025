3337. Total Characters in String After Transformations II

class Solution {
    public static final int MOD = (int)1e9 + 7;

    // Main method to compute length after t transformations
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // Get initial frequency count of each letter in the string
        int[] cnt = getLetterCounts(s);

        // Create transformation matrix based on the nums list
        int[][] transformMatrix = createTransformMatrix(nums);

        // Apply matrix exponentiation to transform letter counts t times
        int[] transformedCnt = matrixExp(transformMatrix, cnt, t);

        // Sum up final letter counts to get result
        long res = 0;
        for (int freq : transformedCnt) res += freq;
        return (int)(res % MOD);
    }

    // Count frequency of each letter (a-z) in the input string
    private int[] getLetterCounts(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        return cnt;
    }

    // Create a 26x26 transformation matrix based on nums
    private int[][] createTransformMatrix(List<Integer> nums) {
        int[][] A = new int[26][26];
        for (int i = 0; i < 26; i++) {
            int lim = i + nums.get(i);
            for (int j = i + 1; j <= lim; j++) {
                A[j % 26][i] = 1;
            }
        }
        return A;
    }

    // Multiply matrix A (26x26) with vector x (length 26)
    private int[] matrixVectorMult(int[][] A, int[] x) {
        int[] y = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                y[i] = (int)((y[i] + 1L * A[i][j] * x[j]) % MOD);
            }
        }
        return y;
    }

    // Square a 26x26 matrix using optimized multiplication
    private int[][] matrixSq(int[][] A) {
        int[][] C = new int[26][26];
        long[] acc = new long[26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(acc, 0L);
            for (int k = 0; k < 26; k++) {
                int aik = A[i][k];
                if (aik == 0) continue;
                int[] Ak = A[k];
                for (int j = 0; j < 26; j++) {
                    acc[j] += (long)aik * Ak[j];
                }
                if ((k & 3) == 3) {
                    for (int j = 0; j < 26; j++) {
                        acc[j] %= MOD;
                    }
                }
            }
            for (int j = 0; j < 26; j++) {
                C[i][j] = (int)(acc[j] % MOD);
            }
        }
        return C;
    }

    // Perform matrix exponentiation and apply transformation to vector
    private int[] matrixExp(int[][] matrix, int[] vector, int exp) {
        int[][] sq = copy(matrix);
        while (exp > 0) {
            if ((exp & 1) == 1) vector = matrixVectorMult(sq, vector);
            sq = matrixSq(sq);
            exp >>= 1;
        }
        return vector;
    }

    // Deep copy of a 26x26 matrix
    private int[][] copy(int[][] matrix) {
        int[][] c = new int[26][26];
        for (int i = 0; i < 26; i++) c[i] = matrix[i].clone();
        return c;
    }

    // Generate identity matrix of size 26x26 (unused in current logic)
    private int[][] identity() {
        int[][] iden = new int[26][26];
        for (int i = 0; i < 26; i++) iden[i][i] = 1;
        return iden;
    }
}
