/* Flyod-Warshall
 * Finds the shortest paths from every node to every other node (APSP).  \\
 * Input: An adjancency matrix $adj$. \\
 * Output: A weighted transitive closure.
 ** |V|^3
 */
//START
public static int[][] allPairsShortestPaths(int[][] adj) {
	int n = adj.length;
	int[][] ans = new int[n][n];
	int[][] path = new int[n][n];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			ans[i][j] = adj[i][j];

	for (int i=0; i<5; i++)
		for (int j=0; j<5; j++)
			if (adj[i][j] == C)
				path[i][j] = -1;
			else
				path[i][j] = i;

	// Compute successively better paths through vertex k.
	for (int k = 0; k < n; k++) {
		// Do so between each possible pair of points.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ans[i][k] + ans[k][j] < ans[i][j]) {
					ans[i][j] = ans[i][k] + ans[k][j];
					path[i][j] = path[k][j];
				}
			}
		}
	}
	// Return the shortest path matrix.
	return ans;
}
//END
