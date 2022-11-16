#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector <int> boxes;
int main() {

	for (int test = 1; test <= 10; test++) {
		int cnt;
		cin >> cnt;

		//초기화
		boxes.clear();
		int tmp;
		for (int i = 0; i < 100; i++) {
			cin >> tmp;
			boxes.push_back(tmp);
		}
		for (int i = 0; i < cnt; i++) {
			sort(boxes.begin(), boxes.end());
			boxes[0] += 1;
			boxes[boxes.size() - 1] -= 1;
		}
		sort(boxes.begin(), boxes.end());
		cout << "#" << test << " " <<  boxes[boxes.size() - 1]-boxes[0] << "\n";
	}
	return 0;
}