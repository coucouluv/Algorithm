class Solution {
    private Pair result;
    private int[] sales;
    private int[][] people;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        sales = new int [emoticons.length];
        people = new int[users.length][2];
        people = users;
        result = new Pair(-1,-1);

        dfs(emoticons.length, 0,emoticons);

        answer[0] = result.cnt;
        answer[1] = result.price;
        return answer;
    }
    public void calculate(int length, int [] emoticons) {
        int total = 0;
        int cnt = 0;
        for(int i = 0; i < people.length; i++) {
            int userTotal = 0;
            // System.out.println("people : " + i);
            for(int j = 0; j < length; j++) {
                // System.out.println("people sales: " + people[i][0] + ", sales: " + sales[j]);
                if(people[i][0] <= sales[j]) {
                    userTotal += emoticons[j] * (100-sales[j])* 0.01;
                }
            }
            // System.out.println("total : " + userTotal);
            if(userTotal >= people[i][1]) {
                cnt++;
            }
            else {
                total += userTotal;
            }
        }
        if(cnt > result.cnt) {
            result = new Pair(cnt, total);
        }
        else if(cnt == result.cnt && total > result.price) {
            result = new Pair(cnt, total);
        }
    }
    public void dfs(int cnt, int idx, int [] emoticons) {
        if(idx == cnt) {
            calculate(cnt, emoticons);
            return;
        }
        for(int i = 1; i <= 4; i++) {
            sales[idx] = i*10;
            dfs(cnt, idx+1, emoticons);
        }
    }
}
class Pair {
    int cnt; //가입자 수
    int price; //가격
    public Pair(int cnt, int price) {
        this.cnt = cnt;
        this.price = price;
    }
}