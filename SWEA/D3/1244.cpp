#include<iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
int cnt; //교환 횟수
vector<int> board;
int result = 0; //최종 금액
void settingboard(int number) {
    int tmp = 0;
    while (number > 0) {
        tmp = number % 10;
        board.push_back(tmp);
        number = number / 10;
    }
    //거꾸로 들어가있음
    /*for (int i = 0; i < board.size(); i++)
        cout << board[i] << " ";
    cout << "\n";*/
}

void findmax(int left, int en, int tmpcnt) {
    //cout << cnt << ": " << tmpcnt << "\n";
    if (tmpcnt == cnt) { //교환 횟수가 동일할 때
        int sum = 0, num = 1;
        for (int i = 0; i < board.size(); i++) {
            sum += board[i] * num;
            num *= 10;
        }
        //cout <<"current value : " << sum << "\n";
        result = max(result, sum);
        return;
    }

    for (int i = left; i <= en; i++) {
        for (int j = i + 1; j <= en; j++) {
            swap(board[i], board[j]);
            findmax(i, en, tmpcnt + 1);
            swap(board[i], board[j]); //원상복구    
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
        int number;
        cin >> number >> cnt;
        //초기화
        board.clear();
        result = 0;
        settingboard(number);
        if (cnt > board.size()) cnt = board.size();
        findmax(0, board.size() - 1, 0);
        cout << "#" << test_case << " " << result << "\n";
    }
    return 0;
}