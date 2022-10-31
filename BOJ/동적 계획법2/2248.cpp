#include <iostream>
#include <cstring>
using namespace std;

long long dp[32][32];
string result;

//int solution(int n, int one) {
//
//    if(dp[n][one] != -1) return dp[n][one];
//
//    if(n == 0 || one == 0) return dp[n][one] = 1;
//    int ret = solution(n-1, one);
//    if(one > 0) ret += solution(n-1, one-1);
//    dp[n][one] = ret;
//    return ret;
//}

void findvalue(int N, int L, long long I) {
    if(N == 0) return;
    if(L == 0) {
        for(int i = 0; i < N; i++)
            result += '0';
        return;
    }
    long long pivot = 0;
    for(int i = 0; i <= L; i++)
        pivot += dp[N-1][i];

    if(I <= pivot) {
        result += '0';
        findvalue(N-1, L, I);
    }
    else {
        result += '1';
        findvalue(N-1, L-1, I-pivot);
    }

}
int main() {

    int N, L;
    long long I;

    cin >> N >> L >> I;

//    memset(dp, -1, sizeof(dp));
    //solution(N, L);
    dp[0][0] = 1;
    for(int i = 1; i <= N; i++)  {
        dp[i][0] = 1;
        dp[i][i] = 1;
    }
    for(int i = 2; i <= N; i++) {
        for(int j = 1; j <= i; j++)
            dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
    }
    //cout << dp[N][L] << endl;

    findvalue(N,L,I);

    cout << result << "\n";

    return 0;
}
