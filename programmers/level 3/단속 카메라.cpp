#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

bool compare(vector <int> a, vector <int> b) {
    if (a[1] == b[1])
        return a[0] < b[0];
    return a[1] < b[1];
}

int solution(vector<vector<int>> routes) {
    int answer = 0;
    sort(routes.begin(), routes.end(), compare);
    int camera = -30001;
    for (int i = 0; i < routes.size(); i++) {
        if (routes[i][0] > camera) {
            answer += 1;
            camera = routes[i][1];
        }
    }

    return answer;
}