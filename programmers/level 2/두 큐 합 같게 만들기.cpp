#include <string>
#include <vector>
#include <iostream>

using namespace std;
long long queue1sum = 0;
long long queue2sum = 0;
int queue1start = 0;
int queue2start = 0;
int solution(vector<int> queue1, vector<int> queue2) {
    int answer = 0;

    long long sum = 0;
    for (int i = 0; i < queue1.size(); i++) {
        sum += queue1[i];
        sum += queue2[i];
        queue1sum += queue1[i];
        queue2sum += queue2[i];
    }
    if (sum % 2 == 1) return -1;
    bool possible = false;
    int size = queue1.size() * 4;
    while (1) {
        if (answer > size) break;
        if (queue1sum > queue2sum) {
            queue1sum -= queue1[queue1start];
            queue2sum += queue1[queue1start];
            queue2.push_back(queue1[queue1start]);
            queue1start += 1;
        }
        else if (queue1sum < queue2sum) {
            queue2sum -= queue2[queue2start];
            queue1sum += queue2[queue2start];
            queue1.push_back(queue2[queue2start]);
            queue2start += 1;
        }
        else {
            return answer;
        }
        answer += 1;
    }
    return -1;
}