2929. Distribute Candies Among Children II

class Solution {
    public long distributeCandies(int n, int limit) {
          return totalWays(n) 
             - 3 * totalWays(n - (limit + 1))
             + 3 * totalWays(n - 2 * (limit + 1))
             - totalWays(n - 3 * (limit + 1));
    }
    private long totalWays(long sum) {
        if (sum < 0) return 0;
        return (sum + 2) * (sum + 1) / 2;
    }
}
