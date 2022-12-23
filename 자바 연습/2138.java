import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] lights;
    private static int[] tmpLights;
    private static int N;
    private static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        lights = new int[N];
        tmpLights = new int[N];
        st = new StringTokenizer(br.readLine());
        String tmp = st.nextToken();
        for(int i = 0; i < N; i++) {
            lights[i] = tmp.charAt(i) - '0';
        }
        st = new StringTokenizer(br.readLine());
        answer = st.nextToken();

        copy(tmpLights,lights);
        int result = solve(0); //1번 전구 안 누름

        if(result == -1) {
            copy(lights,tmpLights);
            lights[0] = 1- lights[0];
            lights[1] = 1- lights[1];
            result = solve(1);
        }
        System.out.println(result);

    }
    public static int solve(int ret) {
        //int ret = 0;
        for(int i = 1; i < N; i++) {
            if((answer.charAt(i-1) - '0') != lights[i-1]) {
                ret += 1;
                for(int j = i-1; j <= i+1; j++) {
                    if(j >= N) break;
                    lights[j] = 1 - lights[j];
                }
            }
        }
        for(int i = 0; i < N; i++) {
            if((answer.charAt(i) - '0') != lights[i])
                return -1;
        }
        return ret;
    }
    public static void copy(int []tmp, int[] origin) {
        for(int i = 0; i < N; i++) {
            tmp[i] = origin[i];
        }
    }
}