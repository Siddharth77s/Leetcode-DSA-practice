class Solution {
    private int ans;
    private int prev;
    private int inf = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        ans = inf;
        prev = inf;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, Math.abs(root.val - prev));
        prev = root.val;
        dfs(root.right);
    }
}