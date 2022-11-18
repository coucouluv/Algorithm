#include <iostream>
#include <cstring>
using namespace std;
int farm[52][52];
int main() {
	int T;
	cin >> T;
	for (int test = 1; test <= T; test++) {

		int N;
		cin >> N;

		//초기화
		memset(farm, 0, sizeof(farm));

		for (int i = 0; i < N; i++) {
			string tmp;
			cin >> tmp;
			for (int j = 0; j < tmp.size(); j++) {
				farm[i][j] = tmp[j] - '0';
			}
		}
		int result = 0;
		int cnt = 1, index = N/2;
		bool down = false;
		for (int i = 0; i < N; i++) {
			for (int j = index - cnt / 2; j <= cnt / 2 + index; j++) {
				cout << i << ", " << j << "\n";
				result += farm[i][j];
			}
			if (cnt == N) down = true;
			if (down) cnt -= 2;
			else cnt += 2;
		}
		cout << "#"<< test << " " << result << "\n";
	}
	return 0;
}