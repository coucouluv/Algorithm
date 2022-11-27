import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Deque<Integer> deq = new LinkedList<>();
        //길이만큼 넣기
        for(int i = 0; i < bridge_length; i++) {
            deq.add(0);
        }
        int totalweight = 0; // 덱안의 총 무게
        int cnt = 0; //트럭 인덱스
        while(cnt < truck_weights.length) {
            int firsttruck = deq.poll();
            if(firsttruck > 0) {
                //cnt += 1;
                totalweight -= firsttruck;
            }
            if(totalweight+truck_weights[cnt] <= weight) {
                deq.add(truck_weights[cnt]);
                totalweight += truck_weights[cnt];
                cnt += 1;
            }
            else {
                deq.add(0);
            }
            answer += 1;
        }
        return answer+bridge_length;
    }
}