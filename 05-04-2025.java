1863. Sum of All Subset XOR Totals

class Solution {
    public int subsetXORSum(int[] nums) {
        int total = 0;
        for (int num : nums) {
            // Compute bitwise OR of all numbers
            total |= num;  
        }
        // Multiply by 2^(n-1)
        return total * (1 << (nums.length - 1));  
    }
}
