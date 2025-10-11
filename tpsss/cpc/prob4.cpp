#include <iostream>
using namespace std;

int main() {
    int k,n;
    cin >> k >>n;  
    while (k--) {
        
        if (n % 10 != 0)
            n--;
        else
            n /=10;
    }

    cout << n << endl;
    return 0;
}
