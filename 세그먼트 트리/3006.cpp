#include <iostream>
#include <algorithm>
using namespace std;

int segment[(1<<18)+1];

void update(int index) {

    segment[index] = 0;
    index /= 2;
    while(index > 0) {
        segment[index] -= 1;
        index /= 2;
    }
}

void print(int left, int right) {

    int ret = 0;
    while(left <= right) {
        if(left % 2 == 1) {
            ret += segment[left];
            left++;
        }
        if(right % 2 == 0) {
            ret += segment[right];
            right--;
        }
        left /= 2;
        right /= 2;
    }
    cout << ret << "\n";
}

int main() {

    int n, num;
    cin >> n;

    int numbers[n+1];
    int tmp;

    int sum = 1;
    while(sum < n) sum *= 2;

    for(int i = 1; i <= n; i++) {
        cin >> tmp;
        numbers[tmp] = i;
        segment[sum+i-1] = 1;
    }

    for(int i = sum-1; i > 0; i--)
        segment[i] = segment[i*2] +segment[i*2+1];


    num = n/2;

    int st = 1, en = n;
    for(int i = 1; i <= num; i++) {
        print(sum, sum+numbers[st]-2);
        update(sum+numbers[st]-1);

        print(sum+numbers[en], sum*2-1);
        update(sum+numbers[en]-1);

        st++;
        en--;
    }
    if(n % 2 == 1) {
        print(sum, sum+numbers[st]-2);
        update(sum+numbers[st]-1);
    }
    return 0;
}
