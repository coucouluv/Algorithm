#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> P;
const int INF = 1000000000;

int main() {

    int N, M;

    cin >> N >> M;

    vector<P> bus[N+1];

    for(int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        bus[a].push_back(P(b,c));
    }

    long long cost[N+1];
    fill(cost, cost+N+1, INF);

    cost[1] = 0;
    bool cycle = false;
    for(int n = 1; n <= N; n++) {
        for(int i = 1; i < N; i++) {
            int cnt = bus[i].size();
            for(int j = 0; j < cnt; j++) {
                int next = bus[i][j].first;
                int nextcost = bus[i][j].second;
                if(cost[i] != INF) {
                    if(cost[next] > cost[i]+nextcost) {
                        cost[next] = cost[i]+nextcost;
                        if(n == N) cycle = true;
                    }
                }
            }
        }
    }
    if(cycle) {
        cout << "-1\n";
    }
    else{
        for(int i = 2; i <= N; i++) {
            if(cost[i] == INF)
                cout << "-1\n";
            else
                cout << cost[i] << "\n";
        }
    }
    return 0;
}
