import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int [] belt  = new int[30005];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String []tmp = in.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]); //벨트 접시 수
        int d = Integer.parseInt(tmp[1]); //초밥 가짓수
        int k = Integer.parseInt(tmp[2]); //연속 접시의 수
        int c = Integer.parseInt(tmp[3]); //쿠폰 번호

        for(int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(in.readLine());
        }
        int result  = 0, start = 0, end = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(c,1);
        while (start < N) {
            int cnt = Math.abs(start-end);
            //sb.append(start + ", " + end + "\n");
            int tmpend = end % N;
            if(cnt < k) {
                if(map.containsKey(belt[tmpend]) == true)
                    map.put(belt[tmpend], map.get(belt[tmpend])+1);
                else
                    map.put(belt[tmpend],1);
                end += 1;
                //if(end == N)
                    //end = 0;
            }
            else if (cnt >= k) {
                if(cnt == k) {
                    result = Math.max(result, map.size());
                }
                if (map.get(belt[start]) > 1) {
                    map.replace(belt[start], map.get(belt[start]) - 1);
                } else
                    map.remove(belt[start]);
                start += 1;
            }
        }
        //System.out.println(sb);
        System.out.println(result);
    }
}