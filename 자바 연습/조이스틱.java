class Solution {

    public int solution(String name) {
        int answer = 0;
        int length =name.length();
        int move = length-1; //오른쪽으로 계속 이동했을 때
        for(int i = 0; i < length; i++) {
            answer += Math.min((name.charAt(i) - 'A'), ('Z' - name.charAt(i))+1);
            //연속 A 개수
            int index = i+1;
            while(index < length && name.charAt(index) == 'A') {
                index += 1;
            }
            move = Math.min(move,2*i+(length-index)); // 오른쪽으로 갓다가 왼쪽
            move = Math.min(move,(length-index)*2+i); //왼쪽으로 갓다가 오른쪽
        }
        return answer + move;
    }

}