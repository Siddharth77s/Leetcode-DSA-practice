class Solution:
    def longestCommonPrefix(self, strs):
        if not strs:
            return ""
        
        # Start by assuming the whole first string is the common prefix
        prefix = strs[0]
        
        # Compare the prefix with each string in the list
        for string in strs[1:]:
            # Reduce the prefix as long as it doesn't match the start of the current string
            while not string.startswith(prefix):
                prefix = prefix[:-1]  # Shorten the prefix by one character at a time
                if not prefix:  # If the prefix becomes empty, return ""
                    return ""
        
        return prefix

