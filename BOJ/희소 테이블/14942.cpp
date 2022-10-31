#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
const int MAX = 100001;
int f[MAX][18] = {0,};
int d[MAX][18] = {0,};
vector <pair <int, int>> path[MAX];

void getparent(int index) {


    for(int i = 0; i < path[index].size(); i++) {
        int child = path[index][i].first;
        int c = path[index][i].second;
        if(f[child][0] != 0 || child == 1)
            continue;
        f[child][0] = index;
        d[child][0] = c;
        getparent(child);
    }

}

int main() {

    int n;
    cin >> n;

    int ant[n+1];

    for(int i = 1; i <= n; i++)
        cin >> ant[i];

    for(int i = 1; i < n; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        path[a].push_back(make_pair(b,c));
        path[b].push_back(make_pair(a,c));
    }

    getparent(1);

    for(int j = 1; j < 18; j++) {
        for(int i = 1; i <= n; i++) {
            f[i][j] = f[f[i][j-1]][j-1];
            d[i][j] = d[i][j-1] + d[f[i][j-1]][j-1];
        }
    }

    for(int i = 1; i <= n; i++) {
        int energy = ant[i];
        int index = 17;
        int room = i;
        while(index >= 0 && energy > 0) {
            if(f[room][index] != 0 && d[room][index] <= energy) {
                energy -= d[room][index];
                room = f[room][index];
            }
            index--;
            if(room == 1)
                break;
        }
        cout << room <<"\n";
    }

    return 0;
}
