#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <algorithm>
using namespace std;
map <string, int> tmp;
vector<int> solution(vector<string> gems) {
    vector<int> answer = { 1,(int)gems.size() };
    int s = 0; //포인터 두개
    int e = 0;
    //일단 보석 담아놓기 몇개 있는 지 알고싶으니까
    for (int i = 0; i < gems.size(); i++) {
        tmp[gems[i]]++;
    }

    int size = tmp.size();
    int len = gems.size() - 1;
    tmp.clear();
    while (1) {
        if (tmp.size() == size) {
            if (len > e - s) {
                len = e - s;
                answer[1] = e;
                answer[0] = s + 1;
            }
            if (tmp[gems[s]] == 1) {
                tmp.erase(gems[s]);
            }
            else
                tmp[gems[s]]--;
            s++;
        }
        else if (e == gems.size()) break;
        else { //e를 뒤로 이동
            tmp[gems[e]]++;
            e++;
        }

    }
    return answer;
}