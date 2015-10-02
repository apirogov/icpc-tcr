/* Held-Karp TSP
 ** n*2^n
 * Find shortest round tour. \\
 * Input: adj. mat. with distances between all vertices
 * Output: Shortest tour
 */
class TSP {
//START
    //Held-Karp TSP in n*2^n (sinnvoll für n<=32)
    public static int tsp(int[][] mat) {
        int n = mat.length;
        int[][] C = new int[1<<n][n];
        for (int k=1; k<n; k++)
            C[1<<k][k] = mat[0][k];

        for (int s=2; s<n; s++) {
            for (int S = 1; S<(1<<n); S++) {
                if (Integer.bitCount(S)!=s)
                    continue;

                for (int k=1; k<n; k++) {
                    if ((S & (1<<k)) == 0)
                        continue;

                    int Smk = S ^ (1<<k);

                    int min = Integer.MAX_VALUE;
                    for (int m=1; m<n; m++) {
                        if ((Smk & (1<<m))==0)
                            continue;

                        int tmp = C[Smk][m] + mat[m][k];
                        if (tmp < min)
                            min = tmp;
                    }
                    C[S][k] = min;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 1; k<n; k++) {
            int tmp = C[(1<<n) - 2][k] + mat[0][k];
            if (tmp < min)
                min = tmp;
        }

        return min;
    }
//END
}
