#include <iostream>
#include <cstring>
using namespace std;
const int MAX = 51;
long long K;
int N;
long long dp[MAX][2*MAX][2];
long long infinite = 0x3c3c3c3c3c3c3c3c;

long long solutiondp(int index, int open, int nono) {

    if(index == N) {
        return nono || open != 0;
    }
    long long &ret = dp[index][open+N][nono];
    if (ret != infinite) return ret;
    ret = 0;
    ret += solutiondp(index+1, open+1, nono);
    ret += solutiondp(index+1, open-1, nono || open <= 0);
    return ret;
}

void result(int index, int open, int nono, long long K) {
    if(index == N) return;
    if(dp[index+1][open+1+N][nono] >= K) {
        if(index == N-1 && K==2) cout << ")";
        else
            cout << "(";
        result(index+1, open+1, nono, K);
    }
    else {
        cout << ")";
        result(index+1, open-1, nono || open <= 0, K-dp[index+1][open+1+N][nono]);
    }

}

int main() {

    cin >> N >> K;

    memset(dp, 0x3c,sizeof(dp));
    solutiondp(0,0,0);
    if(K+1 > dp[0][N][0])
        cout <<"-1\n";
    else
        result(0,0,0,K+1);
    return 0;
}
