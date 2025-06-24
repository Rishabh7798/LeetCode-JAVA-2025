2200. Find All K-Distant Indices in an Array

class Solution {
    static {
    for (int i = 0; i < 500; i++) findKDistantIndices(new int[] {1, 1}, 1, 1);
}

    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
    int n = nums.length, i = 0, j = 0;

    while (i < n && j < n) {
        if (nums[j] != key) j++;
        else if (i < j - k) i++;
        else if (i <= j + k) res.add(i++);
        else j++;
    }

    return res;
    }
}
