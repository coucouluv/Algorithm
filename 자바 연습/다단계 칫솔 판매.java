import java.util.*;
class Solution {
    private HashMap<String, String> map = new HashMap<>();
    private HashMap<String, Integer> profit = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        map.put("minho", "root");
        for(int i = 0; i < enroll.length; i++) {
            if(referral[i].equals("-")) {
                map.put(enroll[i], "minho");
            }
            else {
                map.put(enroll[i], referral[i]);
            }
        }
        for(int i = 0; i < seller.length; i++) {
            calculate(seller[i], amount[i]*100);
        }

        for(int i = 0; i < enroll.length; i++) {
            if(profit.get(enroll[i]) == null) {
                answer[i] = 0;
            }
            else {
                answer[i] = profit.get(enroll[i]);
            }
        }
        return answer;
    }
    public void calculate(String name, int price) {
        if(name.equals("root")) {
            return;
        }
        int nextPrice = price/10;
        if(nextPrice > 0)
            calculate(map.get(name), nextPrice);
        price = price - nextPrice;
        if(profit.get(name) == null) {
            profit.put(name, price);
        }
        else {
            profit.put(name, profit.get(name)+price);
        }
    }
}
