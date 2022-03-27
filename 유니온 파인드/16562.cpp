#include <iostream>
#include <algorithm>
using namespace std;

int n, m, k;
int stu[10001];
int money[10001];

int findroot(int a) {
    if(stu[a] == -1) {
        return a;
    }

    stu[a] = findroot(stu[a]);
    return stu[a];
}


void mergeunion(int a, int b) {
    a = findroot(a);
    b = findroot(b);
    if(a == b) return;
    stu[b] = a;
    money[a] = min(money[b], money[a]);

}

int main() {

    cin >> n >> m >> k;

    for(int i = 1; i <= n; i++) {
        cin >> money[i];
    }

    int a, b;
    fill(stu, stu+10001, -1);

    for(int i = 0; i < m; i++) {
        cin >> a >> b;
        mergeunion(a, b);
    }

    int result = 0;
    for(int i = 1; i <= n; i++) {
        if(stu[i] == -1) {
            result += money[i];
        }
    }

    if(result <= k)
        cout <<result <<"\n";
    else
        cout << "Oh no\n";
    return 0;
}
