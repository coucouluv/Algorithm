#include <string>
#include <vector>
#include <map>
#include <iostream>
using namespace std;
map <string, int> numbers;
bool solution(vector<string> phone_book) {
    bool answer = true;
    int size = phone_book.size();
    for (int i = 0; i < size; i++) {
        numbers[phone_book[i]] = 1;
    }
    auto before = numbers.begin();
    auto iter = numbers.begin();
    for (iter++; iter != numbers.end(); iter++) {
        string tmp = iter->first;
        if (tmp.find(before->first) == 0) {
            return false;
        }

        before = iter;

    }
    return answer;
}