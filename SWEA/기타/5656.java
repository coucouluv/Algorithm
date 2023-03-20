import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, W, H;

    private static int [][]board;
    private static int []x = {0,0,-1,1};
    private static int []y = {-1,1,0,0};
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[H][W];
            for (int j = 0; j < H; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < W; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            result = Integer.MAX_VALUE;
            dfs(0);
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static int countNum() {
        int ret = 0;
        for (int j = 0; j < H; j++) {
            for (int k = 0; k < W; k++) {
                if(board[j][k] > 0) {
                    ret++;
                }
            }
        }
        return ret;
    }

    public static int[][] copyBoard(int[][] origin, int[][] tmp) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                tmp[i][j] = origin[i][j];
            }
        }
        return tmp;
    }

    public static void removeBoard(int h, int w, int num) {
        Queue<Ball> queue = new LinkedList<>();
        queue.add(new Ball(w, h, num));
        board[h][w] = 0;
        while(!queue.isEmpty()) {
            Ball cur = queue.poll();
            for (int i = 1; i <= cur.num-1; i++) {
                for (int j = 0; j < 4; j++) {
                    int nH = cur.h + x[j] * i;
                    int nW = cur.w + y[j] * i;
                    if(nH < 0 || nH >= H || nW < 0 || nW >= W) continue;
                    if(board[nH][nW] == 1) {
                        board[nH][nW] = 0;
                    }
                    else if(board[nH][nW] > 1) {
                        queue.add(new Ball(nW, nH, board[nH][nW]));
                        board[nH][nW] = 0;
                    }
                }
            }
        }
    }
    public static void downBoard() {
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < W; i++) {
            for(int j = H-1; j >= 0; j--) {
                if(board[j][i] != 0) {
                    que.add(board[j][i]);
                }
            }
            for(int j = H-1; j >= 0; j--) {
                if(que.isEmpty()) {
                    board[j][i] = 0;
                }
                else {
                    board[j][i] = que.poll();
                }
            }
        }
    }

    public static void dfs(int cnt) {
        result = Math.min(result, countNum());
        if(cnt == N) {
            return;
        }
        int [][]tmp = copyBoard(board, new int[H][W]);

        for(int i = 0; i < W; i++) {
            for(int j = 0; j < H; j++) {
                if(board[j][i] != 0) {
                    removeBoard(j, i, board[j][i]);
                    downBoard();
                    dfs(cnt+1);
                    copyBoard(tmp, board);
                    break;
                }
            }
        }
    }
    public static void print() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
class Ball {
    int w;
    int h;
    int num;
    public Ball(int w, int h, int num) {
        this.w = w;
        this.h = h;
        this.num = num;
    }
}

