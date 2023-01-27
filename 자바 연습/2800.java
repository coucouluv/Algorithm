import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<String> result = new ArrayList<>();
    private static List<Pair> words = new ArrayList<>();
    private static int[] visited;
    private static String expression = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        expression = st.nextToken();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char cur = expression.charAt(i);
            if(cur == '(') {
                stack.add(i);
            }
            else if(cur == ')') {
                words.add(new Pair(stack.pop(), i));
            }
        }
        visited = new int[expression.length()];
        Arrays.fill(visited, 1);
        dfs(0, words.size());

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < result.size(); i++) {
            if(!result.get(i).equals(result.get(i-1))) {
                sb.append(result.get(i)).append("\n");
            }
        }
        System.out.print(sb);
    }
    private static void dfs(int index, int length) {
        if (index == length) {
            String cur = "";
            for(int i = 0; i < expression.length(); i++) {
                if(visited[i] == 1) {
                    cur += expression.charAt(i);
                }
            }
            result.add(cur);
            return;
        }
        Pair cur = words.get(index);
        visited[cur.start] = 1;
        visited[cur.end] = 1;
        dfs(index+1, length);

        visited[cur.start] = 0;
        visited[cur.end] = 0;
        dfs(index+1, length);
    }

    public static class Pair {
        int start;
        int end;
        public Pair( int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
