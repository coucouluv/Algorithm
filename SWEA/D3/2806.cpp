#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int queen[12][12];
int N;
int result = 0;
void print() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << queen[i][j] << " ";
		}
		cout << "\n";
	}
	cout << "\n";
}
bool possible(int i, int j) {
	//cout << "index : " << i << ", " << j << "\n";
	
	for (int n = 0; n < N; n++) { //세로 확인
		if (queen[n][j] == 1)
			return false;
	}
	//대각선 확인
	int x = i + 1, y = j + 1;
	while (1) {
		if (x >= N || y >= N) break;
		//cout << x << ", " << y << "\n";
		if (queen[x][y] == 1)
			return false;
		x += 1;
		y += 1;
	}
	x = i - 1, y = j - 1;
	while (1) {
		if (x < 0 || y < 0) break;
		//cout << x << ", " << y << "\n";
		if (queen[x][y] == 1)
			return false;
		x -= 1;
		y -= 1;
	}

	//두번째 대각선
	x = i - 1, y = j + 1;
	while (1) {
		if (x < 0 || y >= N) break;
		//cout << x << ", " << y << "\n";
		if (queen[x][y] == 1)
			return false;
		x -= 1;
		y += 1;
	}
	
	x = i + 1, y = j - 1;
	while (1) {
		if (x >= N || y < 0) break;
		//cout << x << ", " << y << "\n";
		if (queen[x][y] == 1)
			return false;
		x += 1;
		y -= 1;
	}
	return true;
}

void solve(int cnt, int i) {
	if (cnt == N) { //다 놓았을 때

		result += 1;
		//print();
		return;
	}
	for (int j = 0; j < N; j++) {
		if (possible(i, j)) {
			queen[i][j] = 1;
			solve(cnt + 1, i + 1);
			queen[i][j] = 0; //원상복구
		}
	}
}

int main() {
	int T;
	cin >> T;
	for (int test = 1; test <= T; test++) {
		
		cin >> N;
		//초기화
		memset(queen, 0, sizeof(queen));
		result = 0;
		solve(0,0);
		cout << "#" << test << " " << result << "\n";
	}
	return 0;
}