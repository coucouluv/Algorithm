import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, K;
    private static Set<String> nums = new TreeSet<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            nums.clear();

            makeSet(word);
            int cnt = 1;
            for(String result : nums) {
                if(cnt == K) {
                    sb.append("#").append(i).append(" ").append(calculate(result)).append("\n");
                    break;
                }
                cnt++;
            }
        }
        System.out.print(sb);
    }

    public static long calculate(String result) {
        long ret = 0;
        int cnt = 1; //영어면 55빼자
        for(int i = result.length()-1; i >= 0; i--) {
            int number = 0;
            if(result.charAt(i) >= 'A' && result.charAt(i) <= 'Z') {
                number = result.charAt(i) - 55;
            }
            else {
                number = result.charAt(i) - '0';
            }
            ret += cnt*number;
            cnt *= 16;
        }
        return ret;
    }

    public static void makeSet(String word) {
        int cursor = 0;
        for (int i = 0; i < N/4; i++) {
            int index = cursor;
            for (int j = 0; j < 4; j++) {
                if(index + N/4 > N) {
                    String tmp = word.substring(index, N);
                    tmp += word.substring(0, N/4-(N-index));
                    nums.add(tmp);
                    index = N/4-(N-index);
//                    System.out.print(tmp + " ");
                }
                else {
                    String tmp = word.substring(index, index+ N/4);
                    nums.add(tmp);
                    index = index+ N/4;
//                    System.out.print(tmp + " ");
                }
//                System.out.println();
            }
            cursor -= 1;
            if(cursor == -1) {
                cursor = N-1;
            }
        }
        
    }
}