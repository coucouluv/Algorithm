#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

#define MAX 100001
int segment[MAX*8];
int book[MAX];
int t, n, m;

void update(int index, int value) {

    segment[index] = value;
    index /= 2;
    while(index > 0) {
        segment[index] = segment[index*2] + segment[index*2+1];
        index /= 2;
    }

}

void print(int left, int right) {

    int ret = 0;
    while(left <= right) {
        if(left % 2 == 1) {
            ret += segment[left];
            left++;
        }
        if(right % 2 == 0) {
            ret += segment[right];
            right--;
        }
        left /= 2;
        right /= 2;
    }
    cout << ret << " ";
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> t;
    for(int i = 0; i < t; i++) {

        //fill(segment, segment+MAX*8, 0);
        fill(book, book+MAX, 0);
        cin >> n >> m;

        int num = ceil(log2(n+m));
        num = 1<<(num+1);
        num--;

        for(int j = 1; j <= n+m; j++) {
            if(j <= n) {
                book[j] = n-j+1;
                update(num+j,1);
            }
            else
                update(num+j,0);
        }

//        for(int j = num-1; j > 0; j--)
//            segment[j] = segment[j*2] + segment[j*2+1];

        int a;
        for(int j = 0; j < m; j++) {
            cin >> a;
            //a = a+num+m-1;
            print(book[a]+num+1,num+n+j);
            update(book[a]+num, 0);
            book[a] = n+j+1;
            update(book[a]+num, 1);
        }
        cout << "\n";
    }

    return 0;
}
