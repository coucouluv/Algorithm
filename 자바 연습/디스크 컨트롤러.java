import java.util.*;

class Pair implements Comparable<Pair> {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Pair p) {
        if(p.y == y)
            return x-p.x;
        return y-p.y;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Pair> que = new PriorityQueue<>();

        Arrays.sort(jobs, new Comparator <int []>() {
            @Override
            public int compare(int [] a, int [] b) {
                if(a[0] == b[0])
                    return a[1] -b[1];
                return a[0] -b [0];
            }
        });
        que.add(new Pair(jobs[0][0],jobs[0][1]));
        int time = jobs[0][0], index = 1;
        while(!que.isEmpty()) {
            // System.out.println("time is " + time);
            Pair cur = que.poll();
            time += cur.y;
            answer += time - cur.x;
            while(index < jobs.length && time >= jobs[index][0]) {
                //System.out.println("time is " + time + " : " +  jobs[index][0]);
                que.add(new Pair(jobs[index][0],jobs[index][1]));
                index += 1;
            }
            //큐는 비었는데 아직 작업 완료하지 않았다면
            if(que.isEmpty() && index < jobs.length) {
                que.add(new Pair(jobs[index][0],jobs[index][1]));
                time = jobs[index][0];
                index += 1;
            }
        }

        answer = answer / jobs.length;
        return answer;
    }

}