class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        backtrack(k, n, 1, currentCombination, result);
        return result;
    }

    private void backtrack(int k, int n, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (currentCombination.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(currentCombination));
            }
            return;
        }

        if (n < 0 || start > 9) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            currentCombination.add(i);
            backtrack(k, n - i, i + 1, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
