3066. Minimum Operations to Exceed Threshold Value II

class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        int size = 0, count = 0;
        int d = k / 3, r = k % 3, minNum = k;
        for(int x: nums){
            if(x - r  < d)
              arr[size++] = x;
            else if(x < k){
              ++count;
              minNum = Math.min(minNum, x);
            }
        }
        
        Arrays.sort(arr, 0, size);
        
        int[] que = new int[size];
        int write = 0, read = 0, index = 0;
        int min = 0, max = 0, steps = 0;
        
        while(index < size || read < write){
            
            min = (index < size && ( read == write ||arr[index] < que[read]))? arr[index++]:que[read++];
            
            ++steps;   
            if(index == size && read == write){
                int x = min * 2 + minNum;
                if(x >= k) --count;
                break;
            }
            
            max = (index < size && ( read == write ||arr[index] < que[read]))? arr[index++]:que[read++];
            
            int x = min * 2 + max;
            if(x - r < d)
               que[write++] = x;
            else if(x < k){
              ++count;
              minNum = Math.min(minNum, x);                
            }
        }
        
        return steps + (count + 1)/2;
    }
}
