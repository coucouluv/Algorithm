#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int main() {

    int N, K;
    cin >> N >> K;

    bool isprime[N+1];
    //int prime[N];

    memset(isprime, true, sizeof(isprime));

    vector <int> result;
    for(int i = 2; i <= N; i++) {
        if(isprime[i]) result.push_back(i);
        for(int j=i*2; j <= N; j += i) {
            if(isprime[j]) {
                result.push_back(j);
                isprime[j] = false;
            }
        }
        if(result.size() >= K)
            break;
    }
    cout << result[K-1];
    return 0;
}
