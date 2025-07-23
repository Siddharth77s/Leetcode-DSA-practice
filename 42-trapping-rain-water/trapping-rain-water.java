public class Trapping_Rain_Water {

    public static void main(String[] args) {
        Trapping_Rain_Water out = new Trapping_Rain_Water();
        Solution_local_minimum s = out.new Solution_local_minimum();

        System.out.println(s.trap(new int[]{5,2,1,2,1,5}));
    }

    // The short board effect, the storage capacity is related to the short board of the bucket.
    // Find the longest slab in the height array, and then iterate from both ends to the long slab. When encountering a shorter one, find the storage capacity, and when encountering a longer one, update the edge.
    public class Solution_optimize {
        public int trap(int[] height) {

            if (height == null || height.length == 0)     return 0;

            int sum = 0;
            int maxind = 0;
            int max = Integer.MIN_VALUE;

            // find max index
            for (int i = 0; i < height.length; i++) {
                if (height[i] > max) {
                    max = height[i];
                    maxind = i;
                }
            }

            // left
            int leftmax = height[0];
            for (int i = 1; i < maxind; i++) {

                if (leftmax < height[i]) {
                    leftmax = height[i];
                }

                sum += leftmax - height[i];
            }

            // right
            int rightmax = height[height.length - 1];
            for (int i = height.length - 2; i > maxind; i--) {
                if (rightmax < height[i]) {
                    rightmax = height[i];
                }

                sum += rightmax - height[i];
            }

            return sum;
        }
    }

    // local minimum solution is NOT doable. like [5,2,1,2,1,5], there are 2 local minimums with each holding 1 water
    // but they are just part of a global minimum
    class Solution_local_minimum {

        public int trap(int[] height) {

            // for each position, probe to left and right, find local minimum

            if (height == null || height.length == 0)     return 0;

            int sum = 0;

            int i = 1; // index=0 or index=length-1 will not be a local min, since nothing on its left to hold water
            while (i < height.length - 1) {

                // only process local min index
                if (height[i] < height[i - 1] && height[i] < height[i + 1]) {

                    int left = i - 1;
                    while (left - 1 >= 0 && height[left] < height[left - 1]) {
                        left--;
                    }

                    int right = i + 1;
                    while (right + 1 < height.length && height[right] < height[right + 1]) {
                        right++;
                    }

                    // now find its highest bar on both sides
                    int lower = Math.min(height[left], height[right]);

                    // add up
                    while (left < right) {

                        if (height[left] < lower) {
                            sum += lower - height[left];
                        }

                        left ++;
                    }

                    // update pointer to search next local minimum
                    i = right;

                } else {
                    i++;
                }
            }

            return sum;
        }

    }

}


//////

class Solution_notFindingMaxHeight {
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int length = height.length;
        int[] leftMax = new int[length]; // `leftMax` represents the maximum height in the subarray from the leftmost index to the current index
        int[] rightMax = new int[length]; // `rightMax` represents the maximum height in the subarray from the current index to the rightmost index
        leftMax[0] = height[0];
        for (int i = 1; i < length; i++)
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        rightMax[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--)
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        int amount = 0;
        for (int i = 0; i < length; i++)
            amount += Math.min(leftMax[i], rightMax[i]) - height[i];
        return amount;
    }
}

//////

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int[] lmx = new int[n];
        int[] rmx = new int[n];
        lmx[0] = height[0];
        rmx[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            lmx[i] = Math.max(lmx[i - 1], height[i]);
            rmx[n - 1 - i] = Math.max(rmx[n - i], height[n - i - 1]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(lmx[i], rmx[i]) - height[i];
        }
        return res;
    }
}