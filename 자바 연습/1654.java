import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static int []wires;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N  = Integer.parseInt(st.nextToken());
        wires = new int[K];
        long maxNum = 0;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            wires[i] = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, wires[i]);
        }
        long result = binarySearch(0,maxNum+1);
        System.out.println(result);
    }
    public static long binarySearch(long low, long high) {
        while(low + 1 < high) {
            long mid = (low + high) /2;
            if(isPossible(mid)) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
    public static boolean isPossible(long mid) {
        int cnt = 0;
        for(int i = 0; i < K; i++) {
            cnt += (wires[i]/mid);
        }
        if(cnt >= N)
            return true;
        return false;
    }
}
