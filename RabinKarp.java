public class RabinKarp {

  // Function to perform string matching using Rabin-Karp algorithm
  public static void search(String text, String pattern) {
    int prime = 101; // A prime number to use in hashing
    int d = 256; // Number of characters in the input alphabet

    int n = text.length();
    int m = pattern.length();

    int patternHash = 0; // Hash value for the pattern
    int textHash = 0; // Hash value for the current window of text

    // Calculate the hash value for the pattern and the first window of text
    for (int i = 0; i < m; i++) {
      patternHash = (patternHash * d + pattern.charAt(i)) % prime;
      textHash = (textHash * d + text.charAt(i)) % prime;
    }

    // Calculate d^(m-1) to be used later for removing the leading digit
    int h = 1;
    for (int i = 0; i < m - 1; i++) {
      h = (h * d) % prime;
    }

    // Slide the pattern over the text one by one
    for (int i = 0; i <= n - m; i++) {
      // Check if the hash values of current window of text and pattern match
      if (patternHash == textHash) {
        // If hash values match, then check character by character
        int j;
        for (j = 0; j < m; j++) {
          if (text.charAt(i + j) != pattern.charAt(j))
            break;
        }
        if (j == m) {
          System.out.println("Pattern found at index " + i);
        }
      }

      // Calculate hash value for next window of text: remove leading digit, add
      // trailing digit
      if (i < n - m) {
        textHash = (d * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;

        // Convert negative hash value to positive by adding prime
        if (textHash < 0)
          textHash += prime;
      }
    }
  }

  public static void main(String[] args) {
    String text = "AABAACAADAABAABA";
    String pattern = "AABA";

    search(text, pattern);
  }
}
