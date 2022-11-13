#include<iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int scores[105];
int main(int argc, char** argv)
{
    int test_case;
    int T;
    cin >> T;

    for (test_case = 1; test_case <= T; ++test_case)
    {
        int number;
        cin >> number;
        //초기화
        memset(scores, 0, sizeof(scores));
        for (int i = 0; i < 1000; i++) {
            int tmp;
            cin >> tmp;
            scores[tmp] += 1;
        }
        int result = 0, cnt = 0;
        for (int i = 100; i >= 0; i--) {
            if (cnt < scores[i]) {
                result = i;
                cnt = scores[i];
            }
            //cout << i << ": " << scores[i] << "\n";
        }
        cout << "#" << test_case << " " << result << "\n";
    }
    return 0;
}