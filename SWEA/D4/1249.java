import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T, N;
    private static int [][]board;

    private static boolean [][]visited;
    private static int []x = {0,0,-1,1};
    private static int []y = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                String tmp = st.nextToken();
                for (int k = 0; k < N; k++) {
                    board[j][k] = tmp.charAt(k) - '0';
                }
            }
            sb.append("#").append(i).append(" ").append(bfs()).append("\n");
        }
        System.out.print(sb);
    }
    public static int bfs() {
        PriorityQueue<Pair> que = new PriorityQueue<>();
        que.add(new Pair(0,0,0));
        visited[0][0] = true;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
//            System.out.println(cur.x + ", " + cur.y + ": " + cur.sum);
            if(cur.x == N-1 && cur.y == N-1) {
                return cur.sum;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + x[i];
                int ny = cur.y + y[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    que.add(new Pair(cur.sum+board[nx][ny], nx, ny));

                }
            }
        }
        return 0;
    }
}
class Pair implements Comparable<Pair>{
    int sum;
    int x;
    int y;

    public Pair(int sum, int x, int y) {
        this.sum = sum;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair o) {
        return this.sum - o.sum;
    }
}