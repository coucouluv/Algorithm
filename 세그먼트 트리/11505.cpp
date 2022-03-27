#include <iostream>

using namespace std;

long long MOD = 1000000007;
long long segment[(1<<21)+1];
int num = 1;

void update(long long index, long long value) {

    segment[index] = value;
    index = index/2;
    while(index > 0) {
        segment[index] = (segment[index*2] * segment[index*2+1]) % MOD;
        index = index/2;
    }
}
//long long update(int index, int target, int value, int nodeL, int nodeR) {
//
//   if(target < nodeL || target > nodeR) return segment[index];
//
//    if(nodeL == nodeR) return segment[index] = value;
//
//    int mid = (nodeL + nodeR)/2;
//    return segment[index] = (update(index*2, target,value, nodeL, mid) * update(index*2+1,target,value, mid+1,nodeR) )%MOD;
//}

long long print(long long left, long long right) {
    long long ret = 1;
    while(left <= right) {
        if(left % 2 == 1) {
            ret = ret * segment[left];
            ret %= MOD;
            left++;
        }
        if(right % 2 == 0) {
            ret = ret * segment[right];
            ret %= MOD;
            right--;
        }
        left /= 2;
        right /= 2;
    }
//    if(left == right)
//        ret = ret * segment[left];

    //ret = ret % MOD;
    return ret;
    //cout << ret << "\n";

}
//long long print(int index, int b, int c, int nodeL, int nodeR) {
//
//    if(c < nodeL || b > nodeR) return 1;
//
//    if(b <= nodeL && c >= nodeR) return segment[index];
//
//    int mid = (nodeL + nodeR)/2;
//    return (print(index*2, b,c, nodeL, mid) * print(index*2+1,b,c, mid+1,nodeR) )%MOD;
//}
int main() {


    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;

    cin >> n >> m >> k;

    while(num < n) num *= 2;

    //fill(segment, segment+2097153, 1);

    int tmp;
    for(int i = num; i < num*2; i++) {
        if(i < num+n)
            cin >> segment[i];
        else
            segment[i] = 1;
        //update(1, i, tmp, 1, n);
        //segment[i] = tmp;
    }
//    for(int i = num+n; i < num+num; i++)
//        segment[i] = 1;
//
    for(int i = num-1; i > 0; i--)
        segment[i] = (segment[2*i]*segment[2*i+1]) % MOD;

    long long a, b, c;
    for(int i = 0; i < m+k; i++) {
        cin >> a >> b >> c;
        if(a == 1) {
            //update(1, b, c, 1, n); print(1, b,c,1,n)
            update(b+num-1, c);
        }
        else {
            cout << print(b+num-1, c+num-1) << "\n";
        }
    }

    return 0;
}
