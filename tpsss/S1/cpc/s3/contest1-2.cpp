#include<iostream>
#include<string>
using namespace std;

int main() {
    int t, x;
    cin >> t;

    while (t--) {
        cin >> x;
        int y = 1;
        while (true) {
            string concat = to_string(x) + to_string(y);
            int num = stoi(concat);
            if (num % (x + y) == 0) {
                break;
            }
            y++;
        }
        cout << y << endl;
    }

    return 0;
}
