/* Longest Increasing Subsequence
** n^2
 * Finds the longest increasing subsequence in an integer array. \\
 * Input: An integer array $X$. \\
 * Output: The length of the LIS.
 */
//START
public static int longestIncreasingSubsequence(int[] X) {

	int result = 0;

	boolean[][] adjacencyMatrix = new boolean[X.length][X.length];

	for (int i = 0; i < X.length; i++) {
		for (int j = i + 1; j < X.length; j++) {
			if (X[i] <= X[j]) {
				adjacencyMatrix[j][i] = true;
			}
		}
	}

	int[] maxLengths = new int[X.length];

	for (int i = 0; i < X.length; i++) {
		for (int j = 0; j < X.length; j++) {
			if (adjacencyMatrix[i][j] && maxLengths[j] + 1 > maxLengths[i]) {
				maxLengths[i] = maxLengths[j] + 1;
				if (maxLengths[i] > result) result = maxLengths[i];
			}
		}
	}

	return result+1;
}
//END
