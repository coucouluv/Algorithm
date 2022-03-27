#include <iostream>
#include <algorithm>

using namespace std;
int A[1024 *1024*2+1];
pair<int, int> part[1000001];

bool com(pair<int, int> a, pair<int , int> b) {
    if (a.first == b.first)
        return a.second > b.second;

    return a.first < b.first;
}
void update(int value, int index) {

    //cout << index << endl;
    A[index] = value;
    index--;
    index /= 2;
    while(index > 0) {
        //cout << index << endl;
        A[index] = max(A[index], value);
        index--;
        index /= 2;
    }
    A[index] = max(A[index], value);
}
//
//void print() {
//
//    for(int i = 0; i < 15; i++) {
//        cout << i << ": " << A[i] << endl;
//    }
//
//}
int findvalue(int left, int right) {

    int ret = 0;
    while(left < right) {
        if(left % 2 == 0) {
            ret = max(ret, A[left]);
            left++;
        }
        if(right % 2 == 1) {
            ret = max(ret, A[right]);
            right--;
        }
        left--;
        right--;
        left /= 2;
        right /= 2;
    }
    if(left == right)
        ret = max(ret, A[left]);
    return ret;

}
int main() {

    int n;
    cin >> n;

    int a;
    for(int i = 0; i < n; i++) {
        cin >> a;
        part[i] = make_pair(a, i);
    }
    sort(part, part+n, com);

    int num = 1;
    while(num < n) num=num+num;

    for(int i = 0; i < n; i++) {
        int value = findvalue(num-1, part[i].second+num-1)+1;
        update(value, part[i].second+num-1);
//        print();
    }
    cout << A[0] << "\n";
    return 0;
}
