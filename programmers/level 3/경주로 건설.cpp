#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cstring>
using namespace std;
int y[] = { -1,0,1,0 }; //상 좌 하 우
int x[] = { 0,-1,0,1 };
int total[28][28][4];
int solution(vector<vector<int>> board) {
    int answer = 987654321;
    int size = board.size();
    //{y, x좌표}, 이전 방향
    queue <pair<pair <int, int>, int>> que;
    que.push({ {0,0},-1, });
    while (!que.empty()) {
        int cy = que.front().first.first;
        int cx = que.front().first.second;
        int cdirec = que.front().second;
        que.pop();
        //cout << cy << ", " << cx << " : " << cdirec << ", "<< total[cy][cx][cdirec] << "\n";
        for (int i = 0; i < 4; i++) {
            int ny = cy + y[i];
            int nx = cx + x[i];
            int price = 0;
            if (ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
            if (board[ny][nx] == 1) continue;
            if (cdirec == -1 || cdirec == i) { //처음 or 직선
                price = 100;
            }
            //else if((cdirec % 2) == (i % 2)) continue; //다시 되돌아 가는 경우
            else { //코너 + 직선
                price = 600;
            }
            if (total[ny][nx][i] == 0 || total[ny][nx][i] > total[cy][cx][cdirec] + price) {
                total[ny][nx][i] = total[cy][cx][cdirec] + price;
                que.push({ {ny,nx},i });
            }

        }
    }
    for (int i = 0; i < 4; i++) {
        if (total[size - 1][size - 1][i] != 0)
            answer = min(answer, total[size - 1][size - 1][i]);
        //cout << total[size-1][size-1][i] << "\n";
    }
    return answer;
}