#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef pair<int, int> P;
const int INF = 1000000000;

int main() {

    int T;
    cin >> T;

    for(int i = 0; i < T; i++) {
        int N, M, W;
        cin >> N >> M >> W;

        vector<P> road[N+1];
        int s, e, t;
        for(int j = 0; j < M ;j++) {
            cin >> s >> e >> t;
            road[s].push_back(P(e,t));
            road[e].push_back(P(s,t));
        }
        for(int j = 0; j < W ;j++) {
            cin >> s >> e >> t;
            road[s].push_back(P(e,-t));
        }
        bool cycle = false;
        int cost[N+1];
        cost[1] = 0;
        fill(cost, cost+N+1, INF);
        for(int j = 1; j <= N; j++) {
            for(int n = 1; n <= N; n++) {
                for(int k = 0; k < road[n].size(); k++) {
                    int next = road[n][k].first;
                    int nextcost = road[n][k].second;
                    if(cost[next] > cost[n]+ nextcost) {
                        cost[next] = cost[n]+ nextcost;
                        if(j == N) cycle = true;
                    }
                }
            }
        }
        if(cycle) cout << "YES\n";
        else cout << "NO\n";
    }


    return 0;
}
