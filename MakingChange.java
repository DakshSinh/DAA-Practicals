import java.util.Arrays;

public class MakingChange {
  static int minCoins(int coins[], int m, int V) {
    // Create a table to store results of subproblems
    int[] table = new int[V + 1];

    // Initialize table[] values as Infinite
    Arrays.fill(table, Integer.MAX_VALUE);

    // Base case (If given value V is 0)
    table[0] = 0;

    // Compute minimum coins required for all values from 1 to V
    for (int i = 1; i <= V; i++) {
      // Go through all coins and pick the minimum
      for (int j = 0; j < m; j++)
        if (coins[j] <= i) {
          int sub_res = table[i - coins[j]];
          if (sub_res != Integer.MAX_VALUE && sub_res + 1 < table[i])
            table[i] = sub_res + 1;
        }
    }
    return table[V];
  }

  public static void main(String args[]) {
    int coins[] = { 1, 2, 5 };
    int m = coins.length;
    int V = 11;
    System.out.println("Minimum coins required to make " + V + " is " + minCoins(coins, m, V));
  }
}
