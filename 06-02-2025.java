// 1726. Tuple with Same Product

class Solution {
    public static int tupleSameProduct(int[] nums) {
        int n = nums.length;
        int N = n * (n - 1) / 2;
        int[] product = new int[N];
        int index = 0;

        // Step 1: Calculate all products of pairs and store them in an array
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                product[index++] = nums[i] * nums[j];
            }
        }

        // Step 2: Sort the product array
        Arrays.sort(product);

        // Step 3: Count the frequency of each product
        int ans = 0;
        int f = 1; // Frequency counter
        for (int i = 1; i < N; i++) {
            if (product[i] == product[i - 1]) {
                f++;
            } else {
                if (f > 1) {
                    ans += f * (f - 1) * 4; // Each pair contributes 8 tuples
                }
                f = 1; // Reset frequency counter
            }
        }

        // Handle the last group of products
        if (f > 1) {
            ans += f * (f - 1) * 4;
        }

        return ans;
    }
}
