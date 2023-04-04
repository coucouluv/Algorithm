import java.util.*;
import java.io.*;

public class Test2 {
	
	private static int n, m;
	private static int [][]board;
	private static int [][]visited;
	private static int []dx = {0,0,-1,1};
	private static int []dy = {-1,1,0,0};
	private static int point = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); //일반 블록 색
		board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int idex = 0;
		while(true) {
			//가장 큰 블록 그룹 찾기
			//없으면 탈출
			Block block = findBlock();
			if(block.cnt < 2) {
				break;
			}
//			print(block);
			//제거
			removeBlock(block.i, block.j);
			//점수 획득
			point += block.cnt * block.cnt;
//			printBoard();
			//중력 작용
			gravity();
//			printBoard();
			//90도 반시계 회전
			rotation();
//			printBoard();
			//중력 작용
			gravity();
//			printBoard();
		}
		System.out.println(point);
	}
	public static void rotation() {
		int [][]tmpBoard = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tmpBoard[i][j] = board[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[j][i] = tmpBoard[i][n-j-1];
			}
		}
	}
	public static void gravity() {
		for (int i = 0; i < n; i++) {
			for (int j = n-1; j >= 0; j--) {
				if (board[j][i] >= 0 && board[j][i] != m+1) {
					int tmp = board[j][i];
					board[j][i] = m+1;
					int idx = j;
					for (; idx < n; idx++) {
						if (board[idx][i] == -1 || (board[idx][i] >= 0 && board[idx][i]!= m+1))
							break;
					}
					board[idx-1][i] = tmp;
				}
			}

		}
	}
	public static void removeBlock(int x, int y) {
		Queue<Pair> que = new LinkedList<>();
		que.add(new Pair(x,y));
		int color = board[x][y];
		board[x][y] = m+1;
		while(!que.isEmpty()) {
			Pair cur = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.i + dx[i];
				int ny = cur.j + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == -1) {
					continue;
				}
				//해당 블록 제거
				if(board[nx][ny] == color || board[nx][ny] == 0) {
					board[nx][ny] = m+1;
					que.add(new Pair(nx, ny));
				}
			}
		}
	}
	public static void print(Block block) {
		System.out.println("default block: " + block.i + ", " + block.j);
		System.out.println("rainbow: " + block.rainbow + ", cnt : " + block.cnt);
	}
	public static void printBoard() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static Block bfs(int x, int y, int color) {
		Queue<Pair> que = new LinkedList<>();
		que.add(new Pair(x,y));
		
		//기준 블록
		Block result = new Block(x,y,0,1);
		while(!que.isEmpty()) {
			Pair cur = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.i + dx[i];
				int ny = cur.j + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == -1) {
					continue;
				}
				//무지개 블록일 때
				if(board[nx][ny] == 0 && visited[nx][ny] != color) {
					visited[nx][ny] = color;
					result.rainbow += 1;
					result.cnt += 1;
					que.add(new Pair(nx,ny));
				}
				//같은 컬러 블록일 때
				else if(board[nx][ny] == color && visited[nx][ny] == 0) {
					visited[nx][ny] = color;
					que.add(new Pair(nx,ny));
					result.cnt += 1;
					//기준 블록 업데이트
					if(result.i > nx || (result.i == nx && result.j > ny)) {
						result.i = nx;
						result.j = ny;
					}
				}
			}
		}
		return result;
	}
	public static Block findBlock() {
		visited = new int[n][n];
		Block result = new Block(0,0,0,0);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] == 0 && board[i][j] > 0 && board[i][j] != m+1) {
//					System.out.println("cur index : " + i + ", " + j);
					visited[i][j] = board[i][j];
					Block block = bfs(i, j, board[i][j]);
//					print(block);
					//더 큰 블록이면 바꾸기
					result = changeBlock(result, block);
				}
			}
		}
		return result;
	}
	public static Block changeBlock(Block result, Block block) {
		if(result.cnt < block.cnt) {
			return block;
		}
		if(result.cnt == block.cnt) {
			if(result.rainbow < block.rainbow) {
				return block;
			}
			if(result.rainbow == block.rainbow) {
				if(result.i < block.i) {
					return block;
				}
				if(result.i == block.i && result.j < block.j) {
					return block;
				}
			}
		}
		return result;
	}
}

class Block {
	int i;
	int j; //기준 블록 정보
	int rainbow; //무지개 블록 개수
	int cnt; //블록 수

	public Block(int i, int j, int rainbow, int cnt) {
		this.i = i;
		this.j = j;
		this.rainbow = rainbow;
		this.cnt = cnt;
	}
}

class Pair {
	int i;
	int j;
	public Pair(int i, int j) {
		this.i = i;
		this.j = j;

	}
}