3355. Zero Array Transformation I

class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int[] arr = new int[nums.length+1];
        //add all queries in diff arr
        for(int[] q : queries){
            arr[q[0]] += 1;
            arr[q[1]+1] -= 1;
        }

        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += arr[i];
            arr[i] = sum;
            if(arr[i] < nums[i]){
                return false;
            }
        }

        //prefix all values
        // int sum = 0;
        // for(int i=0;i<arr.length;i++){
        //     sum += arr[i];
        //     arr[i] = sum;
        // }

        // //check if arr elements are greater than equal to nums arr 
        // for(int i=0;i<nums.length;i++){
        //     if(arr[i] < nums[i]){
        //         return false;
        //     }
        // }

        return true;
    }
}
