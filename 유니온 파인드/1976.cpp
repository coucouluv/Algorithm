#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int city[201];
int visited[201];

int unionfind(int a) {
    if(city[a] == -1) return a;
    city[a] = unionfind(city[a]);
    return city[a];
}
void unionmerge(int a, int b) {
     a = unionfind(a);
     b = unionfind(b);
     if(a == b) return;
     city[a] = b;
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >>n;
    cin >>m;

    int tmp;

    fill(city, city+201, -1);
    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= n; j++) {
            cin >> tmp;
            if(tmp == 1) {
                unionmerge(i, j);
            }
        }
    }

    int pre, cur;
    bool flag = true;
    cin >> pre;
    visited[pre] = 1;
    for(int i = 1; i < m; i++) {
        cin >> cur;
        if(!visited[cur]) {
            visited[cur] = 1;
            if(unionfind(pre) == unionfind(cur)) {
                pre = cur;
                continue;
            }
            else {
                flag = false;
                break;
            }
        }
    }

    if(flag)
        cout << "YES\n";
    else
        cout << "NO\n";

    return 0;
}
