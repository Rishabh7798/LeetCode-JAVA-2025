1498. Number of Subsequences That Satisfy the Given Sum Condition

class Solution {
    public int numSubseq(int[] nums, int target) {
         int mod = 1_000_000_007;
        int n = nums.length;

        Arrays.sort(nums);

        // Precompute powers of 2
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % mod;
        }

        int i = 0, j = n - 1;
        int count = 0;

        while (i <= j) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else {
                count = (count + pow2[j - i]) % mod;
                i++;
            }
        }

        return count;

    }
}
