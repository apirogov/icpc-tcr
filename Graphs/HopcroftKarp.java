/* Hopcroft-Karp
 * Finds a maximum matching in a bipartite graph. \\
 * Input: The disjunct bipartite vertex sets $U$ and $V$ and the set of all edges. \\
 * Output: A set of edges holding the maximum bipartite matching.
 ** |E| \cdot \sqrt{V}
 */
//START
public static Set<Edge> hopcroftKarp(Set<Vertex> U, Set<Vertex> V, Set<Edge> edges) {

	Set<Edge> matching = new HashSet<Edge>();

	Vertex nil = new Vertex();
	HashMap<Vertex,Vertex> pairU = new HashMap<Vertex,Vertex>();
	HashMap<Vertex,Vertex> pairV = new HashMap<Vertex,Vertex>();

	for (Vertex u : U)
		pairU.put(u,nil);

	for (Vertex v : V)
		pairV.put(v,nil);

	while(bfsForHopcroftKarp(U, V, nil, pairU, pairV)) {
		for (Vertex u : U) {
			if (pairU.get(u) == nil) {
				dfsForHopcroftKarp(u,nil,pairU,pairV);
			}
		}
	}

	for (Edge e : edges) {
		if ((pairU.containsKey(e.u) && pairU.get(e.u) == e.v) || (pairV.containsKey(e.u) && pairV.get(e.u) == e.v) ||
				(pairU.containsKey(e.v) && pairU.get(e.v) == e.u) || (pairV.containsKey(e.v) && pairV.get(e.v) == e.u)) {
			matching.add(e);
		}
	}

	return matching;
}

public static boolean dfsForHopcroftKarp(Vertex v, Vertex nil, HashMap<Vertex,Vertex> pairU, HashMap<Vertex,Vertex> pairV) {
	if (v != nil) {
		for (Edge e : v.edges) {
			Vertex u = e.giveAdjacentVertex(v);
			if (pairV.get(u).dist == v.dist + 1) {
				if (dfsForHopcroftKarp(pairV.get(u),nil,pairU,pairV)) {
					pairV.put(u,v);
					pairU.put(v,u);
					return true;
				}
			}
		}
		v.dist = Integer.MAX_VALUE;
		return false;
	}
	return true;
}

public static boolean bfsForHopcroftKarp(Set<Vertex> U, Set<Vertex> V, Vertex nil, HashMap<Vertex,Vertex> pairU, HashMap<Vertex,Vertex> pairV) {

	Queue<Vertex> Q = new LinkedList<Vertex>();

	for (Vertex v : U) {
		if (pairU.get(v) == nil) {
			v.dist = 0;
			Q.offer(v);
		} else {
			v.dist = Integer.MAX_VALUE;
		}
	}
	nil.dist = Integer.MAX_VALUE;

	while (!Q.isEmpty()) {
		Vertex v = Q.poll();
		if (v.dist < nil.dist) {
			for (Edge e : v.edges) {
				Vertex u = e.giveAdjacentVertex(v);
				if (pairV.get(u).dist == Integer.MAX_VALUE) {
					pairV.get(u).dist = v.dist + 1;
					Q.offer(pairV.get(u));
				}
			}
		}
	}

	return nil.dist != Integer.MAX_VALUE;
}
//END
