import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap <String, Integer> map = new HashMap<>();
        //넣어 놓기
        for(int i = 0; i < clothes.length; i++) {
            if(map.containsKey(clothes[i][1]) == true){
                map.put(clothes[i][1],map.get(clothes[i][1])+1);
            }
            else
                map.put(clothes[i][1],1);
        }
        for(String key: map.keySet()) {
            answer *= (map.get(key)+1);
        }
        answer -= 1;
        return answer;
    }
}