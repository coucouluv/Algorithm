import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int result = 0;
    private static int [][] board = new int [52][12];
    private static boolean []visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <= 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int []player = {0,0,0,1,0,0,0,0,0};
        visited = new boolean[10];
        solve(0,player);
        System.out.println(result);
    }
    public static void solve(int cnt, int []player) {
        if(cnt == 8) {
            //이닝 시뮬레이션
            int sum = play(player);
            //System.out.println(sum);
            result = Math.max(result, sum);
            return;
        }
        for(int i = 2; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(cnt >= 3)
                    player[cnt+1] = i;
                else
                    player[cnt] = i;
                solve(cnt + 1, player);
                visited[i] = false;
            }
        }
    }
    public static int play(int []player) {
        int ret = 0, index = 0;
        // 각 이닝
        for(int i = 1; i <= N; i++) {
            int out = 0; //아웃 카운트
            int [] home = {0,0,0,0};//베이스 1루 2루 3루
            while(out < 3) {
                int playerNum = player[index];
                if(board[i][playerNum] == 0)
                    out += 1;
                else {
                    home[0] = 1;
                    for(int j = 3; j >= 0; j--) {
                        if(home[j] == 0) continue;
                        int next = j + board[i][playerNum];
                        if(next > 3) ret += 1;
                        else home[next] = 1;
                        home[j] = 0;
                    }
                }
                index += 1;
                if(index == 9) index = 0;
            }
        }
        return ret;
    }
}