import java.util.*;
class Solution {
    private int[][] space = new int[102][102];
    private int[][] visited = new int[102][102];
    private int[] y = {0,0,-1,1};
    private int[] x = {-1,1,0,0};
    private Rectangle[] rect;
    public int solution(int[][] rectangle, int characterX,
                        int characterY, int itemX, int itemY) {
        int answer = 0;
        rect = new Rectangle[rectangle.length];
        for(int i = 0; i < rectangle.length; i++) {
            rect[i] = new Rectangle(rectangle[i][0]*2, rectangle[i][1]*2,
                    rectangle[i][2]*2, rectangle[i][3]*2);
            for(int y = rectangle[i][1]*2; y <= rectangle[i][3]*2; y++) {
                for(int x = rectangle[i][0]*2; x <= rectangle[i][2]*2; x++) {
                    space[y][x] = 1;
                }
            }
        }
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        return (visited[itemY*2][itemX*2])/2;
    }
    public boolean isPossible(int ny, int nx) {
        for(int i = 0; i < rect.length; i++) {
            if(rect[i].inner(nx,ny)) {
                return false;
            }
        }
        return true;
    }
    public void bfs(int charX, int charY, int itemX, int itemY) {
        Queue <Pair> que = new LinkedList<>();
        que.add(new Pair(charX, charY));
        visited[charY][charX] = 0;
        while(!que.isEmpty()) {
            Pair cur = que.poll();
            if(cur.x == itemX && cur.y == itemY) {
                continue;
            }
            for(int i = 0; i < 4; i++) {
                int ny = cur.y + y[i];
                int nx = cur.x + x[i];
                if(ny < 0 || ny > 102 || nx < 0 || nx > 102) {
                    continue;
                }
                if(space[ny][nx] == 1 && (visited[ny][nx] == 0 ||
                        visited[ny][nx] > visited[cur.y][cur.x] + 1)) {
                    if(isPossible(ny, nx)) {
                        visited[ny][nx] = visited[cur.y][cur.x] + 1;
                        que.add(new Pair(nx,ny));
                    }
                }
            }
        }

    }
}
class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Rectangle {
    int leftX; int leftY;
    int rightX; int rightY;
    public Rectangle(int leftX, int leftY, int rightX, int rightY) {
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.rightY = rightY;
    }
    public boolean inner(int nx, int ny) {
        return (leftX < nx) && (nx < rightX) && (leftY < ny) && (ny < rightY);
    }
}