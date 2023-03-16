import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, K;
    private static Queue<Road> que = new LinkedList<>();
    private static int [][]board;
    private static int []x = {0,0,-1,1};
    private static int []y = {-1,1,0,0};
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            int max = 0;
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, board[j][k]);
                }
            }
            findMax(max);
            result = 0;

            bfs();
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static void findMax(int max) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if(board[j][k] == max) {
                    int [][]visited = new int[N][N];
                    visited[j][k] = board[j][k];
                    que.add(new Road(j,k,1,false, visited));
                }
            }
        }
    }

    public static void bfs() {
        while(!que.isEmpty()) {
            Road cur = que.poll();
            result = Math.max(result, cur.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = x[i] + cur.x;
                int ny = y[i] + cur.y;
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(cur.visited[nx][ny] == 0) {
                    if(board[nx][ny] < cur.visited[cur.x][cur.y]) {
                        cur.visited[nx][ny] = board[nx][ny];
                        que.add(new Road(nx,ny,cur.cnt+1, cur.state, cur.visited));
                        cur.visited[nx][ny] = 0;
                    }
                    else if(cur.state == false) {
                        if(board[nx][ny] - K < cur.visited[cur.x][cur.y]) {
                            cur.visited[nx][ny] = cur.visited[cur.x][cur.y]-1;
                            que.add(new Road(nx,ny,cur.cnt+1, true, cur.visited));
                            cur.visited[nx][ny] = 0;
                        }
                    }
                }
            }
        }
    }
}



class Road {
    int x;
    int y;
    int cnt;
    boolean state;

    int [][]visited;


    public Road(int x, int y, int cnt, boolean state, int[][] visited) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.state = state;

        this.visited = new int[visited.length][visited.length];
        for (int j = 0; j < visited.length; j++) {
            for (int k = 0; k < visited.length; k++) {
                this.visited[j][k] = visited[j][k];
            }
        }
    }

    public void print(int N) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                System.out.print(visited[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}