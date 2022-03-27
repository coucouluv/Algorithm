#include <iostream>

using namespace std;

long long segment[1024 *1024*2+1];
int n, m, k;
int num = 1;

void update(int b, long long c) {

    segment[b] = c;
    b = b/2;
    while(b > 0) {
        segment[b] = segment[b*2] + segment[b*2+1];
        b = b/2;
    }

}

//long long print(int b, int c, int num, int nodeL, int nodeR) {
//
//    if(c < nodeL || b > nodeR) return 0;
//    if(b <= nodeL && c >= nodeR) return segment[num];
//
//    int mid = (nodeL + nodeR)/2;
//    return print(b,c,num*2, nodeL, mid) + print(b,c,num*2+1, mid+1, nodeR);
//}
void print(int b, int c) {
    long long ret = 0;
    while(b < c) {
        if(b % 2 == 1) {
            ret += segment[b];
            b++;
        }
        if(c % 2 == 0) {
            ret += segment[c];
            c--;

        }
        b /= 2;
        c /= 2;

    }
    if(b == c)
        ret += segment[b];

    cout << ret << "\n";
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m >> k;


    while(num < n) {
        num *= 2;

    }

    long long tmp;
    for(int i = num; i < n+num; i++) {
        cin >> tmp;
        segment[i] = tmp;
    }

    for(int i = num-1;i > 0; i--) {
        segment[i] = segment[i*2]+segment[i*2+1];
    }

    int a, b;
    long long c;
    for(int i = 0; i < m+k; i++) {
        cin >> a >> b >> c;
        if(a == 1) {
            b = num + b -1;
            update(b,c);
        }
        else {
            print(b+num-1, c+num-1);
        }
    }

    return 0;
}
