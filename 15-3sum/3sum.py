class Solution:
    def threeSum(self, nums):
        nums.sort()  # Sort the array
        result = []  # This will store the unique triplets
        
        for i in range(len(nums) - 2):  # Iterate through the array
            # Skip the same element to avoid duplicate triplets
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            
            left, right = i + 1, len(nums) - 1  # Two pointers
            while left < right:
                total = nums[i] + nums[left] + nums[right]
                
                if total == 0:
                    result.append([nums[i], nums[left], nums[right]])  # Found a triplet
                    # Skip duplicates for left and right pointers
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    
                    # Move both pointers after finding a valid triplet
                    left += 1
                    right -= 1
                elif total < 0:
                    left += 1  # Increase the left pointer to get a larger sum
                else:
                    right -= 1  # Decrease the right pointer to get a smaller sum
        
        return result
