/* Suurballe
 * Finds two edge-disjoint paths from s to t with minimal sum length, depends on
 *  Dijkstra. Add to Vertex class 2 HashMaps backupNext and resultSuurballe.
 * For also vertex-disjoint paths split vertices in in- and outgoing vertices
 * connected with zero-valued edges.
 */
import java.util.*;
class Vertex implements Comparable<Vertex> {
	public int id;
	public int dist;
	public int pred;
	public HashMap<Integer,Integer> next = new HashMap<Integer,Integer>();
	public HashMap<Integer,Integer> backupNext = new HashMap<Integer,Integer>();
	public HashMap<Integer,Integer> suurbaleResult = new HashMap<Integer,Integer>();
	public Vertex(int i){id=i;}
	public int compareTo(Vertex v) {
		if (dist < v.dist) return -1;
		else if (dist > v.dist) return 1;
		return 0;
	}
}
class Suurballe {
//START
public static int suurballe(int s, int t, HashMap<Integer,Vertex> G) {
	dijkstra(s, G); //find a shortest path
	ArrayList<Integer> path = new ArrayList<Integer>();
	int id = t;
	while (G.get(id).pred != id) {
		path.add(0, id);
		id = G.get(id).pred;
	}
	path.add(0, id);

	//modify weights
	for (Integer i : G.keySet()) {
		Vertex u = G.get(i);
		u.backupNext = new HashMap<Integer,Integer>(u.next); //copy old values
		for (Integer j : u.backupNext.keySet()) {
			Vertex v = G.get(j);
			int weight = u.next.get(j);
			u.next.put(j, weight - v.dist + u.dist);

		}
	}
	//reverse edges on shortest path
	id = s;
	for (int i=0; i<path.size()-1; i++) {
		G.get(path.get(i)).next.remove(path.get(i+1));
		G.get(path.get(i+1)).next.put(path.get(i), 0);
	}
	//remove edges to s
	for (Integer i : G.keySet()) {
		if (G.get(i).next.containsKey(s))
			G.get(i).next.remove(s);
	}

	dijkstra(s, G);
	ArrayList<Integer> path2 = new ArrayList<Integer>();
	id = t;
	if (G.get(id).pred == -1)
		return -1; //no 2nd path!

	while (G.get(id).pred != id) {
		path2.add(0, id);
		id = G.get(id).pred;
	}
	path2.add(0, id);

	int totalpath = 0;

	//disregard 0-cycles and edges not on both paths
	id = s;
	//add edges on first shortest path
	for (int i=0; i<path.size()-1; i++) {
		int u = path.get(i);
		int v = path.get(i+1);

		G.get(u).suurbaleResult.put(v, G.get(u).backupNext.get(v));
		totalpath += G.get(u).suurbaleResult.get(v);
	}
	//add second path, remove cycles
	for (int i=0; i<path2.size()-1; i++) {
		int u = path2.get(i);
		int v = path2.get(i+1);

		if (G.get(v).suurbaleResult.containsKey(u)) {
			totalpath -= G.get(v).suurbaleResult.get(u);
			G.get(v).suurbaleResult.remove(u);
		} else {
			G.get(u).suurbaleResult.put(v, G.get(u).backupNext.get(v));
			totalpath += G.get(u).suurbaleResult.get(v);
		}
	}

	return totalpath;
}
//END
	public static void dijkstra(int s, Map<Integer,Vertex> G) {
		PriorityQueue<Vertex> H = new PriorityQueue<Vertex>();

		Vertex start = G.get(s);
		start.pred = start.id;
		for (int i : G.keySet()) { // init dists with infty
			Vertex ivert = G.get(i);
			ivert.id = i;
			ivert.dist = Integer.MAX_VALUE;
			ivert.pred = -1;
		}
		start.pred = start.id; //init start vertex
		start.dist = 0;
		H.add(G.get(s));
		while (!H.isEmpty()) {
			Vertex u = H.poll();
			for (int v : u.next.keySet()) {
				Vertex vert = G.get(v);
				int utov = u.next.get(v);
				if (vert.dist > u.dist + utov) {
					vert.dist = u.dist + utov;
					vert.pred = u.id;
					H.remove(vert);
					H.add(vert);
				}
			}
		}
	}
}
