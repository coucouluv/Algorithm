class Solution {
    int []visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new int[computers.length];
        for(int i = 0; i < computers.length; i++) {
            if(visited[i] == 0) {
                dfs(i, computers);
                answer += 1;
            }
        }
        return answer;
    }
    public void dfs(int index, int [][] computers) {
        visited[index] = 1;
        for(int i = 0; i < computers.length; i++) {
            if(visited[i] == 0 && computers[index][i] == 1) {
                dfs(i, computers);
            }
        }
    }
}