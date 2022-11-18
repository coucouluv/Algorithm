#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
char board[10][10];
int main() {
	
	for (int test = 1; test <= 1; test++) {
		int N;
		cin >> N;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cin >> board[i][j];
			}
		}
		int result = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//옆으로 
				if (j + N - 1 < 8) {
					string a = "";
					for (int n = j; n < j + N; n++) {
						a += board[i][n];
					}
					string b = a;
					reverse(a.begin(), a.end());
					
					if (b.compare(a) == 0) {
						//cout << b << "\n";
						result += 1;
					}
				}
				//아래로
				if (i + N - 1 < 8) {
					string c = "";
					for (int n = i; n < i + N; n++) {
						c += board[n][j];
					}
					string d = c;
					reverse(c.begin(), c.end());
					if (d.compare(c) == 0) {
						//cout << d << "\n";
						result += 1;
					}
				}
			}
		}
		cout << "#"<< test << " " << result << "\n";
	}
	return 0;
}