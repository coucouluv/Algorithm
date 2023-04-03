import java.util.*;
class Solution {
    List<String> result = new ArrayList<>();; //정답 후보 담기
    Map<String,List<String>> flight = new HashMap<>();
    boolean[] visited;
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        String tmp = "ICN";
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                visited[i] = true;

                dfs(1,tickets[i][1], tmp + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }

        Collections.sort(result);
        String[] answer = result.get(0).split(" ");
        return answer;
    }

    public void dfs(int cnt, String value, String tmp, String[][] tickets) {
        if(cnt == tickets.length) {
            result.add(tmp);
            return;
        }
        for(int i = 0; i < tickets.length; i++) {
            //방문 처리
            if(tickets[i][0].equals(value) && !visited[i]) {
                visited[i] = true;

                dfs(cnt+1, tickets[i][1], tmp + " "  + tickets[i][1], tickets);
                visited[i] = false;
                //방문 취소 처리
            }
        }
    }
}