import java.util.*;
class Solution {
    private long people;
    private long maxTime;
    public long solution(int n, int[] times) {
        long answer = 0;
        people = n;
        int max = Arrays.stream(times).max().getAsInt();
        maxTime = (long)n * (long)max;
        answer = binarySearch(0, maxTime+1, times);
        return answer;
    }
    public long binarySearch(long low, long high, int[] times) {
        while(low + 1 < high) {
            long mid = (low+high)/2;
            //System.out.println(low + ", " + high + " = " + mid);
            if(isPossible(mid, times)) {
                high = mid;
            }
            else {
                low = mid;
            }
        }
        return high;
    }
    public boolean isPossible(long mid, int[] times) {
        long cnt = 0;
        for(int i = 0; i < times.length; i++) {
            long num = mid / (long)times[i];
            cnt += num;
            if(cnt >= people)
                return true;
        }
        return false;
    }
}