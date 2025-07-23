class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
            "",     
            "",     
            "abc",  
            "def",  
            "ghi", 
            "jkl", 
            "mno", 
            "pqrs", 
            "tuv", 
            "wxyz" 
        };

        backtrack(digits, 0, new StringBuilder(), mapping, result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentCombination, String[] mapping, List<String> result) {
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        char digitChar = digits.charAt(index);
        int digitValue = digitChar - '0';
        String letters = mapping[digitValue];

        for (char letter : letters.toCharArray()) {
            currentCombination.append(letter);
            backtrack(digits, index + 1, currentCombination, mapping, result);
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}