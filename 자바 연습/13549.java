import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    private static boolean []visited = new boolean [100005]; //방문 여부 표시
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int result = bfs(N, K);
        System.out.println(result);

    }
    private static int bfs(int N, int K) {
        PriorityQueue<Pair> que = new PriorityQueue<>();
        que.add(new Pair(N,0));
        int ret = 0;
        visited[N] = true;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            //System.out.printf("curx: %d, curcnt: %d\n", cur, curcnt);
            visited[cur.x] = true;
            if(cur.x == K) {
                ret = cur.y;
                break;
            }
            if(cur.x*2 <= 100000 && !visited[cur.x*2]) {
                que.add(new Pair(cur.x*2, cur.y));
            }
            if(cur.x + 1 <= 100000 && !visited[cur.x+1]) {
                que.add(new Pair(cur.x+1, cur.y + 1));
            }
            if(cur.x -1 >= 0 && !visited[cur.x-1]) {
                que.add(new Pair(cur.x-1, cur.y+1));
            }
        }
        return ret;
    }
    private static class Pair implements Comparable<Pair>{
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Pair p) {
            return this.y - p.y;
        }
    }
}