
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
class Main {
    private static int result = 0;
    private static String t;
    private static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();
        Queue <String> que = new LinkedList<>();
        que.offer(t);
        while(!que.isEmpty()) {
            String cur = que.poll();
            if(cur.length() == s.length()) {
                if(cur.equals(s)) {
                    result = 1;
                    break;
                }
                else
                    continue;
            }
            if(cur.charAt(cur.length()-1) == 'A') {
                que.offer(cur.substring(0,cur.length()-1));
            }
            if(cur.charAt(0) == 'B') {
                StringBuilder sb = new StringBuilder(cur.substring(1));
                que.offer(sb.reverse().toString());
            }
        }
        System.out.println(result);
    }
}