/* k-Coloring
 * Finds a k-coloring of an undirected graph if possible. Caution: Uses exhaustive search (backtracking), intractable. \\
 * Input: An adjancency matrix $matrix$ and an integer $k$. \\
 * Output: Output: An array holding the k-coloring (all colors are natural numbers). If a k-coloring is impossible, all colors are -1.
 ** k^n
 */
//START
public static int[] kColoring(boolean[][] matrix, int k) {

	int[] colors = new int[matrix.length];

	int pointer = 0;

	while (true) {

		if (pointer >= colors.length) {
			return colors;
		}

		if (colors[pointer] == 0) {
			colors[pointer] = 1;
		}

		if (colorIsValid(matrix, colors, k, pointer)) {
			pointer++;
		} else {
			if (colors[pointer] < k) {
				colors[pointer]++;
			} else {
				pointer--;
				if (pointer < 0) {
					for (int i = 0; i < colors.length; i++) {
						colors[i] = -1;
					}
					return colors;
				}
				for (int i = pointer+1; i < colors.length; i++) {
					colors[i] = 0;
				}
				colors[pointer]++;
			}
		}
	}
}

public static boolean colorIsValid(boolean[][] matrix, int[] colors, int k, int pointer) {

	if (colors[pointer] > k) {
		return false;
	}

	for (int i = 0; i < matrix.length; i++) {
		if (matrix[pointer][i]) {
			if (colors[pointer] == colors[i]) {
				return false;
			}
		}
	}
	return true;
}
//END
