#include <iostream>

using namespace std;

const int MAX = 200001;
int f[MAX][19];


int main() {
    std::cin.tie(NULL);
    std::ios::sync_with_stdio(0);

    int m;
    cin >> m;


    for(int i = 1; i <= m; i++)
        cin >> f[i][0];

    for(int j = 1; j < 19; j++) {
        for(int i = 1; i <= m; i++) {
            f[i][j] = f[f[i][j-1]][j-1];
        }
    }

    int Q;
    cin >> Q;
    for(int i = 0; i < Q; i++) {
        int n, x;
        cin >> n >> x;

        int index = 18;
        while(index >= 0) {
            if(n >= (1<<index)) {
                n -= (1<<index);
                x = f[x][index];
            }
            index--;
        }
        cout << x << "\n";
    }

    return 0;
}
