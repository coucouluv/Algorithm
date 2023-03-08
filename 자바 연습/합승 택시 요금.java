import java.util.*;
class Solution {
    int [][] road;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        road = new int[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(road[i], 123456789);
            road[i][i] = 0;
        }
        for(int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int c = fares[i][2];
            road[x][y] = c;
            road[y][x] = c;
        }
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    road[i][j] = Math.min(road[i][j], road[i][k] + road[k][j]);
                }
            }
        }
        answer = road[s][a] + road[s][b];
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, road[s][i] + road[i][a]+ road[i][b]);
        }
        return answer;
    }
}