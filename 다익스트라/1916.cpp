#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int, int> P;
const int INF = 1000000000;

int main() {

    int N, M;
    cin >> N >> M;

    vector <P> road[N+1];

    for(int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        road[a].push_back(P(b,c));
    }

    int stcity, encity;
    cin >> stcity >> encity;

    int result[N+1];
    fill(result, result+N+1, INF);
    result[stcity] = 0;

    priority_queue<P, vector<P>, greater <>> pq;
    pq.push(P(0, stcity));

    while(!pq.empty()) {
        int cur = pq.top().second;
        int w = pq.top().first;
        pq.pop();
        if(result[cur] < w)
            continue;
        for(int i = 0; i < road[cur].size(); i++) {
            int next = road[cur][i].first;
            if(result[next] > w + road[cur][i].second) {
                result[next] = w + road[cur][i].second;
                pq.push(P(result[next], next));
            }
        }

    }
    cout << result[encity] << "\n";
    return 0;
}
