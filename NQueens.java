public class NQueens {
  int[][] board;
  int n;

  NQueens(int n) {
    this.n = n;
    this.board = new int[n][n];
  }

  boolean isSafe(int row, int col) {
    int i, j;

    // Check this row on left side
    for (i = 0; i < col; i++)
      if (board[row][i] == 1)
        return false;

    // Check upper diagonal on left side
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
      if (board[i][j] == 1)
        return false;

    // Check lower diagonal on left side
    for (i = row, j = col; j >= 0 && i < n; i++, j--)
      if (board[i][j] == 1)
        return false;

    return true;
  }

  boolean solveNQueensUtil(int col) {
    if (col >= n)
      return true;

    for (int i = 0; i < n; i++) {
      if (isSafe(i, col)) {
        board[i][col] = 1;

        if (solveNQueensUtil(col + 1))
          return true;

        board[i][col] = 0; // Backtrack
      }
    }
    return false;
  }

  boolean solveNQueens() {
    if (!solveNQueensUtil(0)) {
      System.out.println("Solution does not exist");
      return false;
    }

    printSolution();
    return true;
  }

  void printSolution() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(" " + board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int n = 4;
    NQueens queen = new NQueens(n);
    queen.solveNQueens();
  }
}
