#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1000000000;
typedef pair<int, int> P;

int main() {

    int V, E;
    cin >> V >> E;

    int start;
    cin >> start;

    vector <P> graph[V+1];
    for(int i = 0; i < E; i++) {
        int u,v,w;
        cin >> u >> v >> w;
        graph[u].push_back(make_pair(v,w));
    }


    int result[V+1];
    int visited[V+1];
    fill(result, result+V+1, INF);
    fill(visited, visited+V+1, 0);

    priority_queue<P, vector<P>, greater<>> pq;
    result[start] = 0;
    pq.push(P(0,start));

    while(!pq.empty()) {
        int current = pq.top().second;
        pq.pop();
        while(!pq.empty() && visited[current]) {
            current = pq.top().second;
            pq.pop();
        }
        if(visited[current])
            break;
        for(int i = 0; i < graph[current].size(); i++) {
            int next = graph[current][i].first;
            int d = graph[current][i].second;
            if(result[next] > result[current]+d) {
               result[next] = result[current]+d;
               pq.push(P(result[next], next));
            }

        }

    }


    for(int i = 1; i < V+1; i++) {
        if(result[i] == INF)
            cout << "INF\n";
        else
            cout << result[i] << "\n";
    }
    return 0;
}
