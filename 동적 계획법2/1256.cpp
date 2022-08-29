#include <iostream>

using namespace std;

long long dp[101][101];
string result;

void solution(int N, int M, long long K) {
    if (N == 0 && M == 0) return;
    if(N == 0) {
        for(int i = 0; i < M; i++)
            result += 'z';
        return;
    }
    if(M == 0) {
        for(int i = 0; i < N; i++)
            result += 'a';
        return;
    }
    long long pivot = 0;
    pivot = dp[N-1][M];
    if(pivot >= K) {
        result += 'a';
        solution(N-1, M, K);
    }
    else {
        result += 'z';
        solution(N, M-1, K-pivot);
    }
    return;
}

int main() {

    int N, M;
    long long K;

    cin >> N >> M >> K;

    dp[0][0] = 0;
    for(int i = 1; i <= 100; i++) {
        dp[i][0] = 1;
        dp[0][i] = 1;
    }

    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= M; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
            if (dp[i][j] > 1000000000) dp[i][j] = 1000000000;
        }
    }
    if(dp[N][M] < K)
        cout << "-1\n";
    else {
        solution(N,M,K);
        cout << result << "\n";
    }
    return 0;
}
