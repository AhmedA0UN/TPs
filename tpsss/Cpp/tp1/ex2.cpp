#include <iostream>
using namespace std;

int main() {
    int e , m = 1123566551;
    cout << "entrer 10 entiers :" <<endl;
    for (int i=0;i<10;i++) {
            cin >> e;
            if(m > e){m=e;}
        }    
    cout << "le min est :" << m <<endl ;       
    return 0;
}
