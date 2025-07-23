class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());

            graph.get(A).put(B, value);
            graph.get(B).put(A, 1.0 / value);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);

            if (!graph.containsKey(C) || !graph.containsKey(D)) {
                results[i] = -1.0; 
            } else if (C.equals(D)) {
                results[i] = 1.0; 
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, C, D, 1.0, visited);
            }
        }

        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double product, Set<String> visited) {
        visited.add(current);

        if (current.equals(target)) {
            return product;
        }

        if (graph.containsKey(current)) {
            for (Map.Entry<String, Double> neighborEntry : graph.get(current).entrySet()) {
                String neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();

                if (!visited.contains(neighbor)) {
                    double result = dfs(graph, neighbor, target, product * weight, visited);
                    if (result != -1.0) { 
                        return result;
                    }
                }
            }
        }
        
        visited.remove(current); 
        return -1.0;
    }
}