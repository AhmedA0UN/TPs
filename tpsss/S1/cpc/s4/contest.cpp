#include <bits/stdc++.h>
using namespace std;

int main() {

    int t;
    cin >> t;
    while (t--) {
        string a ,b;
        cin >> a >> b;
        ;
        for (int i = 0; i < n; i++) cin >> b[i];

        vector<int> a;
        int l = 0, r = n - 1;
        while (l <= r) {
            a.push_back(b[l++]);   
            if (l <= r) a.push_back(b[r--]);
        }

        for (int i = 0; i < n; i++) {
            cout << a[i] << (i+1==n ? '\n' : ' ');
        }
    }
    return 0;
}


































































