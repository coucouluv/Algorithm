#include <iostream>
#include <queue>
#include <vector>
using namespace std;

const int INF = 100000;
int x[] = {1, -1, 0, 0};
int y[] = {0, 0, 1, -1};

typedef pair<int, pair<int , int>> P;
int main() {

    int N, M;
    cin >> N >> M;

    int miro[M][N];
    string tmp;
    for(int i = 0; i < M; i++) {
        cin >> tmp;
        for(int j = 0; j < N; j++)
            miro[i][j] = tmp[j] - '0';
    }
    int result[M][N];
    fill(&result[0][0], &result[M-1][N], INF);

    priority_queue<P, vector<P>, greater<>>pq;
    pq.push(P(0,make_pair(0,0)));

    while(!pq.empty()) {
        int curx = pq.top().second.first;
        int cury = pq.top().second.second;
        int curcost = pq.top().first;
        pq.pop();
        //if()
        for(int i = 0; i < 4; i++) {
            int nextx = curx + x[i];
            int nexty = cury + y[i];
            if(nextx < 0 || nextx >= M || nexty < 0 || nexty >= N) continue;
            if(nextx == 0 && nexty == 0) continue;
            if(result[nextx][nexty] > miro[curx][cury]+curcost) {

                result[nextx][nexty] = miro[curx][cury]+curcost;
                //cout << "x :" << nextx << " y :" << nexty <<" cost :" <<result[nextx][nexty] <<endl;
                pq.push(P(result[nextx][nexty], make_pair(nextx, nexty)));
            }
        }
    }

    if (result[M-1][N-1] == INF)
        cout << "0\n";
    else
        cout << result[M-1][N-1] << "\n";
    return 0;
}
