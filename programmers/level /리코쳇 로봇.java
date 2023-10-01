import java.util.*;

class Solution {
    
    private int []dx = {0,0,-1,1};
    private int []dy = {-1,1,0,0};
    private boolean visited[][];
    private int N, M, result = Integer.MAX_VALUE;
    public int solution(String[] board) {
        int answer = 0;
        
        Queue<Pair> que = new LinkedList<>();
        int stX = 0, stY = 0;
        N = board.length;
        M = board[0].length();
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i].charAt(j) == 'R') {
                    stX = i; 
                    stY = j;
                    que.add(new Pair(i, j, 0));
                }
            }
        }
        
        visited[stX][stY] = true;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            
            if(board[cur.x].charAt(cur.y) == 'G') {
                result = Math.min(result, cur.cnt);
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x;
                int ny = cur.y;
                while((nx >= 0 && nx < N && ny >= 0 && ny < M) && board[nx].charAt(ny) != 'D') {
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                }
                nx = nx - dx[i];
                ny = ny - dy[i];
                
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    que.add(new Pair(nx, ny, cur.cnt+1));
                } 
            }     
        }
        
        if(result == Integer.MAX_VALUE) 
            return -1;
        
        return result;
    }
}

class Pair {
    int x;
    int y;
    int cnt;
    public Pair(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        // this.direct = direct;
    }
}
