public class LongestCommonSubsequence {

  // Function to find the length of the longest common subsequence
  public static int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    // Create a 2D array to store lengths of longest common subsequence
    int[][] dp = new int[m + 1][n + 1];

    // Build DP table in bottom-up manner
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0)
          dp[i][j] = 0; // Base case: LCS of any string with an empty string is 0
        else if (text1.charAt(i - 1) == text2.charAt(j - 1))
          dp[i][j] = dp[i - 1][j - 1] + 1; // If characters match, extend LCS
        else
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Otherwise, take the maximum of previous results
      }
    }

    // The bottom-right cell of the DP table contains the length of the LCS
    return dp[m][n];
  }

  public static void main(String[] args) {
    String text1 = "ABCDE";
    String text2 = "ACE";
    int lcsLength = longestCommonSubsequence(text1, text2);
    System.out.println("Length of Longest Common Subsequence: " + lcsLength);
  }
}
