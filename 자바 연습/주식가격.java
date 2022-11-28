import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int [length];
        Stack <Integer> st = new Stack<>();
        for(int i = 0; i < length; i++) {
            while(st.empty() == false && prices[st.peek()] > prices[i]) {
                answer[st.peek()] = i - st.peek();
                st.pop();
            }
            st.push(i);
        }
        while(!st.empty()) {
            answer[st.peek()] = length - st.peek()-1;
            st.pop();
        }
        return answer;
    }
}