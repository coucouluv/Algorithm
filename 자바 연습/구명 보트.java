import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Deque<Integer> deq = new ArrayDeque<>();
        Arrays.sort(people);
        for(int i = 0; i < people.length; i++) {
            deq.push(people[i]);
        }
        while(!deq.isEmpty()) {
            int num = deq.pollFirst();
            if(!deq.isEmpty() && deq.peekLast()+num <= limit) {
                deq.pollLast();
            }
            answer += 1;
        }
        return answer;
    }
}