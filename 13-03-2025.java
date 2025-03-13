3356. Zero Array Transformation II

class Solution {
    // Prefix Sum - O(N+M) & O(N)
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, sum = 0, k = 0;  // n = size, sum = prefix sum, k = query count
        int[] differenceArray = new int[n + 1];  // Difference array for range updates

        for (int index = 0; index < n; index++) {
            // Apply queries until nums[index] becomes zero
            while (sum + differenceArray[index] < nums[index]) {  
                k++;  
                if (k > queries.length) return -1;  // Exceeded available queries

                // Extract query parameters
                int left = queries[k - 1][0], right = queries[k - 1][1], val = queries[k - 1][2];  

                // Apply range update
                if (right >= index) {  
                    differenceArray[Math.max(left, index)] += val;  
                    differenceArray[right + 1] -= val;  
                }
            }
            sum += differenceArray[index];  // Update prefix sum
        }
        return k;  // Return min queries needed
    }
}
