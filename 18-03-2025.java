Partition Equal Subset Sum

class Solution {
  public:
    bool equalPartition(vector<int>& arr) {
        // code here
        int sum = accumulate(arr.begin(), arr.end(), 0);
        
        // If the total sum is odd, we cannot partition it into two equal subsets
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        int n = arr.size();
        
        // DP array to store if a subset with sum `j` is possible
        vector<bool> dp(target + 1, false);
        dp[0] = true; // Base case: Subset sum of 0 is always possible
        
        for (int num : arr) {
            // Traverse in reverse to avoid using the same element multiple times
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }
};
