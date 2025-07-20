class Solution {
      public int findKthLargest(int[] nums, int k) {
            // Create a max heap (priority queue with custom comparator for descending order)
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

            // Add all elements to the max heap
            for (int n : nums) {
                  pq.add(n);
            }

            // Remove k-1 largest elements to reach the k-th largest
            while (--k > 0) {
                  pq.poll();
            }

            // Return the k-th largest element
            return pq.peek();
      }
}