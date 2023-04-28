import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] board;
    private static int[] dx = {1,1,-1,-1};
    private static int[] dy = {1,-1,-1,1};
    private static boolean[][] visited;
    private static boolean[] desert;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            visited = new boolean[N][N];
            desert = new boolean[101];
            result = 0;
            for (int j = 0; j < N-2; j++) {
                for (int k = 1; k < N-1; k++) {
                    desert[board[j][k]] = true;
                    visited[j][k] = true;
                    dfs(1, j, k, j, k, 0);
                    desert[board[j][k]] = false;
                    visited[j][k] = false;
                }
            }
            sb.append("#").append(i).append(" ");
            if(result == 0) {
                sb.append("-1\n");
            }
            else {
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
    }
    private static void dfs(int cnt, int x, int y, int stx, int sty, int direct) {

        for (int i = direct; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(nx == stx && ny == sty && cnt > 2) {
                result = Math.max(result, cnt);
                return;
            }
            if(!visited[nx][ny] && !desert[board[nx][ny]]) {
                visited[nx][ny] = true;
                desert[board[nx][ny]] = true;
                dfs(cnt+1, nx, ny, stx, sty, i);
                visited[nx][ny] = false;
                desert[board[nx][ny]] = false;
            }
        }
    }
}