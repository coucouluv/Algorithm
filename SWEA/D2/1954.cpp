#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
int N; //달팽이 크기
int snail[15][15];
int y[] = {0,1,0,-1}; //오, 아래, 왼, 위
int x[] = {1,0,-1,0};
int num = 1; //달팽이에 들어갈 숫자
void makesnail() {
	//int finaly = N / 2, finalx = N/2;
	int cnt = N, index = 0; 
	int cy = 0, cx = 0;
	while (cnt > 0) {
		if (cnt == N) {
			for (int i = 0; i < cnt; i++) {
				snail[cy][cx] = num++;
				cy = cy + y[index];
				cx = cx + x[index];
			}
			cy = cy - y[index]; //마지막 잘못 업데이트한거 수정
			cx = cx - x[index];
			index += 1;
		}
		else {
			//두번 반복
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) {
					cy = cy + y[index];
					cx = cx + x[index];
					snail[cy][cx] = num++;
				}
				index += 1;
				if (index == 4) index = 0;
			}
		}
		cnt -= 1;
	}

}

int main() {

	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		cin >> N;

		//초기화
		memset(snail, 0, sizeof(snail));
		num = 1;
		makesnail();
		cout << "#" << i << "\n";
		for (int j = 0; j < N; j++) {
			for (int n = 0; n < N; n++) {
				cout << snail[j][n] << " ";
			}
			cout << "\n";
		}

	}
	return 0;
}