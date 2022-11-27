import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> foods = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            foods.add(scoville[i]);
        }
        while(!foods.isEmpty()) {
            int first = foods.poll();
            if(foods.size() == 0) {
                if(first < K) {
                    answer = -1;
                    break;
                }
            }
            if(first >= K) {
                break;
            }
            else {
                int second = foods.poll();
                int mix = first + (second*2);
                foods.add(mix);
            }
            answer += 1;
        }
        return answer;
    }
}