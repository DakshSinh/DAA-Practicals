public class ChainedMatrixMultiplication {

  // Function to find the minimum number of multiplications needed for matrix
  // chain multiplication
  public static int minMatrixMultiplications(int[] dimensions) {
    int n = dimensions.length;

    // Create a 2D array to store minimum number of multiplications
    int[][] dp = new int[n][n];

    // Fill the diagonal elements with 0 since a single matrix requires no
    // multiplication
    for (int i = 0; i < n; i++) {
      dp[i][i] = 0;
    }

    // Build DP table in bottom-up manner to find the minimum number of
    // multiplications
    for (int len = 2; len < n; len++) {
      for (int i = 1; i < n - len + 1; i++) {
        int j = i + len - 1;
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int cost = dp[i][k] + dp[k + 1][j] + dimensions[i - 1] * dimensions[k] * dimensions[j];
          if (cost < dp[i][j]) {
            dp[i][j] = cost;
          }
        }
      }
    }

    // Minimum number of multiplications required for the entire chain
    return dp[1][n - 1];
  }

  public static void main(String[] args) {
    // Example dimensions of matrices
    int[] dimensions = { 10, 30, 5, 60 };

    int minMultiplications = minMatrixMultiplications(dimensions);
    System.out.println("Minimum number of multiplications needed: " + minMultiplications);
  }
}
