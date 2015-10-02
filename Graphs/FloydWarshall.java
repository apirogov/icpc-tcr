/* Floyd-Warshall
 ** |V|^3
 * Finds the shortest paths from every node to every other node (APSP).  \\
 * Input: An adjancency matrix $adj$ (no edge=0). \\
 * Output: A weighted transitive closure (MAX\_INT = unreach.)
 */
class Floyd {
//START
public static int[][] apsp(int[][] mat) {
        int n=mat.length;
        int[][] d = new int[n][n];
        for(int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                if (mat[i][j]>0 || i==j)
                    d[i][j] = mat[i][j];
                else
                    d[i][j] = Integer.MAX_VALUE;

        for (int k=0; k<n; k++)
            for (int i=0; i<n; i++)
                for (int j=0; j<n; j++)
                    if (d[i][k] + d[k][j] >= 0)
                        d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
        return d;
}
//END
}
