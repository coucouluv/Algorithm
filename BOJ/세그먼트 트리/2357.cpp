#include <iostream>

using namespace std;
int segmentmin[(1<<18)+1];
int segmentmax[(1<<18)+1];
const int MAX = 1000000000;

void printmin(int left, int right) {

    int ret = MAX;
    while(left <= right) {
        if(left % 2 == 1) {
            ret = min(segmentmin[left], ret);
            left++;
        }
        if(right%2 == 0) {
            ret = min(segmentmin[right], ret);
            right--;
        }
        left /= 2;
        right /= 2;
    }
    cout << ret << " ";

}

void printmax(int left, int right) {
    int ret = 1;
    while(left <= right) {
        if(left % 2 == 1) {
            ret = max(segmentmax[left], ret);
            left++;
        }
        if(right%2 == 0) {
            ret = max(segmentmax[right], ret);
            right--;
        }
        left /= 2;
        right /= 2;
    }
    cout << ret << "\n";
}
int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n, m;
    cin >> n >> m;

    int num = 1;
    while(num < n) num = num*2;

    for(int i = 1; i <= num; i++) {
        if(i <= n) {
            cin >> segmentmin[i+num-1];
            segmentmax[i+num-1] = segmentmin[i+num-1];
        }
        else{
            segmentmax[i+num-1] = 1;
            segmentmin[i+num-1] = MAX;
        }
    }
    for(int i = num-1; i > 0; i--) {
        segmentmin[i] = min(segmentmin[i*2], segmentmin[i*2+1]);
    }

    for(int i = num-1; i > 0; i--) {
        segmentmax[i] = max(segmentmax[i*2], segmentmax[i*2+1]);
    }

    int a, b;
    for(int i = 0; i < m; i++) {
        cin >> a >> b;
        printmin(a+num-1, b+num-1);
        printmax(a+num-1, b+num-1);
    }

    return 0;
}
