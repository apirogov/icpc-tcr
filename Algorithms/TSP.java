/* Held-Karp TSP
 ** n*2^n
 * Find shortest round tour. \\
 * Input: adj. mat. with distances between all vertices
 * Output: Shortest tour
 */
import java.util.*;
class TSP {
//START
//Held-Karp TSP in n*2^n (sinnvoll für n<=32)
public static int[] tsp(int[][] mat) {
	int n = mat.length;
	if (n==1) return new int[]{0}; //triviale tour (nichts tun)
	int[][] C = new int[1<<n][n];
	int[][] p = new int[1<<n][n]; // second-to-last vertex for backtracking
	for (int k=1; k<n; k++)
		C[1<<k][k] = mat[0][k];

	for (int s=2; s<n; s++) {
		for (int S = 1; S<(1<<n); S++) {
			if (Integer.bitCount(S)!=s || (S&1)==1)
				continue;

			for (int k=1; k<n; k++) {
				if ((S & (1<<k)) == 0)
					continue;

				int Smk = S ^ (1<<k);

				int min = Integer.MAX_VALUE;
				int minprev = 0;
				for (int m=1; m<n; m++) {
					if ((Smk & (1<<m))==0)
						continue;

					int tmp = C[Smk][m] + mat[m][k];
					if (tmp < min) {
						min = tmp;
						minprev = m;
					}
				}
				C[S][k] = min;
				p[S][k] = minprev;
			}
		}
	}

	//find shortest tour length
	int min = Integer.MAX_VALUE;
	int minprev = -1;
	for (int k = 1; k<n; k++) {
		int tmp = C[(1<<n) - 2][k] + mat[0][k];
		if (tmp < min) {
			min = tmp;
			minprev = k;
		}
	}
	//backtrack tour
	int[] tour = new int[n+1];
	tour[n] = 0;
	tour[n-1] = minprev;
	int bits = (1<<n)-2;
	for (int k=n-2; k>0; k--) {
		tour[k] = p[bits][tour[k+1]];
		bits =  bits ^ (1<<(tour[k+1]));
	}
	tour[0] = 0;
	return tour;
}
//END
public static void main(String[] args) {
	System.out.println(Arrays.toString(tsp(new int[][]{{0}}))); //0
	int[][] mat = new int[][]{{0,1,2,2},
							  {2,0,2,1},
							  {1,2,0,2},
							  {2,2,1,0}};
	int[] ret = tsp(mat);
	int path = 0;
	for (int i=0; i<ret.length-1; i++) {
		path += mat[ret[i]][ret[i+1]];
		System.out.println(ret[i]+" -> "+ret[i+1]);
	}
	System.out.println("length "+(ret.length-1)+" with weight "+path); //4
}
}
