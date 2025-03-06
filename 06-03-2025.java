2965. Find Missing and Repeated Values

class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long sum_n = (long) n * n * (n * n + 1) / 2;
        long sum_squares = (long) n * n * (n * n + 1) * (2 * n * n + 1) / 6;
        long grid_sum = 0, grid_sum_squares = 0;

        for (int[] row : grid) {
            for (int num : row) {
                grid_sum += num;
                grid_sum_squares += (long) num * num;
            }
        }

        long diff_sum = grid_sum - sum_n; 
        long diff_sum_squares = grid_sum_squares - sum_squares; 
        long sum_ab = diff_sum_squares / diff_sum; 

        int a = (int) ((diff_sum + sum_ab) / 2);
        int b = (int) ((sum_ab - diff_sum) / 2); 

        return new int[]{a, b}; 
    }
}
