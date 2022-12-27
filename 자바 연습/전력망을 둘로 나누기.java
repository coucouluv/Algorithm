import java.util.*;
import java.lang.Math;
class Solution {
    private int [][]tree;
    private int result = 100;
    private int []visited;
    public int solution(int n, int[][] wires) {
        //int answer = -1;
        tree = new int[n+1][n+1];
        //트리 만들기
        for(int i = 0; i < n-1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            tree[a][b] = 1;
            tree[b][a] = 1;
        }

        visited = new int[n+1];
        for(int i = 0; i < n-1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            tree[a][b] = 0;
            tree[b][a] = 0;
            int first = makeNetwork(a,n);
            int second = makeNetwork(b,n);
            result = Math.min(result, Math.abs(first-second));
            tree[a][b] = 1;
            tree[b][a] = 1;
            Arrays.fill(visited,0);
        }
        return result;
    }
    public int makeNetwork(int pare, int n) {
        visited[pare] = 1;
        int ret = 1;
        for(int i = 1; i <= n; i++) {
            if(visited[i] == 0 && tree[pare][i] == 1) {
                //System.out.println(i);
                ret += makeNetwork(i, n);
            }
        }
        return ret;
    }
}