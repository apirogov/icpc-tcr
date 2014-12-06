/* Dijkstra
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP). \\
 * Input: A source vertex $s$ and an adjacency matrix $G$. \\
 * Output: Either a map of all distances (dist) or all predecessors (prev).
 ** |E|\log|V|
 */
//START
public static Map<Integer, Double> dijkstra(int s, double[][] G) {
	Map<Integer, Double> dist = new HashMap<Integer, Double>();
	Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
	PriorityQueue<Vertex> H = new PriorityQueue<Vertex>();
	prev.put(s, s);
	Vertex[] vertices = new Vertex[G.length];
	// init dist with infity
	for (int i = 0; i < G.length; i++) {
		if (i == s) {
			dist.put(s, 0.0);
		} else {
			dist.put(i, Double.MAX_VALUE);
		}
		vertices[i] = new Vertex();
		vertices[i].id = i;
		vertices[i].valueToCompare = dist;
		H.offer(vertices[i]);
	}
	while (!H.isEmpty()) {
		int u = H.poll().id;
		for (int v = 0; v < G.length; v++) {
			if (G[u][v] > 0) {
				if (dist.get(v) > dist.get(u) + G[u][v]) {
					dist.put(v, dist.get(u) + G[u][v]);
					prev.put(v, u);
					H.remove(vertices[v]);
					H.add(vertices[v]);
				}
			}
		}
	}

//	System.out.println("-------------------------------------------");
//	for (int i = 0; i < G.length; i++) {
//		int v = i + 1;
//		double d = dist.get(i);
//		int p = prev.get(i) + 1;
//		System.out.printf("| Vertex: %5d | Distance: %5d | Prev: %5d |", v, d, p);
//	}
//	System.out.println("-------------------------------------------");

	return dist;
}
//END
