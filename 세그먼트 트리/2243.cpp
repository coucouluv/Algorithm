#include <iostream>

using namespace std;

const int ST = (1<<21);
int segment[(1<<22)+1];
int num = 1<<21;

void update(int index, int value) {

    while(index > 0) {
        segment[index] += value;
        //index--;
        index /= 2;
    }
    //segment[index] += value;
}

int print(int index, int value) {
    if(index >= ST) return index - ST;

    int pivot = segment[index*2];
    //index++;
    //cout << index*2 << " : " << pivot << ", " << value << endl;
    if(value <= pivot)
        return print(index*2, value);
    else
        return print(index*2+1, value-pivot);
}


int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;

    int a, b, c;
    for(int i = 0; i < n; i++) {
        cin >> a;
        if(a == 1) {
            cin >> b;
            int result = print(1, b);
            cout << result+1 << "\n";
            update(result+num, -1);
            //cout << num -result << "\n";
        }
        else {
            cin >> b >> c;
            update(num-1+b,c);
        }
    }
    return 0;
}
