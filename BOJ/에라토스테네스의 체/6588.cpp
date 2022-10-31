#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
vector <int> prime;
const int MAX = 1000000;
int main() {
    std::cin.tie(NULL);
    std::ios::sync_with_stdio(0);

    int T;
    string result = "Goldbach's conjecture is wrong.\n";

    bool isprime[1000001];

    memset(isprime, true, sizeof(isprime));

    isprime[0] = false;
    isprime[1] = false;
    for(int i = 2; i <= MAX; i++) {
        for(int j=i*2; j <= MAX; j += i) {
            isprime[j] = false;

        }
    }

    while(1) {
        cin >> T;
        if(T == 0) break;
        int a = 0;
        int b = 0;
        for(int i = 2; i <= T; i++) {
            if(isprime[i] == true && isprime[T-i] == true) {
                a = i;
                b = T-i;
                break;
            }
        }
        if(a > 0)
            cout << T << " = " << a << " + " << b << "\n";
        else
            cout << result;
    }

    return 0;
}
