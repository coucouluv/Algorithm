import java.util.*;
class Solution {
    private int []graph;
    private int sum = 0;
    private PriorityQueue<Pair> pq = new PriorityQueue<>();
    public int solution(int n, int[][] costs) {
        int answer = 0;

        graph = new int[n];

        for(int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int c = costs[i][2];
            pq.add(new Pair(a,b,c));
        }
        Arrays.fill(graph, -1);
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if(merge(cur.x, cur.y)) {
                sum += cur.dist;
            }
        }
        return sum;
    }
    public int find(int a) {
        if(graph[a] == -1)
            return a;
        return find(graph[a]);
    }
    public boolean merge(int a,int b) {
        a = find(a);
        b = find(b);
        if(a == b)
            return false;
        graph[b] = a;
        return true;
    }
}

class Pair implements Comparable<Pair>{
    int x;
    int y;
    int dist;
    Pair(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
    @Override
    public int compareTo(Pair o) {
        return this.dist-o.dist;
    }
}