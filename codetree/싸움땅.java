import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static Info[][] board;
    static Person[] people;
    static int[] dx = {-1,0,1,0}; //위, 오, 아래, 왼
    static int[] dy = {0,1,0,-1};

    static int[] power;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new Info[n+1][n+1];
        people = new Person[m];
        power = new int[m];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                Info info = new Info(-1,tmp);
                board[i][j] = info;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            Person person = new Person(x,y,d,s,0);
            people[i] = person;
            board[x][y].person = i;
        }

        for(int i = 0; i < k; i++) {
            move();
        }
        for(int i = 0; i < m; i++) {
            System.out.print(power[i] + " ");
        }
        System.out.println();
    }
    public static void loserMove(int i, int x, int y) {
        int direct = people[i].d;
        int nx = people[i].x + dx[direct];
        int ny = people[i].y + dy[direct];
        if(nx <= 0 || nx > n || ny <= 0 || ny > n || board[nx][ny].person >= 0) {
            for(int d = 0; d < 3; d++) {
                direct += 1;
                if(direct == 4) {
                    direct = 0;
                }
                nx = people[i].x + dx[direct];
                ny = people[i].y + dy[direct];
                if(nx <= 0 || nx > n || ny <= 0 || ny > n || board[nx][ny].person >= 0) {
                    continue;
                }
                else {
                    break;
                }
            }
            people[i].d = direct;
        }
        //총 획득해
        getGun(i, nx, ny);
        //보드 업데이트 해
        //내 위치도 업데이트 해
        board[people[i].x][people[i].y].person = -1;
        people[i].x = nx;
        people[i].y = ny;
        board[people[i].x][people[i].y].person = i;
    }

    public static void fight(int i, int idx, int x, int y) {

        int powerM = 0, powerY = 0; //나, 너 크기
        //싸우기
        powerM = people[idx].s + people[idx].gun;
        powerY = people[i].s + people[i].gun;
        //내가 더 쎄
        if(powerM > powerY || (powerM == powerY &&people[idx].s > people[i].s)) {
            power[idx] += Math.abs(powerM - powerY);
            //너 총 내려놔
            board[x][y].gun.add(people[i].gun);
            people[i].gun = 0;
            //너 이동해
            loserMove(i, x, y);
            //난 총 획득해
            getGun(idx, x, y);
            //보드 업데이트해
            board[x][y].person = idx;
        }
        //너가 더 쎄
        else if(powerM < powerY || (powerM == powerY &&people[idx].s < people[i].s)) {
            power[i] += Math.abs(powerM - powerY);
            //나 총 내려놔
            board[x][y].gun.add(people[idx].gun);
            people[idx].gun = 0;
            //나 이동해
            loserMove(idx, x, y);
            //너 총 획득해
            getGun(i, x, y);
            //보드 업데이트 해
            board[x][y].person = i;
        }
    }
    public static void getGun(int idx, int x, int y) {
        int gun = board[x][y].gun.peek();
        if(gun > people[idx].gun) {
            board[x][y].gun.poll();
            int tmp = people[idx].gun;
            people[idx].gun = gun;
            board[x][y].gun.add(tmp);
        }
    }

    public static void move() {
        for(int i = 0; i < m; i++) {
            //이동
            int direct = people[i].d;
            int nx = people[i].x + dx[direct];
            int ny = people[i].y + dy[direct];
            board[people[i].x][people[i].y].person = -1; //나 이제 여기 없어
            if(nx <= 0 || nx > n || ny <= 0 || ny > n) {
                direct = (direct +2) % 4;
                nx = people[i].x + dx[direct];
                ny = people[i].y + dy[direct];
                people[i].d = direct;
            }
            //위치 변경
            people[i].x = nx;
            people[i].y = ny;
            //이동한 곳에 사람 있음??
            if(board[nx][ny].person >= 0) {
                //싸워
                fight(board[nx][ny].person, i, nx, ny);
            }
            else {
                //총 획득해
                getGun(i, nx, ny);
                board[nx][ny].person = i; //이제 내 위치 여기야
            }
        }
    }
}

class Info {
    int person; //사람 있으면 사람 번호
    PriorityQueue<Integer> gun;
    public Info(int person, int gun) {
        this.person = person;
        this.gun = new PriorityQueue<>(Collections.reverseOrder());
        this.gun.add(gun);
    }
}

class Person {
    int x;
    int y;
    int d; //방향
    int s; // 초기 능력치
    int gun; //총
    public Person(int x, int y, int d, int s, int gun) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.s = s;
        this.gun = gun;
    }
}

