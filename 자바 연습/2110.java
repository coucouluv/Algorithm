import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    private static int N;
    private static int C;
    private static int []home;
    //private static int []result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new int[N];
        //result = new int[C];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            home[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(home);
        int result = binarySearch(0, home[N - 1]);
        System.out.println(result);
    }

    public static int binarySearch(int low, int high) {
        while(low + 1 < high) {
            int mid = (low + high) / 2;
            if(possible(mid)) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
    public static boolean possible(int mid) {
        int cnt = 1;
        int cur = home[0];
        for(int i = 0; i < N; i++) {
            if(home[i]-cur >= mid) {
                cur = home[i];
                cnt += 1;
            }
        }
        if(cnt >= C) return true;
        return false;
    }
}