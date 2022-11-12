#include <string>
#include <vector>
#include <iostream>
#include <sstream>
#include <map>
using namespace std;
int price[10000]; // 요금 
map <int, int> car; //번호, 시간

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;

    for (int i = 0; i < records.size(); i++) {
        stringstream is(records[i]);
        string tmp, tmpnum, where; //시간, 번호, in or out
        is >> tmp >> tmpnum >> where;
        //시간
        int time = stoi(tmp.substr(0, 2)) * 60; //시
        time += stoi(tmp.substr(3, 2));
        //번호
        int carnum = stoi(tmpnum);
        if (where.compare("IN") == 0) {
            car[carnum] = time; //시간
        }
        else {
            price[carnum] += time - car[carnum]; //누적 시간 
            car[carnum] = -1; //시간 
        }
    }
    //돌면서 요금 계산
    for (auto iter = car.begin(); iter != car.end(); iter++) {
        if (iter->second != -1) { //마지막 아웃 기록이 없는거임
            price[iter->first] += (1439 - iter->second);
        }
        int cprice = 0;
        cprice += fees[1];
        if (price[iter->first] >= fees[0]) {
            price[iter->first] -= fees[0];
            cprice += price[iter->first] / fees[2] * fees[3];
            if (price[iter->first] % fees[2] != 0) cprice += fees[3];
        }
        answer.push_back(cprice);
    }
    return answer;
}