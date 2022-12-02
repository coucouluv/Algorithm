import java.util.*;

class Solution {
    private String [] result;
    public String solution(int[] numbers) {
        result = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            result[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(result, new Comparator<String>() {
            @Override
            public int compare(String st1, String st2) {
                String st1first = st1+st2;
                String st2first = st2+st1;
                return  st2first.compareTo(st1first);
            }
        });
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for(String st: result) {
            sb.append(st);
        }
        answer = sb.toString();
        if(answer.charAt(0) == '0')
            answer = "0";
        return answer;
    }
}