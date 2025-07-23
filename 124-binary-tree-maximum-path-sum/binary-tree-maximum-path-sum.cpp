class Solution {
public:
    int maxPathSum(TreeNode* root) {
        int ans = -1001;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int left = max(0, dfs(root->left));
            int right = max(0, dfs(root->right));
            ans = max(ans, left + right + root->val);
            return root->val + max(left, right);
        };
        dfs(root);
        return ans;
    }
};