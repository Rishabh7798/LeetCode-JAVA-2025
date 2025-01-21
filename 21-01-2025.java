// 2017. Grid Game

class Solution {
    public long gridGame(int[][] grid) {
        long ans = Long.MAX_VALUE;
            long s1 = 0; //suffix sum of the points in the first row
            long s2 = 0; //prefix sum of the points in the second row
            for (int v : grid[0]) {
                s1 += v;
            }
            int n = grid[0].length;
            for (int j = 0; j < n; ++j) { //j = find optimal position to go down
                s1 -= grid[0][j];
                ans = Math.min(ans, Math.max(s1, s2));
                s2 += grid[1][j];
            }
            return ans;
    }
}
