/* Kosaraju
 ** n^2
 * Indentifies strongly connected components (SCC) in a directed graph. \\
 * Can be used for solving 2SAT in CNF as follows: \\
 * 1) Create the inference graph G such that for each variable xi in the 2-SAT instance, xi and ~xi are vertices of the inference graph. xi and ~xi are complements of each other. \\
 * 2) For each clause (u OR v), add the edges ~u -> v and ~v -> u to the inference graph G. \\
 * 3) Process the strongly connected components S of G in reverse topological order as follows: If S is marked, do nothing. If S = ~S (i.e., a variable and its complement belong to the same SCC), then stop, the instance is unsatisfiable. Otherwise, mark S true and ~S false. \\
 * 4) We get a satisfying assignment by assigning to each variable the truth value of the component containing it. \\
 * Input: An integer adjacency matrix $M$.  \\
 * Output: An integer array mapping the color of the corresponding SCC to every vertex in the graph (all colors are natural numbers).
 */
//START
public static int[] kosaraju(int M[][]) {

	int color[] = new int[M.length];
	Stack<Integer> stack = new Stack<Integer>();
	int colorN = 1;

	for (int i = 0; i < M.length; i++) {
		if (color[i] == 0) {
			sccDFS(M, stack, color, i);
		}
	}

	for (int i = 0; i < M.length; i++) {
		color[i] = 0;
	}

	int v;

	while (!stack.isEmpty()) {
		v = stack.pop();
		if (color[v] == 0) {
			sccRDFS(M, stack, color, v, colorN++);
		}
	}

	return color;
}

public static void sccDFS(int[][] M, Stack<Integer> stack, int[] color, int i) {
	color[i] = 1;
	for (int j = 0; j < M.length; j++) {
		if (M[i][j] > 0 && color[j] == 0) {
			sccDFS(M, stack, color, j);
		}
	}
	stack.push(i);
}

public static void sccRDFS(int[][] M, Stack<Integer> stack, int[] color,
		int j, int colorN) {
	color[j] = colorN;
	for (int i = 0; i < M.length; i++) {
		if (M[i][j] > 0 && color[i] == 0) {
			sccRDFS(M, stack, color, i, colorN);
		}
	}
}
//END
