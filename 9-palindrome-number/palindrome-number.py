class Solution:
    def isPalindrome(self, x):
        # Negative numbers are not palindromes
        if x < 0:
            return False
        
        # Numbers ending with 0 (except 0) are not palindromes
        if x != 0 and x % 10 == 0:
            return False
        
        reversed_half = 0
        while x > reversed_half:
            # Add the last digit of x to the reversed_half
            reversed_half = reversed_half * 10 + x % 10
            x //= 10  # Remove the last digit from x
        
        # If x is equal to the reversed half, or x is equal to reversed_half // 10 (for odd-length numbers)
        return x == reversed_half or x == reversed_half // 10
