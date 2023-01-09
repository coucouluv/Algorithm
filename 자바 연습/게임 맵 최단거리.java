import java.util.*;
class Solution {
    private int []x = {-1,1,0,0};
    private int []y = {0,0,-1,1};
    private int [][]visited;
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        visited = new int[n][m];

        bfs(n,m, maps);
        if(visited[n-1][m-1] == 0)
            return -1;
        return visited[n-1][m-1];
    }
    public void bfs(int n, int m,int [][]maps) {
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(0,0));
        visited[0][0] = 1;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + x[i];
                int ny = cur.y + y[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if(maps[nx][ny] == 0)
                    continue;
                if(visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                    que.add(new Pair(nx,ny));
                }
            }
        }
    }
}
class Pair{
    int x;
    int y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}