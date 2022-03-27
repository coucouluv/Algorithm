#include <iostream>
#include <stack>
#include <algorithm>
using namespace std;

int n, m, q;
int power[100001];
stack<pair<int,int>> last;

int rootfind(int a) {
    if(power[a] < 0) return a;
    return power[a] = rootfind(power[a]);
}

void unionmerge(int a, int b) {
    a = rootfind(a);
    b = rootfind(b);
    if(a == b) return;
    power[a] += power[b];
    power[b] = a;
    return;
}

int main() {

    cin >> n >> m >> q;

    fill(power,power+m+1,-1);
    int a[m+1][2];
    for(int i = 1; i <= m; i++) {
        cin >> a[i][0] >> a[i][1];

    }

    int tmp;
    for(int i = 0; i < q; i++) {
        cin >> tmp;
        last.push({a[tmp][0],a[tmp][1]});
        a[tmp][0] = 0;
        a[tmp][1] = 0;
    }

    for(int i = 1; i <= m; i++) {
        if(a[i][0] != 0) {
            unionmerge(a[i][0], a[i][1]);
        }
    }

    long long result = 0;
    while(!last.empty()) {
        int x = last.top().first;
        int y = last.top().second;
        last.pop();
        x = rootfind(x);
        y = rootfind(y);
        if(x != y) {
            result += power[x] * power[y];
            power[x] += power[y];
            power[y] = x;
        }
    }
    cout << result << "\n";
    return 0;
}
