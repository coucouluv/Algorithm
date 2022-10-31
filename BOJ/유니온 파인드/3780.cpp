#include <iostream>
#include <cstdlib>
using namespace std;
int n;
int building[20001];
int parent[20001];

int rootfind(int a) {
    if(parent[a] == a) return a;
    int paren = rootfind(parent[a]);
    building[a] += building[parent[a]];
    return parent[a] = paren;
}

void unionmerge(int a, int b) {

    building[a] = abs(a-b)%1000;
    parent[a] = b;
    return;
}
void print() {

    for(int i = 1; i <= n; i++) {
        cout << i << ": " << parent[i] << " " << building[i] << endl;
    }

}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    char tmp;

    cin >> t;

    int a, b;
    for(int i = 0; i < t; i++) {
        cin >> n;
        for(int j = 1; j <= n; j++) {
            building[j] = 0;
            parent[j] = j;
        }
        while(1) {
            cin >> tmp;
            if(tmp == 'O')
                break;
            else if(tmp == 'E') {
                cin >> a;

                rootfind(a);
                cout <<building[a] << "\n";
            }
            else {
                cin >> a >> b;
                unionmerge(a, b);
            }
            print();
        }

    }

    return 0;
}
