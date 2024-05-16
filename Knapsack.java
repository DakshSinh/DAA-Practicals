public class Knapsack {

  public static void main(String[] args) {
    int[] values = { 5, 1, 10 };
    int[] weights = { 10, 2, 9 };
    int capacity = 10;
    int maxValue = knapsack(values, weights, capacity);
    System.out.println("Maximum value that can be obtained: " + maxValue);
  }

  public static int knapsack(int[] values, int[] weights, int capacity) {
    int n = values.length;
    int[][] dp = new int[n + 1][capacity + 1];

    // Initialize the first row and column with zeros
    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= capacity; w++) {
        if (i == 0 || w == 0) {
          dp[i][w] = 0;
        }
      }
    }

    // Build the dp table bottom-up
    for (int i = 1; i <= n; i++) {
      for (int w = 1; w <= capacity; w++) {
        if (weights[i - 1] <= w) {
          dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }

    return dp[n][capacity];
  }
}
