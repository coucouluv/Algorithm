#include <iostream>

using namespace std;
int n, m;
int part[1000001];

int findunion(int a) {
    if(part[a] == -1)
        return a;
    part[a] = findunion(part[a]);
    return part[a];
}

void unionMerge(int a, int b) {
    a = findunion(a);
    b = findunion(b);
    if(a == b) return;
    part[a] = b;
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n >> m;

    for(int i = 0; i <= n; i++)
        part[i] = -1;

    int cmd, a, b;
    for(int i = 0; i < m; i++) {
        cin >> cmd >> a >> b;
        if(cmd == 0) {
            unionMerge(a, b);
        }
        else {
            if(findunion(a) == findunion(b))
                cout << "YES\n";
            else
                cout << "NO\n";
        }
    }
    return 0;
}
