#include <iostream>
#include <algorithm>
using namespace std;

int segment[(1<<18)+1];

//bool cmp(pair<int,int> a, pair<int,int> b) {
//    if(a.first == b.first)
//        return a.second > b.second;
//    return a.first < b.first;
//}
int findvalue(int left, int right) {
    int ret = 0;
    while(left <= right) {
        if(left % 2 == 1) {
            ret = max(ret, segment[left]);
            left++;
        }
        if(right % 2 == 0) {
            ret = max(ret, segment[right]);
            right--;
        }
        left /= 2;
        right /= 2;
    }
    return ret;
}
void print(int value, int index) {

    segment[index] = value;
    index /= 2;
    while(index > 0) {
        segment[index] = max(segment[index], value);
        index /= 2;
    }

}
int main() {

    int n, num = 1;

    cin >> n;

    while(num < n) num *= 2;

    pair<int, int> line[n+1];
    int tmp;
    for(int i = 1; i <= n; i++) {
        cin >> tmp;
        line[i] = make_pair(tmp, i);
    }

    sort(line, line+n+1);

    for(int i = 1; i <= n; i++) {
        int value = findvalue(num, line[i].second+num-1)+1;
        print(value, line[i].second+num-1);
    }
    cout << n-segment[1] <<"\n";
    return 0;
}
