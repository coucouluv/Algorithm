import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int [][]loads;
    private static int [] visited;
    private static PriorityQueue<Pair> que = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken()); //마을
        int M = Integer.parseInt(st.nextToken()); //연결 도로
        int X = Integer.parseInt(st.nextToken()); //도착 마을

        //도로 정보 입력
        loads = new int[N+2][N+2];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            loads[a][b] = c;
            if(a == X) {
                que.add(new Pair(b,c));
            }
        }
        visited = new int[N+2];
        goHome(N,X); //X -> 각 마을

        Arrays.fill(visited, 0);
        for(int i = 1; i <= N; i++) {
            if(loads[i][X] > 0) {
                que.add(new Pair(i,loads[i][X]));
            }
        }
        goParty(N,X); //각 마을 -> X

        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(i != X)
                result = Math.max(loads[i][X]+loads[X][i], result);
        }
        System.out.println(result);
    }
    public static void goParty(int N, int X) {
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            if(visited[cur.x] == 0) {
                visited[cur.x] = 1;
                for (int i = 1; i <= N; i++) {
                    if (loads[i][cur.x] > 0 && visited[i] == 0) {
                        if (loads[i][X] == 0 || loads[i][X] > loads[i][cur.x] + cur.y) {
                            loads[i][X] = loads[i][cur.x] + cur.y;
                            que.add(new Pair(i, loads[i][X]));
                        }
                    }
                }
            }
        }
    }
    public static void goHome(int N,int X) {
        visited[X] = 1;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            if(visited[cur.x] == 0) {
                visited[cur.x] = 1;
                //System.out.println(cur.x+": "+cur.y);
                for (int i = 1; i <= N; i++) {
                    if (loads[cur.x][i] > 0 && visited[i] == 0) {
                        if (loads[X][i] == 0 || loads[X][i] > loads[cur.x][i] + cur.y) {
                            loads[X][i] = loads[cur.x][i] + cur.y;
                            que.add(new Pair(i, loads[X][i]));
                        }
                    }
                }
            }
        }
    }
    static class Pair implements Comparable<Pair>{
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return this.y-o.y;
        }
    }
}