package au.edu.rmit.storyboard_navigation.utils;

public class LevenshteinDistance {
    public static int distance(String first, String second) {
        first = first.toLowerCase();
        second = second.toLowerCase();

        int[] costs = new int [second.length() + 1];

        for (int i = 0; i < costs.length; i++) {
            costs[i] = i;
        }

        for (int i = 1; i <= first.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= second.length(); j++) {
                int cost_j = Math.min(1 + Math.min(costs[j], costs[j - 1]), first.charAt(i - 1) == second.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cost_j;
            }
        }

        return costs[second.length()];
    }
}
