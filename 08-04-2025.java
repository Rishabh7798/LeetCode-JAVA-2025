Bridge edge in a graph

class Solution {
  public:
    void dfs(int u, int parent, vector<int> adj[], vector<bool> &visited, 
             vector<int> &disc, vector<int> &low, vector<pair<int,int>> &bridges, int &timer) {
        visited[u] = true;
        disc[u] = low[u] = timer++;
        
        for (int v : adj[u]) {
            if (v == parent) continue;

            if (!visited[v]) {
                dfs(v, u, adj, visited, disc, low, bridges, timer);
                low[u] = min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridges.push_back({u, v});
                }
            } else {
                low[u] = min(low[u], disc[v]);
            }
        }
    }

    bool isBridge(int V, vector<vector<int>> &edges, int c, int d) {
        vector<int> adj[V];
        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        vector<bool> visited(V, false);
        vector<int> disc(V, -1), low(V, -1);
        vector<pair<int, int>> bridges;
        int timer = 0;

        for (int i = 0; i < V; ++i) {
            if (!visited[i]) {
                dfs(i, -1, adj, visited, disc, low, bridges, timer);
            }
        }

        for (auto &b : bridges) {
            if ((b.first == c && b.second == d) || (b.first == d && b.second == c))
                return true;
        }
        return false;
    }
};
