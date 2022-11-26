import java.util.*;
class Pair {
    int location;
    int important;
    Pair (int important,int location) {
        this.location = location;
        this.important = important;
    }

}
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue <Pair> que = new LinkedList<>(); //위치와 중요도
        PriorityQueue<Integer> imports = new PriorityQueue<>(Collections.reverseOrder());
        int cnt = 1; //인쇄 카운트

        for(int i = 0; i < priorities.length; i++) {
            que.offer(new Pair(priorities[i], i));
            imports.offer(priorities[i]);
        }
        while(!que.isEmpty()) {
            Pair tmp = que.poll();
            if(tmp.important == imports.peek()) {
                if(tmp.location == location) {
                    answer = cnt;
                    break;
                }
                cnt += 1;
                imports.remove();
            }
            else {
                que.add(tmp);
            }
        }
        return answer;
    }
}