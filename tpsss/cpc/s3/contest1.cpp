#include<iostream>
using namespace std;

int t , x;

cin >> t;

while (t--){
    cin >> x;
    int y=0;
    while (int(string(x)+string(y))%(x+y) != 0){
        y++;
    }
    cout << y << endl;

}
