class Solution {
public:
    Node* copyRandomList(Node* head) {
        auto p = head;
        while (p) {
            auto node = p;
            p = p->next;
            auto copy = new Node(node->val);
            node->next = copy;
            copy->next = p;
        }
        p = head;
        while (p) {
            if (p->random) p->next->random = p->random->next;
            p = p->next->next;
        }
        p = head;
        Node h(0), *tail = &h;
        while (p) {
            auto node = p->next;
            p->next = node->next;
            p = p->next;
            tail->next = node;
            tail = node;
        }
        return h.next;
    }
};