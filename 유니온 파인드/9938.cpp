#include <iostream>
#include <cstdlib>
using namespace std;

int n, l;
int drawer[300001];
int drink[300001];

int rootfind(int a) {
    if(drawer[a] < 0) return a;
    return drawer[a] = rootfind(drawer[a]);
}

int unionmerge(int a, int b) {
    a = rootfind(a);
    b = rootfind(b);
    if(a == b) return b;
    drawer[b] += drawer[a];
    drawer[a] = b;
    drink[b] += drink[a];
    return b;
}

//bool drawerfind(int a, int i) {
//    if(drink[a] == 0) {
//        drink[a] = i;
//        return true;
//    }
//    drawerfind(drawer[a], i);
//    return false;
//}
//
//void print1() {
//    for(int i = 1; i <= l; i++)
//        cout << i << ":" << drawer[i] << endl;
//
//}
//
//void print2() {
//    for(int i = 1; i <= l; i++)
//        cout << i << ":" << drink[i] << endl;
//}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n >>l;

    int a, b;

    for(int i = 1; i <= l; i++)
        drawer[i] = -1;


    for(int i = 1; i <= n; i++) {
        cin >> a >> b;
        int tmp =unionmerge(a,b);
        if(drink[tmp] < abs(drawer[tmp])) {
            cout << "LADICA\n";
            drink[tmp] += 1;
        }
        else
            cout << "SMECE\n";

//        if(drawerfind(a, i)) {
//            cout << "LADICA\n";
//        }
//        else {
//            cout << "SMECE\n";
//        }
        //print1();
        //print2();
    }

    return 0;
}
