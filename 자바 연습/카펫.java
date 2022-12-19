import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        int sum = brown + yellow;
        for(int i = 3; i <= sum; i++) {
            if(sum % i == 0) { //약수일 때
                int width = sum / i;
                int height = i;
                if((width-2)*(height-2) == yellow) {
                    answer[0] = width;
                    answer[1] = height;
                    break;
                }
            }
        }
        return answer;
    }
}