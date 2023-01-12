import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static char[][] space;
    private static int[][] visited;
    private static int[][] visitedFire;
    private static int []x = {0,0,-1,1};
    private static int []y = {-1,1,0,0};
    private static int R;
    private static int C;
    private static Queue<Pair> user= new LinkedList<>();
    private static Queue<Pair> fire = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        space = new char[R][C];
        visited = new int[R][C];
        visitedFire = new int[R][C];
        for(int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < C; j++) {
                if('J' == tmp.charAt(j)) {
                    user.add(new Pair(i,j));
                }
                else if('F' == tmp.charAt(j)) {
                    fire.add(new Pair(i,j));
                }
                space[i][j] = tmp.charAt(j);
            }
        }
        userBfs();
//        print();
        fireBfs();
//        print2();
        int a = validI(0);
        a = Math.min(validI(R-1),a);
        a = Math.min(validJ(0),a);
        a = Math.min(validJ(C-1),a);
        if(a == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(a);
    }
    public static int validJ(int j) {
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < R; i++) {
            //System.out.println(i + ", " + j);
            if(space[i][j] == '.'){
                if(visited[i][j] != 0 && (visited[i][j] < visitedFire[i][j]|| visitedFire[i][j] == 0)) {
                    if (result == -1 || result > visited[i][j] + 1)
                        result = visited[i][j] + 1;
                }
            }
            if(space[i][j] == 'J') {
                if(result == -1 || result > visited[i][j] + 1)
                    result = visited[i][j] + 1;
            }
        }
        return result;
    }
    public static int validI(int i) {
        int result = Integer.MAX_VALUE;
        for(int j = 0; j < C; j++) {
            //System.out.println(i + ", " + j);
            if(space[i][j] == '.'){
                if(visited[i][j] != 0 && (visited[i][j] < visitedFire[i][j]|| visitedFire[i][j] == 0)) {
                    if (result == -1 || result > visited[i][j] + 1)
                        result = visited[i][j] + 1;
                }
            }
            if(space[i][j] == 'J') {
                if(result == -1 || result > visited[i][j] + 1)
                    result = visited[i][j] + 1;
            }
        }
        return result;
    }
    public static void print() {
        for(int i = 0; i < R; i ++) {
            for(int j = 0; j < C; j++) {
                System.out.print(visited[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print2() {
        for(int i = 0; i < R; i ++) {
            for(int j = 0; j < C; j++) {
                System.out.print(visitedFire[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void userBfs() {
        while(!user.isEmpty()) {
            Pair curUser = user.poll();
            //System.out.println(curUser.x + ", " + curUser.y);
            for(int i = 0; i < 4; i ++) {
                int nx = x[i]+ curUser.x;
                int ny = y[i]+ curUser.y;
                if(nx < 0 || nx >= R || ny < 0 || ny >= C)
                    continue;
                if(space[nx][ny] == '.' && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[curUser.x][curUser.y]+1;
                    user.add(new Pair(nx,ny));
                }
            }
        }
    }
    public static void fireBfs() {
        while(!fire.isEmpty()) {
            Pair curFire = fire.poll();

            for(int i = 0; i < 4; i ++) {
                int nx = x[i]+ curFire.x;
                int ny = y[i]+ curFire.y;
                if(nx < 0 || nx >= R || ny < 0 || ny >= C)
                    continue;
                if(space[nx][ny] == '.' && visitedFire[nx][ny] == 0) {
                    visitedFire[nx][ny] = visitedFire[curFire.x][curFire.y]+1;
                    fire.add(new Pair(nx,ny));
                }
            }
        }
    }
    public static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}