class BSTIterator {
private:
    stack<TreeNode*> s;
    void pushNodes(TreeNode *node) {
        while (node) {
            s.push(node);
            node = node->left;
        }
    }
public:
    BSTIterator(TreeNode* root) {
        pushNodes(root);
    }
    int next() {
        auto node = s.top();
        s.pop();
        pushNodes(node->right);
        return node->val;
    }
    bool hasNext() {
        return s.size();
    }
};