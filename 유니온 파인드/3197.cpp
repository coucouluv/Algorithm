#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
int r, c;
int lake[1500][1500];
int scope[2250001];
queue <pair<int, int>> bfs1, bfs2;
int dx[] = {-1,1,0,0};
int dy[] = {0,0,-1,1};

int rootfind(int a) {
    if(scope[a] == a) return a;
    return scope[a] = rootfind(scope[a]);
}

bool unionmerge(int a, int b) {
    a = rootfind(a);
    b = rootfind(b);
    if(a == b) return true;
    scope[b] = a;
    return false;
}

//void print() {
//
//    for(int i = 0; i < r; i++) {
//        for(int j = 0; j < c; j++)
//            cout << lake[i][j] <<" ";
//        cout << endl;
//    }
//
//
//}

void scopemerge() {
    while(!bfs1.empty()) {
        int x = bfs1.front().first;
        int y = bfs1.front().second;
        bfs1.pop();
        bfs2.push({x,y});

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(lake[nx][ny] > 0 &&lake[nx][ny] != lake[x][y]) {
                unionmerge(lake[nx][ny], lake[x][y]);
            }
        }
    }
}

void spread() {
    while(!bfs2.empty()) {
        int x = bfs2.front().first;
        int y = bfs2.front().second;
        bfs2.pop();

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(lake[nx][ny] == 0) {
                lake[nx][ny] =  lake[x][y];
                bfs1.push({nx, ny});
            }
        }
    }

}

int main() {


    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> r >> c;

    char tmp;
    int index = 1;
    queue <pair<int, int>> swan;
    for(int i = 0; i < r; i++) {
        for(int j = 0; j < c; j++) {
            cin >> tmp;
            if(tmp == '.') {
                bfs1.push({i, j});
                lake[i][j] = index;
                scope[index] = index;
                index++;
            }
            else if(tmp == 'L') {
                bfs1.push({i, j});
                lake[i][j] = index;
                scope[index] = index;
                index++;
                swan.push({i, j});
            }
        }
    }
    int swx1 = swan.front().first;
    int swy1 = swan.front().second;
    swan.pop();
    int swx2 = swan.front().first;
    int swy2 = swan.front().second;
    int a, b;
    int result = 0;
    while(1) {
        scopemerge();
        a = rootfind(lake[swx1][swy1]);
        b = rootfind(lake[swx2][swy2]);
        if(a == b)
            break;
        spread();
        result++;
    }
    cout << result << "\n";
    return 0;
}
