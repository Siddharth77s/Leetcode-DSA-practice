public class Maximum_Gap {

    public class Solution {
        public int maximumGap(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }

            // Step 1: find max and min element
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }

                if (num < min) {
                    min = num;
                }
            }

            int len = nums.length;

            // Step 2: calculate the intervals and number of buckets.
            int interval = (int) Math.ceil((double) (max - min) / (len - 1));

            if (interval == 0) {
                interval = 1;
            }
            int numBuckets = (max - min) / interval + 1;
            Bucket[] buckets = new Bucket[numBuckets];
            for (int i = 0; i < numBuckets; i++) {
                buckets[i] = new Bucket();
            }

            // Step 3: iterate through the nums and assign the number into the buckets.
            for (int num : nums) {
                int bucketNum = (num - min) / interval;
                if (num > buckets[bucketNum].max) {
                    buckets[bucketNum].max = num;
                }

                if (num < buckets[bucketNum].min) {
                    buckets[bucketNum].min = num;
                }
            }

            // Step 4: iterate through the buckets and get the maximal gap
            int prev = buckets[0].max;
            int maxGap = 0;
            for (int i = 1; i < numBuckets; i++) {
                if (prev != Integer.MIN_VALUE && buckets[i].min != Integer.MAX_VALUE) {
                    maxGap = Math.max(maxGap, buckets[i].min - prev);
                    prev = buckets[i].max;
                }
            }

            return maxGap;
        }

        private class Bucket {
            public int min;
            public int max;

            public Bucket() {
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
            }
        }
    }

}

//////

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int inf = 0x3f3f3f3f;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (var bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (var bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
