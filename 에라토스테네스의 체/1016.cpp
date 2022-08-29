#include <iostream>
#include <cstring>
using namespace std;
const int MAX = 1000000;
bool nosquare[MAX+1];

int main() {

    long long minv, maxv;
    long long result = 0;

    cin >> minv >> maxv;




    memset(nosquare, true, sizeof(nosquare));

    for(long long i = 2; i*i <= maxv; i++) {
        long long x = minv / (i*i);
        if(minv % (i*i) != 0)
            x += 1;
        for(long long j = x; i*i*j <= maxv; j++)
            nosquare[j*i*i - minv] = false;

    }



    for(long long i = 0; i <= maxv-minv; i++)
        if(nosquare[i]) result += 1;

    cout << result;
    return 0;
}
