/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean left = true;
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (!left) {
                Collections.reverse(t);
            }
            ans.add(t);
            left = !left;
        }
        return ans;
    }
}

//////

public class Binary_Tree_Zigzag_Level_Order_Traversal {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    // count as level marker
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            boolean isLeftToRight = true;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            int currentLevelCount = 1;
            int nextLevelCount = 0;

            List<Integer> one = new ArrayList<>();

            while (!q.isEmpty()) {
                TreeNode current = q.poll();
                currentLevelCount--;

                if (isLeftToRight) {
                    one.add(current.val);
                } else {
                    one.add(0, current.val);
                }

                if (current.left != null) {
                    q.offer(current.left);
                    nextLevelCount++;
                }
                if (current.right != null) {
                    q.offer(current.right);
                    nextLevelCount++;
                }

                if (currentLevelCount == 0) {
                    currentLevelCount = nextLevelCount;
                    nextLevelCount = 0;

                    result.add(one);
                    one = new ArrayList<>();

                    isLeftToRight = !isLeftToRight;
                }
            }

            return result;
        }
    }

    public class Solution_nullAsMarker {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

            List<List<Integer>> list = new ArrayList<List<Integer>>();

            if (root == null) {
                return list;
            }

            Queue<TreeNode> q = new LinkedList<>();

            q.offer(root);
            q.offer(null);// @note: use null as marker for end of level

            boolean direction = true; // true: left=>right, false: right=>left
            List<Integer> oneLevel = new ArrayList<>();
            while (!q.isEmpty()) {

                TreeNode current = q.poll();

                if (current == null) {
                    List<Integer> copy = new ArrayList<>(oneLevel);
                    list.add(copy);

                    // clean after one level recorded
                    oneLevel.clear();// @memorize: this api
                    direction = !direction;

                    // @note:@memorize: if stack is now empty then DO NOT add null, or else infinite looping
                    // sk.offer(null); // add marker
                    if (!q.isEmpty()) {
                        q.offer(null); // add marker
                    }

                    continue;
                }

                if (direction) {
                    oneLevel.add(current.val);
                } else {
                    oneLevel.add(0, current.val);
                }

                // @note:@memorize: since using null as marker, then must avoid adding null when children are null
                // sk.offer(current.left);
                // sk.offer(current.right);
                if (current.left != null) {
                    q.offer(current.left);
                }
                if (current.right != null) {
                    q.offer(current.right);
                }

            }

            return list;
        }
    }

}
