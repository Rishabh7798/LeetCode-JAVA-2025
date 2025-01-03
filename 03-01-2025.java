// 2270. Number of Ways to Split Array

class Solution {
    public int waysToSplitArray(int[] nums) {
        long sum = 0;

        for(int num : nums){
            sum += num;
        }

        int i = 0;
        int cnt = 0;
        long n = 0;
        while(i < nums.length-1){
            n += nums[i];
            long r = sum - n;

            if(n >= r){
                cnt++;
            }
            i++;
        }
        return cnt;
    }
}
