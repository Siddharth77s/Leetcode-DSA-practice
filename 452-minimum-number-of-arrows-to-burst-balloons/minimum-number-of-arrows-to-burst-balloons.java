class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] < b[1] ? -1 : 1;
            }
        });

        int arrows = 1; 
        long lastArrowPos = points[0][1]; 
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > lastArrowPos) {
                arrows++;
                lastArrowPos = points[i][1]; 
            }
        }
        return arrows;
    }
}