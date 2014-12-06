 public int minCycle(int n, int m, ArrayList<LinkedList<Integer>> adj){
	int min = Integer.MAX_VALUE;
	int[] length = new int[n];
	int[] prev = new int[n];
	for (int start = 0; start < n; start++) {
		Arrays.fill(length, -1);
		Arrays.fill(prev, -1);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		length[start] = 0;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			if (2*length[u] >= min )
				break;
			for (int v : adj.get(u)) {
				if (length[v] < 0 ) {
					length[v] = length[u] + 1;
					prev[v] = u;
					if(length[v]<min){
						queue.add(v);
					}
				} else if( prev[u] != v && prev[v]!=u){
					min = Math.min(length[v] + length[u] + 1, min);
				}
			}
		}
	}
	return min;
 }