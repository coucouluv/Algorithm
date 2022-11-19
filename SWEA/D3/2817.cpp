#include <iostream>
#include <cstring>
using namespace std;

int A[25];
int N, K;
int result = 0;
void solve(int index, int sum) {
	if (sum == K) {
		result += 1;
		return;
	}
	if (sum > K) return;
	if (index == N) return;
	for (int i = index; i < N; i++) {
		solve(i + 1, sum+A[i]);
	}
}

int main() {

	int T;
	cin >> T;
	
	for (int test = 1; test <= T; test++) {
		
		cin >> N >> K;

		memset(A, 0, sizeof(A)); //초기화
		result = 0;
		for (int i = 0; i < N; i++) {
			cin >> A[i];
		}
		solve(0, 0);
		cout << "#" << test << " " << result << "\n";
	}
	return 0;
}