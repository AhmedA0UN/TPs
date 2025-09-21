#include <iostream>
using namespace std;

int main() {
    int e,i,m=maxint ;
    cout << "entrer 10 entiers :"
    for(int i=0;i<10;i++){
            cin >> e;
            if(m > e){m=e;}
            }    
    cout << "le min est :" << m ;       
    return 0;
}
