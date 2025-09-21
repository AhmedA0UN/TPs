#include <iostream>
#include <string>
using namespace std;
#include<cmath>

int main() {
    float d , xa, ya, xb, yb;
    cout << "entrer les coordonnees des pts a et b ";
    cin >> xa;
    cin >> ya;
    cin >> xb;
    cin >> yb;
    d = distance(xa,ya,xb,yb);
    cout <<"la ddistance entre a et b est : " << d;
    return 0;
}

float distance (float xa, float ya, float xb , float yb){
    return sqrt ( pow(xa-xb, 2)+pow(ya,yb,2) );
    }
