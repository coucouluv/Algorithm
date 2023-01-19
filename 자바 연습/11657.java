import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    private static int N;
    private static int M;
    private static long []dist = new long[502];
    private static ArrayList<Bus>[] busStop;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
//        busStop = new ArrayList[N+1];
        busStop = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            busStop[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            busStop[a].add(new Bus(b,c));
        }

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        boolean circle = false;
        for(int i = 0; i <=N; i++) {
            for(int j = 1; j <= N; j++) {
                for(Bus b : busStop[j]) {
                    int nextBus = b.bus;
                    int nextTime = b.time;
                    if(dist[j] != Long.MAX_VALUE && dist[nextBus] > dist[j] + nextTime) {
                        dist[nextBus] = dist[j] + nextTime;
                        if(i == N) {
                            circle = true;
                        }
                    }
                }
            }
//            sb.append(result).append("\n");
        }

        if(circle) {
            System.out.println("-1");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for(int i = 2; i <= N; i++) {
                if(dist[i] == Long.MAX_VALUE) {
                    sb.append(-1).append("\n");
                }
                else {
                    sb.append(dist[i]).append("\n");
                }
            }
            System.out.print(sb);
        }

    }
    public static class Bus {
        int bus;
        int time;
        Bus(int bus, int time) {
            this.bus = bus;
            this.time = time;
        }
    }
}