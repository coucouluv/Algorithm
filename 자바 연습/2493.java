import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main {
    private static Stack<Integer> index = new Stack<>();
    private static int []result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int []tower = new int[N];
        st  = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }
        result = new int[N];
        index.push(N-1);
        for(int i = N-2; i >= 0; i--) {
            while(!index.isEmpty() && tower[index.peek()] <= tower[i]) {
                result[index.peek()] = i+1;
                index.pop();
            }
            index.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i : result) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}