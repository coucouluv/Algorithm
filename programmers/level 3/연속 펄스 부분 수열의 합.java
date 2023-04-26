class Solution {
    private long[] dp1;
    private long[] dp2;
    public long solution(int[] sequence) {
        dp1 = new long[sequence.length];
        dp2 = new long[sequence.length];

        int pattern1 = 1, pattern2 = -1;
        for(int i = 0; i < sequence.length; i++) {
            dp1[i] = sequence[i] * pattern1;
            dp2[i] = sequence[i] * pattern2;
            pattern1 *= -1;
            pattern2 *= -1;
        }
        long sum = dp1[0];

        for(int i = 1; i < sequence.length; i++) {
            dp1[i] = Math.max(dp1[i], dp1[i]+dp1[i-1]);
            sum = Math.max(sum, dp1[i]);
        }

        sum = Math.max(sum, dp2[0]);
        for(int i = 1; i < sequence.length; i++) {
            dp2[i] = Math.max(dp2[i], dp2[i]+dp2[i-1]);
            sum = Math.max(sum, dp2[i]);
        }
        // System.out.println(sum);
        return sum;
    }
}