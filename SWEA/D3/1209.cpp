#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
int board[105][105];
int main() {
	
	for (int test = 1; test <= 10; test++) {
		int n;
		cin >> n;
		int result = 0;
		//입력 받으면서 행, 대각선 처리
		int sum = 0, diagonal1 = 0, diagonal2 = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				cin >> board[i][j];
				sum += board[i][j];
				if (i == j)
					diagonal1 += board[i][j];
				if( i+j == 99)
					diagonal2 += board[i][j];
			}
			result = max(result, sum);
			sum = 0;
		}
		result = max(result, diagonal1);
		result = max(result, diagonal2);

		//열 처리
		for (int i = 0; i < 100; i++) {
			sum = 0;
			for (int j = 0; j < 100; j++) {
				sum += board[j][i]; 
			}
			result = max(result, sum);
		}

		cout << "#" << test << " " << result << "\n";
	}
	return 0;
}