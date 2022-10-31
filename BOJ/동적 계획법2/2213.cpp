#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

int n;
int w[10001];
int visit[10001];
vector <int> tmp[10001];
vector <int> tree[10001];
int dp[10001][2];
vector <int> result;

void maketree(int root) {

    visit[root] = 1;
    for(int i = 0; i < tmp[root].size(); i++) {
        if(!visit[tmp[root][i]]) {
            tree[root].push_back(tmp[root][i]);
            maketree(tmp[root][i]);
        }
    }

}

int solution(int vertex, int pick) {

    int &ret = dp[vertex][pick];
    if(ret != -1) return ret;


    if(pick == 1) {
        ret = w[vertex];
        for(int i = 0; i < tree[vertex].size(); i++) {
            ret += solution(tree[vertex][i], 0);
        }
    }
    else {
        ret = 0;
        for(int i = 0; i < tree[vertex].size(); i++) {
            ret += max(solution(tree[vertex][i], 0), solution(tree[vertex][i], 1));
        }
    }
    return ret;
}

void findresult(int past, int cur) {
    if(dp[cur][1] > dp[cur][0] && visit[past] == 0) {
        result.push_back(cur);
        visit[cur] = 1;
    }
    for(int i = 0; i < tree[cur].size(); i ++) {
        findresult(cur, tree[cur][i]);
    }

}

int main() {

    cin >> n;

    for(int i = 1; i <= n; i++)
        cin >> w[i];

    int a, b;
    for(int i = 0; i < n-1; i++) {
        cin >> a >> b;
        tmp[a].push_back(b);
        tmp[b].push_back(a);
    }

    maketree(1);
    memset(dp, -1, sizeof(dp));

    cout << max(solution(1, 0), solution(1,1)) << endl;

    memset(visit, 0, sizeof(visit));
    findresult(0,1);
    sort(result.begin(), result.end());
    for(int i = 0; i < result.size(); i++)
        cout << result[i] << " ";
    cout << "\n";
    return 0;
}
