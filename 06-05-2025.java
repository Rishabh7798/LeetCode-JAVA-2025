1920. Build Array from Permutation

class Solution {
    public int[] buildArray(int[] nums) {
        apermutation(nums,0);
        return nums;
    }
    void apermutation(int[] nums, int start){
        if(start<nums.length){
            int temp = nums[start];
            int result = nums[temp];
            apermutation(nums,start+1);
            nums[start]=result;
        }
    }
}
