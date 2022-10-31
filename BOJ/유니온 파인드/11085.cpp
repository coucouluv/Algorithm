#include <iostream>
#include <tuple>
#include <algorithm>
#include <vector>
using namespace std;
int p, w;
int c, v;
int scope[1001];

vector <tuple <int, int, int>> road;

int findroot(int a) {
    if(scope[a] == -1) return a;
    return scope[a] = findroot(scope[a]);
}


void roadmerge(int a, int b) {
    a = findroot(a);
    b = findroot(b);
    if (a == b) return;
    scope[b] = a;
    return ;
}


int main() {

    cin >> p >> w;
    cin >> c >> v;

    int st, en, wid;
    for(int i = 0; i < w; i++) {
        cin >> st >> en >> wid;
        road.push_back({wid, st, en});
        //cin >> road[i][1] >> road[i][2] >> road[i][0];

    }
    sort(road.begin(), road.end(), greater<>());
    fill(scope, scope+1001, -1);

    int i;
    for(i = 0; i < w; i++) {
        roadmerge(get<1>(road[i]),get<2>(road[i]));
        if(findroot(c) == findroot(v))
            break;
        //cout << get<0>(road[i]) << " " <<  get<1>(road[i]) << " " << get<2>(road[i]) << endl;

    }
    cout << get<0>(road[i]) << "\n";
    return 0;
}
