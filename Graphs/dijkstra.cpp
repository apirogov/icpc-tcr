/* Dijkstra shortest path
 */
#include <set>
#include <vector>
#include <limits>
#include <iostream>

using namespace std;

//START
typedef int vert_t;
typedef long long int dist_t;
// div 2 guarantees absence of overflows
const dist_t dist_infty = numeric_limits<dist_t>::max() / 2;

typedef vector<vert_t> ps_t;
typedef vector<dist_t> dists_t;

typedef struct {
    vert_t to;
    dist_t len;
} edge_t;
typedef vector<vector<edge_t> > graph_t; // adj. lists


dists_t dijkstra_shortest_path(graph_t g, vert_t s) {
    set<pair<dist_t, vert_t> > q; // implicit ordering by distance
    
    // ps_t ps(g.size(), -1);
    
    dists_t dists(g.size(), dist_infty);
    dists[s] = 0;
    
    q.insert(make_pair(dists[s], s));
    while( !q.empty() ) {
        dist_t dist = q.begin()->first;
        vert_t v = q.begin()->second;
        q.erase( q.begin() ); // pop first
        
        for(int i=0; i<g[v].size(); i++) {
            vert_t t = g[v][i].to;
            dist_t d_with = dist + g[v][i].len;
            
            if( d_with < dists[t] ) { // relax
                q.erase( make_pair(dists[t], t) );
                q.insert( make_pair(d_with, t) );
                dists[t] = d_with;
                // ps[t] = v;
            }
        }
    }
    
    return dists;
}
//END
