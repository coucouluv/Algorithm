import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M, L;

    private static int [][]board;
    private static int [][]visited;
    private static int []x = {-1,1,0,0}; // 상하좌우
    private static int []y = {0,0,-1,1};

    private static int[][]pipes = {
            {0,0,0,0},{0,1,2,3},{0,1,-1,-1}, {2,3,-1,-1}, {0,3,-1,-1}, {1,3,-1,-1}, {1,2,-1,-1}, {0,2,-1,-1}
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            L = Integer.parseInt(st.nextToken());
            board = new int[N][M];
            visited = new int[N][M];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            bfs(R, C);
            int result = calculate();
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
    public static int calculate() {
        int ret = 0;
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                if(visited[j][k] > 0) {
                    ret++;
                }
            }
        }
        return ret;
    }
    public static boolean isPossible(int curd, int nx, int ny) {
        int num = board[nx][ny];

        for (int i = 0; i < 4; i++) {
            if(pipes[num][i] == -1) break;
            if(curd % 2 == 0 && pipes[num][i] == curd+1) {
                return true;
            }
            else if(curd % 2 == 1 && pipes[num][i] == curd-1) {
                return true;
            }
        }
        return false;
    }
    public static void bfs(int R, int C) {
        Queue<Pair> que = new LinkedList<>();

        que.add(new Pair(R, C));
        visited[R][C] = 1;

        while(!que.isEmpty()) {
            Pair cur = que.poll();

            if(visited[cur.x][cur.y] >= L) {
                continue;
            }
            int num = board[cur.x][cur.y];
            for(int i = 0; i < 4; i++) {
                if(pipes[num][i] == -1) break;
                int nx = x[pipes[num][i]] + cur.x;
                int ny = y[pipes[num][i]] + cur.y;
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] <= 0 && board[nx][ny] > 0) {
                    if(isPossible(pipes[num][i], nx, ny)) {
                        visited[nx][ny] = visited[cur.x][cur.y] + 1;
                        que.add(new Pair(nx, ny));
                    }
                }
            }

        }
    }

}
class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

