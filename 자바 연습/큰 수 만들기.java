import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int cnt = number.length() - k;
        while(cnt > 0) {
            int maxNum = -1;
            for(int i = index; i <= number.length()-cnt ; i++) {
                if(number.charAt(i) == '9') {
                    maxNum = 9;
                    index = i;
                    break;
                }
                else if(maxNum < (number.charAt(i)-'0')) {
                    maxNum = (number.charAt(i)-'0');
                    index = i;
                }
            }
            sb.append(Integer.toString(maxNum));
            index += 1;
            cnt -= 1;
        }
        return sb.toString();
    }
}
