/* Welsh-Powell
** d(G) \cdot n^2
 * Finds a coloring of an undirected graph (greedy) using at most $d(G)+1$ colors ($d(G)$ is the maximum degree of $G$). \\
 * Input: An adjancency matrix $matrix$. \\
 * Output: An array holding the coloring (all colors are natural numbers).
 */
//START
public static int[] welshPowell(boolean[][] matrix) {

	int[] colors = new int[matrix.length];
	int[] degreesArray = new int[matrix.length];
	PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();

	for (int i = 0; i < degreesArray.length; i++) {
		for (int j = 0; j < degreesArray.length; j++) {
			if (matrix[i][j]) {
				degreesArray[i]++;
				degreesArray[j]++;
			}
		}
	}

	for (int i = 0; i < degreesArray.length; i++) {
		Vertex v = new Vertex();
		v.id = i;
		v.valueToCompare = degreesArray[i];
		Q.add(v);
	}

	Vertex cur;
	int curColor;
	boolean foundColor;

	while (!Q.isEmpty()) {
		cur = Q.poll();
		curColor = 1;
		foundColor = false;

		outer : while (!foundColor) {

			for (int i = 0; i < matrix.length; i++) {
				if (matrix[cur.id][i]) {
					if (colors[i] == curColor) {
						curColor++;
						continue outer;
					}
				}
			}

			colors[cur.id] = curColor;
			foundColor = true;
		}
	}

	return colors;
}
//END
