/* Edmonds-Karp max flow
 ** VE^2
 */
#include <iostream>
using namespace std;

// Edmonds-Karp flow
#include <limits>
#include <vector>
#include <queue>

//START
typedef int vert_t;
typedef long long int cap_t;
typedef vector<vector<cap_t> > capmat_t;
typedef vector<vector<vert_t> > graph_t; // graph as adjacency list
typedef vector<vert_t> path_ps_t;
const cap_t cap_infty = numeric_limits<cap_t>::max() / 2;

path_ps_t bfs( graph_t g, capmat_t caps, vert_t s, vert_t t ) {
    path_ps_t ps( g.size(), -1 ); // parents
    ps[s] = s;
    queue<vert_t> q;
    q.push(s);
    while( !q.empty() ) {
        vert_t u = q.front(); q.pop();
        if( u == t ) return ps;
        for( int i=0; i<g[u].size(); i++ ) {
            vert_t v = g[u][i];
            if( ps[v] < 0 && caps[u][v] > 0 ) {
                ps[v] = u;
                q.push(v);
            }
        }
    }
}

cap_t max_flow( graph_t g, capmat_t caps, vert_t s, vert_t t ) {
    cap_t flow = 0; // current flow
    while(1) {
        path_ps_t ps = bfs( g, caps, s, t );
        if( ps[t] < 0 ) break; // no path with pos. flow
        cap_t pc = cap_infty; // path capacity
        for(vert_t v = t; v != s; v = ps[v]) {
            vert_t u = ps[v];
            pc = min(pc, caps[u][v]);
        }
        // update residual graph
        for(vert_t v = t; v != s; v = ps[v]) {
            vert_t u = ps[v];
            caps[u][v] -= pc;
            caps[v][u] += pc;
        }
        flow += pc;
    }
    return flow;
}

int main() {
    int network = 1;
    while(1) {
        int n,s,t,e;
        cin >> n;
        if(n == 0) break;

        cin >> s >> t >> e;

        graph_t g(n);
        capmat_t caps(n, vector<cap_t>(n,0));

        for(int i=0; i<e; i++) {
            int u,v,c;
            cin >> u >> v >> c;
            g[u-1].push_back(v-1);
            g[v-1].push_back(u-1);
            caps[u-1][v-1] += c;
            caps[v-1][u-1] += c;
        }

        cout << "Network " << network++ << endl;
        cout << "The bandwidth is " << max_flow(g, caps, s-1, t-1) << "." << endl << endl;
    }
}
//END
