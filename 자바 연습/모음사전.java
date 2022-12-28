import java.util.*;
class Solution {
    private char[] alpa = {'A','E','I','O','U'};
    private int sum = 0;
    private HashMap<String,Integer> map = new HashMap<>();
    public int solution(String word) {
        int answer = 0;
        dfs(0, "");

        return map.get(word);
    }
    public void dfs(int index,String tmp) {
        if(sum > 0) {
            map.put(tmp, sum);
        }
        sum += 1;
        if(index >= 5) return;

        for(int i = 0; i < 5; i++) {
            //tmp[index] = alpa[i];
            dfs(index+1, tmp+alpa[i]);
        }
    }
}