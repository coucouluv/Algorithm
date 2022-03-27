#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int n, k;
int world[2001][2001];
int visited[100002];
queue<pair<int, int>> bfs1, bfs2;

int rootfind(int index) {;
    if(visited[index] == index) return index;
    visited[index] = rootfind(visited[index]);
    return visited[index];
}

bool unionfind(int a, int b) {
    a= rootfind(a);
    b= rootfind(b);
    if(a == b) return true;

    visited[a] = b;
    return false;
}

void bfsmerge() {
    while(!bfs1.empty()) {
        int x = bfs1.front().first;
        int y = bfs1.front().second;
        bfs1.pop();
        bfs2.push({x, y});
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx <= 0 || ny <= 0 || nx > n || ny > n) continue;
            if(world[nx][ny] > 0 && world[nx][ny] != world[x][y]) {
                if(!unionfind(world[nx][ny], world[x][y]))
                    k--;
            }
        }
    }
}

void bfsspread() {
    while(!bfs2.empty()) {
        int x = bfs2.front().first;
        int y = bfs2.front().second;
        bfs2.pop();
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx <= 0 || ny <= 0 || nx > n || ny > n) continue;
            if(world[nx][ny] == 0) {
                world[nx][ny] = world[x][y];
                bfs1.push({nx,ny});
            }
        }
    }
}
int main() {

    cin >> n >> k;

    int a, b;

    for(int i = 1; i <= k; i++) {
        cin >> a >> b;
        world[a][b] = i;
        bfs1.push({a,b});
        visited[i] = i;
    }

    int result = 0;
    while(1) {
        bfsmerge();
        if(k == 1) {
            cout << result << "\n";
            break;
        }
        bfsspread();
        result++;
    }
    return 0;
}
