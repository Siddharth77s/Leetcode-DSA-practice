class Solution:
    def isValid(self, s):
        # Stack to store opening brackets
        stack = []
        
        # Dictionary to map closing brackets to their corresponding opening brackets
        bracket_map = {')': '(', '}': '{', ']': '['}
        
        for char in s:
            # If the character is a closing bracket
            if char in bracket_map:
                # Pop the top of the stack if it exists, otherwise assign a dummy value
                top_element = stack.pop() if stack else '#'
                
                # Check if the popped element matches the expected opening bracket
                if bracket_map[char] != top_element:
                    return False
            else:
                # If the character is an opening bracket, push it onto the stack
                stack.append(char)
        
        # If the stack is empty, all the brackets were matched; otherwise, it's invalid
        return not stack
