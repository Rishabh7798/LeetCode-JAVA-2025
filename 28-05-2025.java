3372. Maximize the Number of Target Nodes After Connecting Trees I

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        var n = edges1.length + 1;
        var m = edges2.length + 1;
        if (k == 0) {
            var ans = new int[n];
            Arrays.fill(ans, 1);
            return ans;
        }
        var inTargets1 = countInTargets(n, edges1, k, false);
        var maxInTargets2 = 1;
        if (k > 1) {
            var inTargets2 = countInTargets(m, edges2, k-1, true);
            for (var in : inTargets2) {
                maxInTargets2 = Math.max(maxInTargets2, in);
            }
        }
        for (int i = 0; i < n; i++) {
            inTargets1[i] += maxInTargets2;
        }
        return inTargets1;
    }

    int[] countInTargets(int n, int[][] edges, int k, boolean findMax) {
        if (k >= n) {
            if (findMax) {
                return new int[]{n};
            } else {
                var ans = new int[n];
                Arrays.fill(ans, n);
                return ans;
            }
        }
        var prevDp = new int[n];
        Arrays.fill(prevDp, 1);
        var dp = new int[n];
        Arrays.fill(dp, 1);
        for (var edge : edges) {
            dp[edge[0]]++;
            dp[edge[1]]++;
        }
        var nextDp = new int[n];
        k--;
        for (; k > 0; k--) {
            var allAtMax = true;
            for (int i = 0; i < n; i++) {
                nextDp[i] = prevDp[i];
                if (nextDp[i] != n) {
                    allAtMax = false;
                }
            }
            if (allAtMax) {
                return nextDp;
            }
            for (var edge : edges) {
                var a = edge[0];
                var b = edge[1];
                nextDp[a] += dp[b] - prevDp[a];
                nextDp[b] += dp[a] - prevDp[b];
                if (findMax && (nextDp[a] == n || nextDp[b] == n)) {
                    return new int[]{n};
                }
            }
            var temp = prevDp;
            prevDp = dp;
            dp = nextDp;
            nextDp = temp;
        }
        return dp;
    }
}
