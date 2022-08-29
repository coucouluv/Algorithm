#include <iostream>
#include <string>

using namespace std;

int num[1001][1001];
string s1, s2;

/*
메모리 초과 발생
string num[1001][1001];

    for(int i = 1; i <= s1.length(); i++) {
        for(int j = 1; j <= s2.length(); j++) {
            if(s1[i-1] == s2[j-1]) {
                num[i][j] = num[i-1][j-1]+ s1[i-1];
            }
            else {
                if(num[i-1][j].length() > num[i][j-1].length())
                    num[i][j] = num[i-1][j];
                else
                    num[i][j] = num[i][j-1];
            }
        }
    }

    cout << num[s1.length()][s2.length()].length() << "\n";
    cout << num[s1.length()][s2.length()] << "\n";


*/

int main() {

    cin >> s1 >> s2;

    for(int i = 1; i <= s1.length(); i++) {
        for(int j = 1; j <= s2.length(); j++) {
            if(s1[i-1] == s2[j-1]) {
                num[i][j] = num[i-1][j-1]+ 1;
            }
            else {
                num[i][j] = max(num[i-1][j],num[i][j-1]);
            }

        }
    }

    string result;
    int index = s2.length();

    // if num[i][j] == num[i-1][j]
    // else if num[i][j] == num[i][j-1]
    // else 대각선
    for(int i = s1.length(); i > 0; i--) {
        if(num[i][index] == 0) break;
        for(int j = index; j > 0; j--) {
            if(num[i][j] == num[i-1][j])
                break;
            if(num[i][j] != num[i][j-1]) {
                result += s2[j-1];
                index = j-1;
                break;
            }
        }

    }

    cout << num[s1.length()][s2.length()] << "\n";
    //cout << num[s1.length()][s2.length()] << "\n";

    if(num[s1.length()][s2.length()] != 0) {
        for(int i = result.length()-1; i >=0; i--)
            cout << result[i];
        cout << "\n";
    }
    return 0;
}
