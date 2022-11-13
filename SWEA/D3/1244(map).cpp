#include<iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <map>
#include <string>
using namespace std;
int cnt; //교환 횟수
map<string, vector<int>> board;
int result = 0; //최종 금액
string number;

void findmax(int st, int en,int tmpcnt) {
	//cout << cnt << ": " << tmpcnt << "\n";
	if (tmpcnt == cnt) { //교환 횟수가 동일할 때
		//cout <<"current value : " << number << "\n";
		result = max(result, stoi(number));
		return;
	}
	for (int i = st; i <= en; i++) {
		for (int j = i + 1; j <= en; j++) {
			//cout << i << ": " << j << "\n";
			swap(number[i], number[j]);
			auto iter = board.find(number);
			if (iter== board.end()) { //없음
				//cout << "dont have number" << "\n";
				//cout << number << ", " << tmpcnt << "\n";
				board[number].push_back(tmpcnt+1);
				findmax(i, en,tmpcnt + 1);
				//swap(number[i], number[j]);
			}
			else {
				vector <int> tmp = iter->second;
				if (find(tmp.begin(),tmp.end(),tmpcnt+1) == tmp.end()) {
					////cout << "dont have cnt" << "\n";
					board[number].push_back(tmpcnt+1);
					//swap(number[i], number[j]);
					findmax(i, en, tmpcnt + 1);
					
				}
			}
			swap(number[i], number[j]);
		}
	}
	
}
int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		cin >> number >> cnt;
		//초기화
		board.clear();
		result = 0;

		findmax(0, number.length() - 1, 0);
		cout << "#" << test_case << " " << result << "\n";
	}
	return 0;
}

