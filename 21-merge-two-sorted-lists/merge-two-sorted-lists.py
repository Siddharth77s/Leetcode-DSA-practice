class Solution:
    def mergeTwoLists(self, list1, list2):
        # Create a dummy node to simplify edge cases (like an empty list)
        dummy = ListNode()
        current = dummy
        
        # Merge the two lists while both lists are not empty
        while list1 and list2:
            if list1.val < list2.val:
                current.next = list1
                list1 = list1.next
            else:
                current.next = list2
                list2 = list2.next
            current = current.next
        
        # If there are remaining nodes in either list, append them
        if list1:
            current.next = list1
        elif list2:
            current.next = list2
        
        # Return the merged list, starting from the node after the dummy
        return dummy.next

