#include<iostream>
#include <algorithm>
#include <vector>
using namespace std;
int N;
int answer = 0;
int space[1005];
int main(int argc, char** argv)
{
	int test_case;
	//int T;
	//cin >> T;

	for (test_case = 1; test_case <= 10; ++test_case)
	{
		cin >> N;
		for (int i = 0; i < N; i++) {
			cin >> space[i];
		}
		answer = 0;

		for (int i = 2; i < N - 2; i++) {
			int maxnum = 0;
			for (int j = i - 2; j <= i + 2; j++) {
				if (j == i) continue;
				maxnum = max(maxnum, space[j]);
			}
			if (maxnum < space[i])
				answer += space[i] - maxnum;
		}
		cout << "#" << test_case << " " << answer << "\n";
	}
	return 0;
}

