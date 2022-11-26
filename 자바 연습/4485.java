import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
    static int dx[] = {-1,1,0,0}; //상 하 좌 우
    static int dy[] = {0,0,-1,1};
    static int[][] board;
    static int [][] dist;
    private static final int MAX = Integer.MAX_VALUE / 4;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true) {
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            board = new int[N+5][N+5];
            dist = new int [N+5][N+5];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = MAX;
                }
            }
            int result = bfs(N);
            sb.append("Problem " + cnt + ": " + result).append('\n');
            cnt += 1;
        }
        System.out.println(sb);
    }
    private static int bfs(int N) {
        Queue <Pair> que = new LinkedList<>();
        dist[0][0] = board[0][0];
        que.add(new Pair(0,0));
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            if(cur.x == N-1 && cur.y == N-1) {
                continue;
            }
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if(dist[nx][ny] > (dist[cur.x][cur.y]+board[nx][ny])) {
                        dist[nx][ny] = dist[cur.x][cur.y]+board[nx][ny];
                        que.add(new Pair(nx,ny));
                    }
                }
            }
        }
        return dist[N-1][N-1];
    }
    private static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}